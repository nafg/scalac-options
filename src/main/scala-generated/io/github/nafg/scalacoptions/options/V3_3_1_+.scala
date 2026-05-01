// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_3_1_+ extends V3_3_0_+ {
  /**
   * Compile code with classes specific to the given version of the Java platform available on the classpath and emit bytecode for this version. Corresponds to -release flag in javac. Choices : 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
   */
  override def javaOutputVersion = List("-java-output-version")

  /**
   * Warn when block statements are non-Unit expressions.
   */
  def WnonunitStatement = List("-Wnonunit-statement")
}
