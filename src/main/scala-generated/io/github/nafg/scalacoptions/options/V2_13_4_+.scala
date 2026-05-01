// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_4_+ extends V2_13_3_+ {
  /**
   * Increase the quantity of debugging output when unpickling tasty. [false]
   */
  def VdebugTasty = List("-Vdebug-tasty")

  /**
   * Pattern match on an unsealed class without a catch-all.
   */
  def XlintStrictUnsealedPatmat = List("-Xlint:strict-unsealed-patmat")

  /**
   * Disable strict exhaustivity analysis, which assumes guards are false and refutable extractors don't match [false]
   */
  def XnonStrictPatmatAnalysis = List("-Xnon-strict-patmat-analysis")

  /**
   * Use range positions for syntax trees. [true]
   */
  override def Yrangepos = List("-Yrangepos")

  /**
   * Disable support for reading annotations from TASTy, this will prevent safety features such as pattern match exhaustivity and reachability analysis. [false]
   */
  def YtastyNoAnnotations = List("-Ytasty-no-annotations")

  /**
   * Enable support for reading Scala 3's TASTy files, allowing consumption of libraries compiled with Scala 3 (provided they don't use any Scala 3 only features). [false]
   */
  def YtastyReader = List("-Ytasty-reader")
}
