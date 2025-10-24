package io.github.nafg.scalacoptions

import scala.collection.immutable.SortedMap


object ScalacOptions extends ScalacOptionsBase {
  import VersionOrdering.ordering
  
  lazy val sortedVersionMap: SortedMap[String, options.Common] = 
    SortedMap(versionMap.toSeq: _*)

  def apply(versionString: String): options.Common = {
    val matchingVersions = sortedVersionMap.filter { case (k, _) => ordering.lteq(k, versionString) }
    if (matchingVersions.isEmpty) sortedVersionMap.head._2
    else matchingVersions.last._2
  }

  def allMatching(versionString: String)(partialFunctions: PartialFunction[options.Common, List[String]]*)
    : List[String] = {
    val opts = apply(versionString)
    partialFunctions.toList.flatMap { pf =>
      pf.applyOrElse[options.Common, List[String]](opts, _ => Nil)
    }
  }

  def all(versionString: String)(optionsFunctions: VersionOptionsFunction*) =
    allMatching(versionString)(optionsFunctions.map(_.toPartialFunction): _*)
}
