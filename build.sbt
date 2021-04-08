import _root_.io.github.nafg.scalacoptions.generator.Generator
import sbt.util.CacheImplicits._

import scala.concurrent.Await
import scala.concurrent.duration.Duration


ThisBuild / scalaVersion := "2.12.13"
ThisBuild / scalacOptions += "-deprecation"

ThisBuild / organization := "io.github.nafg.scalac-options"

val generate = taskKey[Seq[File]]("Generate code")

generate := {
  val dir = (Compile / sourceManaged).value / "io" / "github" / "nafg" / "scalacoptions"
  val cacheStore = streams.value.cacheStoreFactory.make("scalac-options-result")
  val runCached = Cache.cached[Unit, Generator.Result](cacheStore) { _ =>
    Await.result(Generator.run, Duration.Inf)
  }
  val result = runCached(())
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
