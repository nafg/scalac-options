// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_5_0 extends V3_5_0_+ {
  /**
   * Enable one or more language features.
   */
  override def language(features: String) = List("-language:" + features)

  /**
   *  Emit bytecode for the specified version of the Java platform. This might produce bytecode that will break at runtime. Corresponds to -target flag in javac. When on JDK 9+, consider -java-output-version as a safer alternative. Choices : 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
   */
  override def XuncheckedJavaOutputVersion = List("-Xunchecked-java-output-version")
}

object V3_5_0 extends V3_5_0
