package io.github.nafg.scalacoptions.launcher

import java.io.File

import scala.collection.concurrent.TrieMap

import coursier.core.{Module, ModuleName, Organization}
import coursier.{Dependency, Fetch}


object Scalac {
  private val classpathCache = TrieMap.empty[String, String]

  def run(scalaVersion: String, stdout: os.ProcessOutput, scalacArgs: String*): Unit = {
    val (artifact, mainClass) =
      if (scalaVersion.startsWith("2."))
        "scala-compiler" -> "scala.tools.nsc.Main"
      else if (scalaVersion.contains("-"))
        s"scala3-compiler_$scalaVersion" -> "dotty.tools.dotc.Main"
      else
        "scala3-compiler_3"              -> "dotty.tools.dotc.Main"

    val classpath = classpathCache.getOrElseUpdate(
      scalaVersion, {
        val dep = Dependency(Module(Organization("org.scala-lang"), ModuleName(artifact), Map.empty), scalaVersion)
        Fetch().addDependencies(dep).run().mkString(File.pathSeparator)
      }
    )

    os.proc(
      os.Path(sys.props("java.home")) / "bin" / "java",
      "-cp",
      classpath,
      mainClass,
      scalacArgs
    ).call(
      stdout = stdout,
      mergeErrIntoOut = true,
      env = Map("COLUMNS" -> "20000"),
      check = false
    )
  }

  def main(args: Array[String]): Unit = args match {
    case Array(outPath, scalaVersion, scalacArgs @ _*) =>
      val path = os.Path(outPath)
      os.makeDir.all(path / os.up)
      run(scalaVersion, path, scalacArgs: _*)
    case _                                             =>
      sys.error(s"Expected args: <output path> <scala version> <scalac arg>*")
  }
}
