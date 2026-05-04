package io.github.nafg.scalacoptions.runner

import coursier.core.{Module, ModuleName, Organization}
import coursier.{Dependency, Fetch}

import java.io.File
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future, blocking}


final case class RunResult(exitCode: Int, stdout: String, stderr: String)

object Scalac {
  private def epochOf(scalaVersion: String): Int =
    scalaVersion.takeWhile(c => c.isDigit).toInt

  def dependency(scalaVersion: String): Dependency = {
    val moduleName =
      if (epochOf(scalaVersion) < 3) "scala-compiler"
      else "scala3-compiler_3"
    Dependency(
      Module(Organization("org.scala-lang"), ModuleName(moduleName), Map.empty),
      scalaVersion
    )
  }

  def mainClass(scalaVersion: String): String =
    if (epochOf(scalaVersion) < 3) "scala.tools.nsc.Main"
    else "dotty.tools.dotc.Main"

  def fetchClasspath(scalaVersion: String)(implicit ec: ExecutionContext): Future[Seq[File]] =
    Fetch().addDependencies(dependency(scalaVersion)).future()

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
