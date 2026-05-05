package io.github.nafg.scalacoptions.launcher

import java.io.File

import coursier.core.{Module, ModuleName, Organization}
import coursier.{Dependency, Fetch}


object Scalac {
  def main(args: Array[String]): Unit = args match {
    case Array(outPath, scalaVersion, scalacArgs @ _*) =>

      val (artifact, mainClass) =
        if (scalaVersion.startsWith("2."))
          "scala-compiler" -> "scala.tools.nsc.Main"
        else if (scalaVersion.contains("-"))
          s"scala3-compiler_$scalaVersion" -> "dotty.tools.dotc.Main"
        else
          "scala3-compiler_3"              -> "dotty.tools.dotc.Main"

      val dependency = Dependency(Module(Organization("org.scala-lang"), ModuleName(artifact), Map.empty), scalaVersion)

      os.proc(
        os.Path(sys.props("java.home")) / "bin" / "java",
        "-cp",
        Fetch().addDependencies(dependency).run().mkString(File.pathSeparator),
        mainClass,
        scalacArgs
      ).call(
        stdout = os.Path(outPath),
        mergeErrIntoOut = true,
        env = Map("COLUMNS" -> "20000"),
        check = false
      )

    case _ =>
      sys.error(s"Expected args: <output path> <scala version> <scalac arg>*")
  }
}
