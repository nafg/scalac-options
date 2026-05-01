// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_7_2_+ extends V3_7_1_+ {
  /**
   * Allows to rewrite the implicit keywords to their scala-3 given counterparts. Does not adjust imports. Use in conjunction with --rewrite.
   */
  def YimplicitToGiven = List("-Yimplicit-to-given")

  /**
   * Do not patch stdlib files (temporary and only to be used for the stdlib migration)
   */
  def YnoStdlibPatches = List("-Yno-stdlib-patches")
}
