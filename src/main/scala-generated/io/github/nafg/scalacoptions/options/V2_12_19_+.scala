// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_19_+ extends V2_12_18_+ {
  /**
   * Target platform for class files. Target < 8 is deprecated; target > 8 uses 8. Default: `8`, `help` to list choices.
   */
  override def target(target: String) = List("-target:" + target)

  /**
   * Expose platform packages hidden under --release
   */
  def Yrelease(packages: String) = List("-Yrelease:" + packages)
}
