// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_6_4_+ extends V3_6_3_+ {
  /**
   * The code will be run on REPL startup.
   */
  def replInitScript = List("-repl-init-script")

  /**
   *  Emit bytecode for the specified version of the Java platform. This might produce bytecode that will break at runtime. Corresponds to -target flag in javac. When on JDK 9+, consider -java-output-version as a safer alternative. Choices : 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24
   */
  override def XuncheckedJavaOutputVersion = List("-Xunchecked-java-output-version")

  /**
   * Option deprecated. Use -Xno-enrich-error-messages instead. Show raw StackOverflow stacktraces, instead of decoding them into triggering operations.
   */
  override def YnoDecodeStacktraces = List("-Yno-decode-stacktraces")
}
