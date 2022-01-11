package io.github.nafg.scalacoptions

import coursier.core.Version

import scala.collection.immutable.SortedMap


object ScalacOptions extends ScalacOptionsBase {
  lazy val sortedVersionMap = SortedMap(versionMap.toSeq.map { case (k, v) =>
    new Version(k) -> v
  }: _*)

  def apply(versionString: String): options.Common =
    sortedVersionMap.to(new Version(versionString)).last._2

  def allMatching(
    versionString: String
  )(partialFunctions: PartialFunction[options.Common, List[String]]*): List[String] = {
    val opts = apply(versionString)
    partialFunctions.toList.flatMap { pf =>
      pf.applyOrElse[options.Common, List[String]](opts, _ => Nil)
    }
  }

  def all(versionString: String)(optionsFunctions: VersionOptionsFunction*) =
    allMatching(versionString)(optionsFunctions.map(_.toPartialFunction): _*)
}
