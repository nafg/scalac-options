// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_17_+ extends V2_13_16_+ {
  /**
   * Warn on definitions with an inferred structural type.
   */
  def XlintInferStructural = List("-Xlint:infer-structural")

  /**
   * Overload differs only in an implicit parameter.
   */
  def XlintOverload = List("-Xlint:overload")

  /**
   * Dubious usage of member of `Any` or `AnyRef`.
   */
  override def XlintUniversalMethods = List("-Xlint:universal-methods")
}
