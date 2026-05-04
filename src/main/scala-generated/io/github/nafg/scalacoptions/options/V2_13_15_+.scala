// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_15_+ extends V2_13_14_+ {
  /**
   * Silence warnings. (-Wconf:any:s) [false]
   */
  override def nowarn = List("-nowarn")

  /**
   * Override location of Java system modules
   */
  def system(path: String) = List("-system", path)

  /**
   * Target platform for object files. ([8],9,10,11,12,13,14,15,16,17,18,19,20,21)
   */
  def target(target: String) = List("-target:" + target)

  /**
   * Debug cyclic reference error. [false]
   */
  def Vcyclic = List("-Vcyclic")

  /**
   * [:phases]             Print compiler statistics for specific phases (implies `-Ycollect-statistics`) <phases> (default: parser,typer,patmat,erasure,cleanup,jvm)
   */
  override def Vstatistics = List("-Vstatistics")

  /**
   * Warn when a standard interpolator uses toString. [false]
   */
  def WtostringInterpolated = List("-Wtostring-interpolated")

  /**
   * Generate forwarder methods in classes inheriting concrete methods from traits. Default: `true`, `help` to list choices.
   */
  override def XmixinForceForwarders(mode: String) = List("-Xmixin-force-forwarders:" + mode)

  /**
   * Collect cold statistics (quietly, unless `-Vstatistics` is set) [false]
   */
  def YcollectStatistics = List("-Ycollect-statistics")
}
