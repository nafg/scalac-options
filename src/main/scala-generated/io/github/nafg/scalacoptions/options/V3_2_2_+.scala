// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_2_2_+ extends V3_2_1_+ {
  /**
   * Used in conjunction with captureChecking language import, debug info for captured references
   */
  override def YccDebug = List("-Ycc-debug")

  /**
   * Used in conjunction with captureChecking language import, suppress type abbreviations
   */
  override def YccNoAbbrev = List("-Ycc-no-abbrev")

  /**
   * Use experimental lightweight implementation of lazy vals
   */
  def YlightweightLazyVals = List("-Ylightweight-lazy-vals")
}
