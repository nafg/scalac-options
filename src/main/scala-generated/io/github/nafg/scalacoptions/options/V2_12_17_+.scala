// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_17_+ extends V2_12_16_+ {
  /**
   * Compile for a version of the Java API and target class file. (5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,[21])
   */
  def release(release: String) = List("-release:" + release)
}
