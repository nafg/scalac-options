package io.github.nafg.scalacoptions

object ScalacOptions extends ScalacOptionsBase {
  def apply(versionString: String): options.Common = versionMap(versionString)

  def allMatching(versionString: String)
                 (partialFunctions: PartialFunction[options.Common, List[String]]*): List[String] = {
    val opts = apply(versionString)
    partialFunctions.toList.flatMap { pf =>
      pf.applyOrElse[options.Common, List[String]](opts, _ => Nil)
    }
  }

  def all(versionString: String)(optionsFunctions: VersionOptionsFunction*) =
    allMatching(versionString)(optionsFunctions.map(_.toPartialFunction): _*)
}
