import _root_.io.github.nafg.scalacoptions.generator.Generator
import sbt.util.CacheImplicits._

import scala.concurrent.Await
import scala.concurrent.duration.Duration


ThisBuild / scalaVersion := "2.12.13"
ThisBuild / scalacOptions += "-deprecation"

ThisBuild / organization := "io.github.nafg.scalac-options"

val downloadScalaCompilerJars = taskKey[Unit]("Download all scala compiler jars")
downloadScalaCompilerJars := {
  streams.value.log.info("Downloading all scala compiler jars...")
  Await.result(Generator.prefetch, Duration.Inf)
  streams.value.log.info("Finished downloading all scala compiler jars...")
}

val getOutputs = taskKey[Generator.Outputs]("Run all scala compilers with help flags and collect the outputs")
getOutputs := {
  downloadScalaCompilerJars.value

  val cacheStore = streams.value.cacheStoreFactory.make("scalac-options-outputs")
  val runCached = Cache.cached[Unit, Generator.Outputs](cacheStore) { _ =>
    Await.result(Generator.getOutputs, Duration.Inf)
  }

  val outputs = runCached(())

  val dir = streams.value.cacheDirectory
  for ((version, pages) <- outputs; (flag, output) <- pages)
    IO.write(dir / version / (flag + ".txt"), output)

  outputs
}

val generate = taskKey[Seq[File]]("Generate code")
generate := {
  val outputs = getOutputs.value

  val dir = (Compile / sourceManaged).value / "io" / "github" / "nafg" / "scalacoptions"
  val result = Generator.parseAllOutputs(outputs)
  result.allContainers.map { c =>
    val file = dir / "options" / (c.name + ".scala")
    IO.write(file,
      s"""package io.github.nafg.scalacoptions.options
         |
         |${c.code}
         |""".stripMargin)
    file
  } :+ {
    val file = dir / "ScalacOptionsBase.scala"
    IO.write(file,
      s"""package io.github.nafg.scalacoptions
         |
         |trait ScalacOptionsBase {
         |  val versionMap = Map(
         |${result.versionMap.map { case (version, name) => s"""    "$version" -> options.$name""" }.mkString(",\n")}
         |  )
         |}
         |""".stripMargin
    )
    file
  }
}

Compile / sourceGenerators += generate

Compile / packageSrc / mappings ++=
  generate.value.pair(Path.relativeTo((Compile / sourceManaged).value) | Path.flat)
