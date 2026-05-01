// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_5_+ extends V2_13_4_+ {
  /**
   * Enable -Wunused:explicits,implicits,synthetics.
   */
  override def WunusedParams = List("-Wunused:params")

  /**
   * Warn if a synthetic implicit parameter (context bound) is unused.
   */
  def WunusedSynthetics = List("-Wunused:synthetics")
}
