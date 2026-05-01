// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_17 extends V2_12_17_+ {
  /**
   * Target platform for object files. All JVM 1.5 - 1.7 targets are deprecated. (5,6,7,[8],9,10,11,12,13,14,15,16,17,18,19,20,21)
   */
  override def target(target: String) = List("-target:" + target)

  /**
   * [:phases]                    Print compiler statistics for specific phases <phases> (default: parser,typer,patmat,erasure,cleanup,jvm)
   */
  override def Ystatistics = List("-Ystatistics")
}

object V2_12_17 extends V2_12_17
