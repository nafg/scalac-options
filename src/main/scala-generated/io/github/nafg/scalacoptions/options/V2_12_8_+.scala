// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_8_+ extends V2_12_7_+ {
  /**
   * <file>              Print all compiler arguments to the specified location. Use - to echo to the reporter.
   */
  override def XprintArgs = List("-Xprint-args")

  def YmacroGlobalFreshNames = List("-Ymacro-global-fresh-names")

  /**
   * <file>             Profiling output - specify a file or `-` for console.
   */
  override def YprofileDestination = List("-Yprofile-destination")

  /**
   * <file>                   Capture trace of compilation in Chrome Trace format
   */
  def YprofileTrace = List("-Yprofile-trace")
}
