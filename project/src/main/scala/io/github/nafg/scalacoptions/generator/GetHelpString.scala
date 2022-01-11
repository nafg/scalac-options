package io.github.nafg.scalacoptions.generator

import coursier.core.{Module, ModuleName, Organization}
import coursier.{Dependency, Fetch}

import java.io.File
import scala.concurrent.{ExecutionContext, Future, blocking}


object GetHelpString {
  def dependency(version: Versions.Minor) =
    Dependency(
      Module(
        Organization("org.scala-lang"),
        ModuleName(
          version match {
            case Versions.Minor(2, _, _, _, _)       => "scala-compiler"
            case Versions.Minor(3, _, _, Some(_), _) => s"scala3-compiler_${version.versionString}"
            case Versions.Minor(3, _, _, None, _)    => "scala3-compiler_3"
          }
        ),
        Map.empty
      ),
      version.versionString
    )

  trait Runner {
    def apply(flag: String): String
  }

  def runner(
    dependency: Dependency,
    mainClass: String
  )(implicit
    ec: ExecutionContext): Future[Runner] =
    Fetch()
      .addDependencies(dependency)
      .future()
      .map { files =>
        new Runner {
          override def apply(flag: String) = {
            val commandResult =
              blocking {
                os.proc(
                  "java",
                  "-cp",
                  files.mkString(File.pathSeparator),
                  mainClass,
                  flag
                ).call(stderr = os.Pipe)
              }
            geny.ByteData.Chunks(commandResult.chunks.map(_.merge)).trim()
          }
        }
      }

  def runner(version: Versions.Minor)(implicit ec: ExecutionContext): Future[Runner] =
    runner(
      dependency = dependency(version),
      mainClass =
        if (version.epoch < 3) "scala.tools.nsc.Main"
        else "dotty.tools.dotc.Main"
    )

  def fetchAll(versions: Seq[Versions.Epoch]) =
    Fetch()
      .addDependencies(versions.flatMap(_.allMinors).map(dependency): _*)
      .future()
}
