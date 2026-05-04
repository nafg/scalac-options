// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_2_+ extends V2_12_1_+ {
  /**
   * Maximum errors to print
   */
  def Xmaxerrs(n: String) = List("-Xmaxerrs", n)

  /**
   * Maximum warnings to print
   */
  def Xmaxwarns(n: String) = List("-Xmaxwarns", n)

  def YwarnExtraImplicit = List("-Ywarn-extra-implicit")

  /**
   * Enable or disable specific `unused' warnings: `_' for all, `-Ywarn-unused:help' to list choices.
   */
  def YwarnUnused(`_,warning,-warning`: String) = List("-Ywarn-unused:" + `_,warning,-warning`)
}
