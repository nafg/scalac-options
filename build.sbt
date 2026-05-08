import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*
import scala.concurrent.{Await, blocking}

import _root_.io.github.nafg.scalacoptions.{ScalacOptions, options}

import sbt.complete.DefaultParsers.spaceDelimited
import sbt.util.CacheImplicits._


ThisBuild / crossScalaVersions := Seq("2.12.21", "2.13.18", "3.3.7")
ThisBuild / scalaVersion       := "2.12.21"
ThisBuild / scalacOptions ++=
  ScalacOptions.all(scalaVersion.value)((opts: options.Common) => opts.deprecation ++ opts.feature)
ThisBuild / scalacOptions ++= Seq("-release", "8")

ThisBuild / organization := "io.github.nafg.scalac-options"

ThisBuild / versionScheme := Some("early-semver")

// sbt-git defaults to JGit for read operations, but JGit 5.13.x doesn't support git worktrees
// (NoWorkTreeException on load). Shell out to the `git` CLI instead. See sbt/sbt#2323.
useReadableConsoleGit

def runVersionUpdater(log: sbt.util.Logger, dryRun: Boolean): Unit =
  log.info(Await.result(new VersionUpdater().run(dryRun = dryRun), 5.minutes))

val updateVersions       = taskKey[Unit]("Update versions.yaml with latest Scala releases from Maven Central")
val updateVersionsDryRun = taskKey[Unit]("Check for new Scala versions without modifying versions.yaml")
val runScalacFn          = taskKey[(Versions.Minor, String) => String](
  "Function that runs scalac for a given version + flag, returning combined stdout+stderr"
)
val generate             = inputKey[Seq[File]]("Generate code. Optionally pass one or more Scala versions.")

def selectScalaVersions(args: Seq[String]): Seq[Versions.Minor] = {
  val requested = args.flatMap(_.split(",")).filter(_.nonEmpty)
  val all       = Versions.loadVersions().flatMap(_.allMinors)
  if (requested.isEmpty) all
  else {
    val allByVersion = all.map(version => version.versionString -> version).toMap
    val unknown      = requested.filterNot(allByVersion.contains)
    if (unknown.nonEmpty)
      sys.error(
        s"Unknown Scala version(s): ${unknown.mkString(", ")}. " +
          s"Known versions: ${all.map(_.versionString).mkString(", ")}"
      )
    requested.map(allByVersion)
  }
}

def writeGeneratedCode(sourceManaged: File, outputs: Generator.Outputs): Seq[File] = {
  val dir    = sourceManaged / "io" / "github" / "nafg" / "scalacoptions"
  val result = Generator.parseAllOutputs(outputs)
  result.allContainers.map { c =>
    val file = dir / "options" / (c.name + ".scala")
    IO.write(
      file,
      s"""package io.github.nafg.scalacoptions.options
         |
         |${c.code}
         |""".stripMargin
    )
    file
  } :+ {
    val file = dir / "ScalacOptionsBase.scala"
    IO.write(
      file,
      s"""package io.github.nafg.scalacoptions
         |
         |import scala.collection.immutable.ListMap
         |
         |trait ScalacOptionsBase {
         |  val versionMap = ListMap(
         |${result.versionMap
          .map { case (version, name) => s"""    "$version" -> options.$name""" }
          .mkString(",\n")}
         |  )
         |}
         |""".stripMargin
    )
    file
  }
}

lazy val launcher =
  project
    .settings(
      publish / skip := true,
      libraryDependencies ++= Seq(
        ("io.get-coursier" %% "coursier" % "2.1.24").cross(CrossVersion.for3Use2_13),
        "com.lihaoyi"      %% "os-lib"   % "0.11.8"
      ),
      runScalacFn    := {
        val log           = streams.value.log
        val cp            = (Compile / fullClasspath).value.files
        val scalaRunner   = (Compile / runner).value
        val mainClassName = (Compile / mainClass).value.getOrElse(sys.error("No main class found in scalacRunner"))

        val baseCacheStoreFactory = streams.value.cacheStoreFactory

        { (version, flag) =>
          val cacheStoreFactory = baseCacheStoreFactory.sub(version.versionString).sub(flag)
          val cacheStore        = cacheStoreFactory.make("scalac-options-output.json")
          val cached            =
            Cache.cached[Unit, String](cacheStore) { _ =>
              blocking {
                val tempFile = os.temp(prefix = "scalac-out", suffix = ".txt")
                try {
                  scalaRunner.run(mainClassName, cp, Seq(tempFile.toString(), version.versionString, flag), log).get
                  os.read(tempFile)
                } finally
                  os.remove(tempFile)
              }
            }

          cached(())
        }
      }
    )

lazy val library = (project in file("."))
  .dependsOn(launcher % Test)
  .settings(
    libraryDependencies ++= Seq(
      ("io.get-coursier" %% "coursier-core" % "2.1.24").cross(CrossVersion.for3Use2_13),
      "org.scalameta"    %% "munit"         % "1.3.0" % Test
    ),

    updateVersions       := runVersionUpdater(streams.value.log, dryRun = false),
    updateVersionsDryRun := runVersionUpdater(streams.value.log, dryRun = true),

    generate := Def.inputTaskDyn {
      val args             = spaceDelimited("<scala-version>").parsed
      val selectedVersions = selectScalaVersions(args)

      Def.task {
        val versionsString = selectedVersions.map(_.versionString).mkString(", ")
        streams.value.log.info(s"Getting compiler help texts for $versionsString...")
        val outputs        = Generator.getOutputs(selectedVersions)((launcher / runScalacFn).value)
        streams.value.log.info("Finished collecting compiler help texts.")

        writeGeneratedCode((Compile / sourceManaged).value, outputs)
      }
    }.evaluated,

    Compile / sourceGenerators += generate.toTask("").taskValue,

    Compile / packageSrc / mappings ++=
      generate.toTask("").value.pair(
        Path.relativeTo((Compile / sourceManaged).value) | Path.flat
      )
  )
