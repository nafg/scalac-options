import sbt.util.CacheImplicits._
import sbt.util.CacheStore
import sbt.complete.DefaultParsers.spaceDelimited

import _root_.io.github.nafg.scalacoptions.{ScalacOptions, options}

import scala.concurrent.Await
import scala.concurrent.duration.*


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

def runVersionUpdater(log: sbt.util.Logger, dryRun: Boolean): Unit = {
  import scala.concurrent.ExecutionContext.Implicits.global
  log.info(Await.result(new VersionUpdater().run(dryRun = dryRun), 5.minutes))
}

val updateVersions =
  taskKey[Unit]("Update versions.yaml with latest Scala patch releases from Maven Central")
updateVersions := runVersionUpdater(streams.value.log, dryRun = false)

val updateVersionsDryRun =
  taskKey[Unit]("Check for new Scala versions without modifying versions.yaml")
updateVersionsDryRun := runVersionUpdater(streams.value.log, dryRun = true)

val downloadScalaCompilerJars =
  taskKey[Unit]("Download all scala compiler jars")
downloadScalaCompilerJars := {
  streams.value.log.info("Downloading all scala compiler jars...")
  Await.result(Generator.prefetch, Duration.Inf)
  streams.value.log.info("Finished downloading all scala compiler jars...")
}

val getOutputs = taskKey[Generator.Outputs](
  "Run all scala compilers with help flags and collect the outputs"
)

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

def collectOutputs(selectedVersions: Seq[Versions.Minor], cacheStore: CacheStore): Generator.Outputs = {
  val runCached = Cache.cached[Seq[String], Generator.Outputs](cacheStore) { _ =>
    Await.result(Generator.getOutputs(selectedVersions), Duration.Inf)
  }
  runCached(selectedVersions.map(_.versionString))
}

def writeCachedOutputs(cacheDirectory: File, outputs: Generator.Outputs): Unit =
  for ((version, pages) <- outputs; (flag, output) <- pages)
    IO.write(cacheDirectory / version.versionString / (flag + ".txt"), output)

getOutputs := {
  val selectedVersions = selectScalaVersions(Nil)
  downloadScalaCompilerJars.value

  val cacheStore =
    streams.value.cacheStoreFactory.make("scalac-options-outputs")
  val outputs    = collectOutputs(selectedVersions, cacheStore)

  writeCachedOutputs(streams.value.cacheDirectory, outputs)

  outputs
}

val generate = inputKey[Seq[File]]("Generate code. Optionally pass one or more Scala versions.")
generate := Def.inputTaskDyn {
  val args             = spaceDelimited("<scala-version>").parsed
  val selectedVersions = selectScalaVersions(args)

  if (args.isEmpty)
    Def.task {
      writeGeneratedCode((Compile / sourceManaged).value, getOutputs.value)
    }
  else
    Def.task {
      streams.value.log.info(
        s"Downloading scala compiler jars for ${selectedVersions.map(_.versionString).mkString(", ")}..."
      )
      Await.result(Generator.prefetch(selectedVersions), Duration.Inf)
      streams.value.log.info("Finished downloading scala compiler jars.")

      val cacheStore = streams.value.cacheStoreFactory.make("scalac-options-outputs")
      val outputs    = collectOutputs(selectedVersions, cacheStore)
      writeCachedOutputs(streams.value.cacheDirectory, outputs)
      writeGeneratedCode((Compile / sourceManaged).value, outputs)
    }
}.evaluated

Compile / sourceGenerators += generate.toTask("").taskValue

Compile / packageSrc / mappings ++=
  generate.toTask("").value.pair(
    Path.relativeTo((Compile / sourceManaged).value) | Path.flat
  )

libraryDependencies += ("io.get-coursier" %% "coursier-core" % "2.1.24")
  .cross(CrossVersion.for3Use2_13)

libraryDependencies += "org.scalameta" %% "munit" % "1.1.1" % Test

lazy val runner = (project in file("runner"))
  .settings(
    name                := "scalac-options-runner",
    publish / skip      := true,
    libraryDependencies ++= Seq(
      ("io.get-coursier" %% "coursier" % "2.1.24").cross(CrossVersion.for3Use2_13),
      "com.lihaoyi"      %% "os-lib"   % "0.11.8"
    )
  )

lazy val library = (project in file("."))
  .dependsOn(runner % Test)
