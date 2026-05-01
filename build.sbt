import scala.concurrent.Await
import scala.concurrent.duration.Duration


ThisBuild / crossScalaVersions := Seq("2.12.21", "2.13.18", "3.3.7")
ThisBuild / scalaVersion := "2.12.21"
ThisBuild / scalacOptions += "-deprecation"

ThisBuild / organization := "io.github.nafg.scalac-options"

ThisBuild / versionScheme := Some("early-semver")

// sbt-git defaults to JGit for read operations, but JGit 5.13.x doesn't support git worktrees
// (NoWorkTreeException on load). Shell out to the `git` CLI instead. See sbt/sbt#2323.
useReadableConsoleGit

val downloadScalaCompilerJars =
  taskKey[Unit]("Download all scala compiler jars")
downloadScalaCompilerJars := {
  streams.value.log.info("Downloading all scala compiler jars...")
  Await.result(Generator.prefetch, Duration.Inf)
  streams.value.log.info("Finished downloading all scala compiler jars...")
}

def helpTextFile(baseDir: File, version: Versions.Minor, flag: String): File =
  baseDir / "help-text" / version.versionString / (flag + ".txt")

def expectedHelpTextPairs: Seq[(Versions.Minor, String)] =
  Versions.versions.flatMap(_.allMinors).flatMap(v => v.helpFlags.map(v -> _))

def readAllHelpText(baseDir: File): Generator.Outputs =
  expectedHelpTextPairs.groupBy(_._1).toSeq.sortBy(_._1).map { case (version, pairs) =>
    version -> pairs.map(_._2).map(flag =>
      flag -> IO.read(helpTextFile(baseDir, version, flag))
    )
  }

val missingHelpTextPairs = taskKey[Seq[(Versions.Minor, String)]](
  "(version, flag) help-text entries not yet present on disk"
)
missingHelpTextPairs := {
  val baseDir = baseDirectory.value
  expectedHelpTextPairs.filterNot { case (v, f) =>
    helpTextFile(baseDir, v, f).exists
  }
}

val getOutputs = taskKey[Generator.Outputs](
  "Read cached scalac help-text outputs from help-text/, fetching any missing entries"
)
getOutputs := {
  if (missingHelpTextPairs.value.isEmpty)
    readAllHelpText(baseDirectory.value)
  else {
    val baseDir = baseDirectory.value
    val log = streams.value.log
    val missing = missingHelpTextPairs.value
    log.info(s"Fetching ${missing.size} missing help-text entries...")
    val needed: Map[Versions.Minor, Seq[String]] =
      missing.groupBy(_._1).map { case (v, pairs) => v -> pairs.map(_._2) }
    val fetched = Await.result(Generator.getOutputs(needed), Duration.Inf)
    for ((version, pages) <- fetched; (flag, output) <- pages)
      IO.write(helpTextFile(baseDir, version, flag), output)
    readAllHelpText(baseDir)
  }
}

val refreshOutputs = taskKey[Generator.Outputs](
  "Re-fetch all scalac help-text outputs from network and overwrite help-text/"
)
refreshOutputs := {
  downloadScalaCompilerJars.value
  val baseDir = baseDirectory.value
  val outputs = Await.result(Generator.getOutputs, Duration.Inf)
  for ((version, pages) <- outputs; (flag, output) <- pages)
    IO.write(helpTextFile(baseDir, version, flag), output)
  outputs
}

def generatedFileHeader(target: String): String =
  s"// AUTO-GENERATED — edit $target or generator code, then run `sbt generate`\n\n"

val generate = taskKey[Seq[File]]("Generate code under src/main/scala-generated/")
generate := {
  val outputs = getOutputs.value

  val dir =
    baseDirectory.value / "src" / "main" / "scala-generated" /
      "io" / "github" / "nafg" / "scalacoptions"
  val optionsDir = dir / "options"
  IO.createDirectory(optionsDir)

  val result = Generator.parseAllOutputs(outputs)

  val containerFiles = result.allContainers.map { c =>
    val file = optionsDir / (c.name + ".scala")
    IO.write(
      file,
      generatedFileHeader("versions.yaml") +
        s"""package io.github.nafg.scalacoptions.options
           |
           |${c.code}
           |""".stripMargin
    )
    file
  }
  val baseFile = dir / "ScalacOptionsBase.scala"
  IO.write(
    baseFile,
    generatedFileHeader("versions.yaml") +
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

  val produced = containerFiles.toSet + baseFile
  val existingOptions = Option(optionsDir.listFiles).fold(Seq.empty[File])(_.toSeq)
  val existingTop = Option(dir.listFiles).fold(Seq.empty[File])(_.toSeq.filter(_.isFile))
  (existingOptions ++ existingTop).filterNot(produced.contains).foreach(IO.delete)

  containerFiles :+ baseFile
}

Compile / sourceGenerators += generate

Compile / unmanagedSourceDirectories += baseDirectory.value / "src" / "main" / "scala-generated"

libraryDependencies += ("io.get-coursier" %% "coursier-core" % "2.1.24")
  .cross(CrossVersion.for3Use2_13)
