// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_3_+ extends V2_12_2_+ {
  /**
   * Patterns for classfile names from which to allow inlining, `help` for details.
   */
  def optInlineFrom(patterns: String) = List("-opt-inline-from:" + patterns)

  def XprintArgs = List("-Xprint-args")

  /**
   * Treat compiler input as Scala source for the specified version, see scala/bug#8126.
   */
  override def Xsource(version: String) = List("-Xsource:" + version)

  def YprofileDestination = List("-Yprofile-destination")

  def YprofileEnabled = List("-Yprofile-enabled")

  /**
   * [:phases]         Enable profiling for a phase using an external tool hook. Generally only useful for a single phase <phases> (default: typer)
   */
  def YprofileExternalTool = List("-Yprofile-external-tool")

  def YprofileRunGc = List("-Yprofile-run-gc")
}
