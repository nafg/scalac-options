// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_11_2 extends V2_11_2_+ {
  /**
   * Target platform for object files. All JVM 1.5 targets are deprecated. (jvm-1.5,jvm-1.6,jvm-1.7) default:jvm-1.6
   */
  override def target(target: String) = List("-target:" + target)

  /**
   * Infer types for arguments of overriden methods.
   */
  override def YinferArgumentTypes = List("-Yinfer-argument-types")

  /**
   * Emit inlining warnings. (Normally surpressed due to high volume)
   */
  override def YinlineWarnings = List("-Yinline-warnings")

  /**
   * Invalidate classpath entry before run
   */
  def Yinvalidate(`classpath-entry`: String) = List("-Yinvalidate", `classpath-entry`)

  /**
   * Print compiler statistics.
   */
  def Ystatistics = List("-Ystatistics")

  /**
   * Warn when local and private vals, vars, defs, and types are are unused
   */
  override def YwarnUnused = List("-Ywarn-unused")

  /**
   * Warn when imports are unused
   */
  override def YwarnUnusedImport = List("-Ywarn-unused-import")
}

object V2_11_2 extends V2_11_2
