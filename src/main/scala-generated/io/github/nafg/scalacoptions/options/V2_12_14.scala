// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_14 extends V2_12_14_+ {
  /**
   * Compile for a specific version of the Java platform. Supported targets: 6, 7, 8, 9
   */
  def release(release: String) = List("-release", release)

  /**
   * Target platform for object files. All JVM 1.5 - 1.7 targets are deprecated. (jvm-1.5,jvm-1.6,jvm-1.7,[jvm-1.8])
   */
  override def target(target: String) = List("-target:" + target)

  /**
   * [:phases]                    Print compiler statistics for specific phases <phases> (default: parser,typer,patmat,erasure,cleanup,jvm)
   */
  override def Ystatistics = List("-Ystatistics")
}

object V2_12_14 extends V2_12_14
