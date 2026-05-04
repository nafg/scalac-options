// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_20_+ extends V2_12_19_+ {
  /**
   * Collect cold statistics (quietly, unless `-Ystatistics` is set) [false]
   */
  def YcollectStatistics = List("-Ycollect-statistics")

  /**
   * [:phases]                    Print compiler statistics for specific phases (implies `-Ycollect-statistics`) <phases> (default: parser,typer,patmat,erasure,cleanup,jvm)
   */
  override def Ystatistics = List("-Ystatistics")
}
