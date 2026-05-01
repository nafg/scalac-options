// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_6_3_+ extends V3_6_2_+ {
  /**
   * Capture trace of compilation in JSON Chrome Trace format to the specified file. This option requires -Yprofile-enabled. The output file can be visualized using https://ui.perfetto.dev/.
   */
  def YprofileTrace = List("-Yprofile-trace")
}
