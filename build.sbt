import _root_.io.github.nafg.scalacoptions.generator.Generator
import sbt.util.CacheImplicits._

import scala.concurrent.Await
import scala.concurrent.duration.Duration


scalaVersion := "2.12.13"
scalacOptions += "-deprecation"

val generate = taskKey[Seq[File]]("Generate code")

generate := {
  val dir = (Compile / sourceManaged).value / "io" / "github" / "nafg" / "scalacoptions"
  val cacheStore = streams.value.cacheStoreFactory.make("scalac-options-result")
  val runCached = Cache.cached[Unit, Generator.Result](cacheStore) { _ =>
    Await.result(Generator.run, Duration.Inf)
  }
  val result = runCached(())
  result.allContainers.map { c =>
    val file = dir / (c.name + ".scala")
    IO.write(file,
      s"""package io.github.nafg.scalacoptions
         |
         |${c.code}
         |""".stripMargin)
    file
  } :+ {
    val file = dir / "ScalacOptions.scala"
    IO.write(file,
      s"""package io.github.nafg.scalacoptions
         |
         |object ScalacOptions {
         |  val map = Map(
         |${result.versionMap.map { case (version, name) => s"""    "$version" -> $name""" }.mkString(",\n")}
         |  )
         |  def apply(versionString: String) = map(versionString)
         |}
         |""".stripMargin
    )
    file
  }
}

Compile / sourceGenerators += generate

Compile / packageSrc / mappings ++=
  generate.value.pair(Path.relativeTo((Compile / sourceManaged).value) | Path.flat)
