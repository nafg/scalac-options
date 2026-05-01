// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_4_1_+ extends V3_4_0_+ {
  /**
   * Explain cyclic reference errors in more detail.
   */
  def explainCyclic = List("-explain-cyclic")

  /**
   * Print the stack trace when a cyclic reference error occurs.
   */
  def YdebugCyclic = List("-Ydebug-cyclic")
}
