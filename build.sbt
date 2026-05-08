import java.util.concurrent.atomic.AtomicInteger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*
import scala.concurrent.{Await, Future, blocking}

import _root_.io.github.nafg.scalacoptions.{ScalacOptions, options}

import sbt.complete.DefaultParsers.spaceDelimited


ThisBuild / crossScalaVersions := Seq("2.12.21", "2.13.18", "3.3.7")
ThisBuild / scalaVersion       := "2.12.21"
ThisBuild / scalacOptions ++=
  ScalacOptions.all(scalaVersion.value)((opts: options.Common) => opts.deprecation ++ opts.feature)
ThisBuild / scalacOptions ++=
  ScalacOptions.all(scalaVersion.value)(
    (opts: options.V2_12_17_+) => opts.release("8"),
    (opts: options.V2_13_9_+) => opts.release("8"),
    (opts: options.V3_2_+) => opts.javaOutputVersion("8")
  )

ThisBuild / organization := "io.github.nafg.scalac-options"

ThisBuild / versionScheme := Some("early-semver")

// sbt-git defaults to JGit for read operations, but JGit 5.13.x doesn't support git worktrees
// (NoWorkTreeException on load). Shell out to the `git` CLI instead. See sbt/sbt#2323.
useReadableConsoleGit

def runVersionUpdater(log: sbt.util.Logger, dryRun: Boolean): Unit =
  log.info(Await.result(new VersionUpdater().run(dryRun = dryRun), 5.minutes))

val scalacHelpDir        = settingKey[os.Path]("Directory holding committed scalac help-text files")
val updateVersions       = taskKey[Unit]("Update versions.yaml with latest Scala releases from Maven Central")
val updateVersionsDryRun = taskKey[Unit]("Check for new Scala versions without modifying versions.yaml")
val regenerateScalacHelp = taskKey[Unit]("Regenerate files in scalac-help/")
val generate             = inputKey[Seq[File]]("Generate code. Optionally pass one or more Scala versions.")

ThisBuild / scalacHelpDir := os.Path((ThisBuild / baseDirectory).value) / "scalac-help"

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
      publish / skip       := true,
      libraryDependencies ++= Seq(
        ("io.get-coursier" %% "coursier" % "2.1.24").cross(CrossVersion.for3Use2_13),
        "com.lihaoyi"      %% "os-lib"   % "0.11.8"
      ),
      regenerateScalacHelp := {
        val log           = streams.value.log
        val cp            = (Compile / fullClasspath).value.files
        val scalaRunner   = (Compile / runner).value
        val mainClassName = (Compile / mainClass).value.getOrElse(sys.error("No main class in launcher"))
        val helpDir       = scalacHelpDir.value

        os.remove.all(helpDir)

        val all =
          for {
            version <- Versions.loadVersions().flatMap(_.allMinors)
            flag    <- version.helpFlags
          } yield (version, flag, Generator.scalacHelpFile(helpDir, version, flag))

        val completed = new AtomicInteger(0)
        log.info(s"Generating ${all.size} scalac-help files...")
        Await.result(
          Future.traverse(all) { case (version, flag, outFile) =>
            Future {
              blocking {
                scalaRunner.run(mainClassName, cp, Seq(outFile.toString(), version.versionString, flag), log).get
              }
              log.info(s"[${completed.incrementAndGet()}/${all.size}] $version $flag")
            }
          },
          Duration.Inf
        )
      }
    )

lazy val library = (project in file("."))
  .dependsOn(launcher % Test)
  .settings(
    name               := "scalac-options",
    libraryDependencies ++= Seq(
      ("io.get-coursier" %% "coursier-core" % "2.1.24").cross(CrossVersion.for3Use2_13),
      "org.scalameta"    %% "munit"         % "1.3.0" % Test
    ),
    Test / logBuffered := false,

    updateVersions       := runVersionUpdater(streams.value.log, dryRun = false),
    updateVersionsDryRun := runVersionUpdater(streams.value.log, dryRun = true),

    generate := Def.inputTaskDyn {
      val args             = spaceDelimited("<scala-version>").parsed
      val selectedVersions = selectScalaVersions(args)

      Def.task {
        val versionsString = selectedVersions.map(_.versionString).mkString(", ")
        streams.value.log.info(s"Reading compiler help texts for $versionsString...")
        val outputs        = Generator.getOutputs(selectedVersions, scalacHelpDir.value)
        writeGeneratedCode((Compile / sourceManaged).value, outputs)
      }
    }.evaluated,

    Compile / sourceGenerators += generate.toTask("").taskValue,

    Compile / packageSrc / mappings ++=
      generate.toTask("").value.pair(
        Path.relativeTo((Compile / sourceManaged).value) | Path.flat
      )
  )
