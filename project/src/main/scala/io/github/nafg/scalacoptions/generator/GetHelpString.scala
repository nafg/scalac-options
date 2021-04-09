package io.github.nafg.scalacoptions.generator

import coursier.core.{Module, ModuleName, Organization}
import coursier.{Dependency, Fetch}

import java.io.File
import scala.concurrent.{ExecutionContext, Future, blocking}


object GetHelpString {
  def run(dependency: Dependency, mainClass: String, flag: String)(implicit ec: ExecutionContext): Future[String] =
    Fetch()
      .addDependencies(dependency)
      .future()
      .map { files =>
        val commandResult =
          blocking {
            os.proc("java", "-cp", files.mkString(File.pathSeparator), mainClass, flag)
              .call(stderr = os.Pipe)
          }
        geny.ByteData.Chunks(commandResult.chunks.map(_.merge)).trim()
      }

  def run(version: String, flag: String)(implicit ec: ExecutionContext): Future[String] = {
    run(
      dependency =
        Dependency(
          Module(
            Organization("org.scala-lang"),
            ModuleName(if (version.split('.').head.toInt < 3) "scala-compiler" else s"scala3-compiler_$version"),
            Map.empty
          ),
          version
        ),
      mainClass = if (version.split('.').head.toInt < 3) "scala.tools.nsc.Main" else "dotty.tools.dotc.Main",
      flag = flag
    )
  }

  val helpFlags = Set("-help", "-X", "-Y")
  val helpFlags213 = helpFlags + "-V" + "-W"

  val versionsAndHelpFlags =
    (0 to 12).map(p => ("2.11." + p) -> helpFlags) ++
      (0 to 13).map(p => ("2.12." + p) -> helpFlags) ++
      (0 to 5).map(p => ("2.13." + p) -> helpFlags213) ++
      List("-RC2").map(p => ("3.0.0" + p) -> helpFlags)

  val versionsAndHelpFlagsForTesting = List(
    "2.11.0" -> helpFlags,
    "2.11.12" -> helpFlags,
    "2.12.0" -> helpFlags,
    "2.12.13" -> helpFlags,
    "2.13.0" -> helpFlags213,
    "2.13.5" -> helpFlags213,
    "3.0.0-RC2" -> helpFlags
  )
}
