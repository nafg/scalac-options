// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_7_1_+ extends V3_7_0_+ {
  /**
   * source version Default 3.7 Choices : 3.0-migration, 3.0, 3.1, 3.2-migration, 3.2, 3.3-migration, 3.3, 3.4-migration, 3.4, 3.5-migration, 3.5, 3.6-migration, 3.6, 3.7-migration, 3.7, 3.8-migration, 3.8, future-migration, future
   */
  override def source = List("-source")

  /**
   * Warn a standard interpolator used toString on a reference type.
   */
  def WtostringInterpolated = List("-Wtostring-interpolated")

  /**
   *  Emit bytecode for the specified version of the Java platform. This might produce bytecode that will break at runtime. Corresponds to -target flag in javac. When on JDK 9+, consider -java-output-version as a safer alternative. Choices : 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
   */
  override def XuncheckedJavaOutputVersion = List("-Xunchecked-java-output-version")

  /**
   * Print root capabilities with more details
   */
  def YccVerbose = List("-Ycc-verbose")
}
