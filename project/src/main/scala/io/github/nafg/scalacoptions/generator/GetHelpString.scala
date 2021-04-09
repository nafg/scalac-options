package io.github.nafg.scalacoptions.generator

import coursier.core.{Module, ModuleName, Organization}
import coursier.{Dependency, Fetch}

import java.io.File
import scala.concurrent.{ExecutionContext, Future, blocking}


object GetHelpString {
  def dependency(version: String) =
    Dependency(
      Module(
        Organization("org.scala-lang"),
        ModuleName(if (version.split('.').head.toInt < 3) "scala-compiler" else s"scala3-compiler_$version"),
        Map.empty
      ),
      version
    )

  trait Runner {
    def apply(flag: String): String
  }

  def runner(dependency: Dependency, mainClass: String)(implicit ec: ExecutionContext): Future[Runner] =
    Fetch()
      .addDependencies(dependency)
      .future()
      .map { files =>
        new Runner {
          override def apply(flag: String) = {
            val commandResult =
              blocking {
                os.proc("java", "-cp", files.mkString(File.pathSeparator), mainClass, flag)
                  .call(stderr = os.Pipe)
              }
            geny.ByteData.Chunks(commandResult.chunks.map(_.merge)).trim()
          }
        }
      }

  def runner(version: String)(implicit ec: ExecutionContext): Future[Runner] =
    runner(
      dependency = dependency(version),
      mainClass = if (version.split('.').head.toInt < 3) "scala.tools.nsc.Main" else "dotty.tools.dotc.Main"
    )

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

  def fetchAll(versions: Seq[String]) =
    Fetch()
      .addDependencies(versions.map(dependency): _*)
      .future()
}
