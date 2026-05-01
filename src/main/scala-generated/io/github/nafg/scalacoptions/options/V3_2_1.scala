// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_2_1 extends V3_2_1_+ {
  /**
   * Check captured references (warning: extremely experimental and unstable)
   */
  def Ycc = List("-Ycc")

  /**
   * Used in conjunction with -Ycc, debug info for captured references
   */
  override def YccDebug = List("-Ycc-debug")

  /**
   * Used in conjunction with -Ycc, suppress type abbreviations
   */
  override def YccNoAbbrev = List("-Ycc-no-abbrev")
}

object V3_2_1 extends V3_2_1
