package io.github.nafg.scalacoptions.runner

import coursier.core.{Module, ModuleName, Organization}
import coursier.{Dependency, Fetch}

import java.io.File
import scala.collection.concurrent.TrieMap
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future, blocking}


final case class RunResult(exitCode: Int, stdout: String, stderr: String)

object Scalac {
  private def epochOf(scalaVersion: String): Int =
    scalaVersion.takeWhile(c => c.isDigit).toInt

  def dependency(scalaVersion: String): Dependency = {
    val moduleName =
      if (epochOf(scalaVersion) < 3) "scala-compiler"
      else if (scalaVersion.contains("-")) s"scala3-compiler_$scalaVersion"
      else "scala3-compiler_3"
    Dependency(
      Module(Organization("org.scala-lang"), ModuleName(moduleName), Map.empty),
      scalaVersion
    )
  }

  def mainClass(scalaVersion: String): String =
    if (epochOf(scalaVersion) < 3) "scala.tools.nsc.Main"
    else "dotty.tools.dotc.Main"

  private val classpathCache = TrieMap.empty[String, Seq[File]]

  def fetchClasspath(scalaVersion: String)(implicit ec: ExecutionContext): Future[Seq[File]] =
    classpathCache.get(scalaVersion) match {
      case Some(files) => Future.successful(files)
      case None        =>
        Fetch().addDependencies(dependency(scalaVersion)).future().map { files =>
          classpathCache.put(scalaVersion, files)
          files
        }
    }

  def fetchAllClasspaths(scalaVersions: Seq[String])(implicit ec: ExecutionContext): Future[Seq[File]] =
    Fetch().addDependencies(scalaVersions.map(dependency): _*).future()

  def run(scalaVersion: String, args: String*)(implicit ec: ExecutionContext): RunResult = {
    val files = Await.result(fetchClasspath(scalaVersion), Duration.Inf)
    val cmd   = Seq("java", "-cp", files.mkString(File.pathSeparator), mainClass(scalaVersion)) ++ args
    val res   = blocking {
      os.proc(cmd).call(
        stdout = os.Pipe,
        stderr = os.Pipe,
        env = Map("COLUMNS" -> "20000"),
        check = false
      )
    }
    RunResult(exitCode = res.exitCode, stdout = res.out.text(), stderr = res.err.text())
  }
}
