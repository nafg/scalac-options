// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_11_3_+ extends V2_11_2_+ {
  /**
   * Target platform for object files. All JVM 1.5 targets are deprecated. (jvm-1.5,jvm-1.6,jvm-1.7,jvm-1.8) default:jvm-1.6
   */
  override def target(target: String) = List("-target:" + target)

  /**
   * Enable optimizations: `_' for all, `-Yopt:help' to list
   */
  def Yopt(`_,optimization,-optimization`: String) = List("-Yopt:" + `_,optimization,-optimization`)

  /**
   * <n>              off
   */
  def YpatmatExhaustDepth = List("-Ypatmat-exhaust-depth")

  /**
   * Print compiler statistics for specific phases: `_' for all, `-Ystatistics:help' to list
   */
  def Ystatistics(`_,phase,-phase`: String) = List("-Ystatistics:" + `_,phase,-phase`)

  /**
   * Warn when imports are unused.
   */
  override def YwarnUnusedImport = List("-Ywarn-unused-import")
}
