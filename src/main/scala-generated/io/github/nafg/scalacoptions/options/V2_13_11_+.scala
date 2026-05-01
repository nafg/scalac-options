// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_11_+ extends V2_13_10_+ {
  /**
   * [:phases]                  Print out program after (or ~phase for before and after) <phases> (default: typer)
   */
  def Vprint = List("-Vprint")

  /**
   * -Wvalue-discard for adapted arguments.
   */
  def XlintArgDiscard = List("-Xlint:arg-discard")

  /**
   * A method reference was eta-expanded but the expected SAM type was not annotated @FunctionalInterface.
   */
  override def XlintEtaSam = List("-Xlint:eta-sam")

  /**
   * Warn when an integer division is converted (widened) to floating point: `(someInt / 2): Double`.
   */
  def XlintIntDivToFloat = List("-Xlint:int-div-to-float")

  /**
   * Dubious usages, such as `42.isNaN`.
   */
  def XlintNumericMethods = List("-Xlint:numeric-methods")
}
