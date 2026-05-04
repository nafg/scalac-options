// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_1_2_+ extends V3_1_1_+ {
  /**
   * Pass -Dproperty=value directly to the runtime system.
   */
  def D(property: String, value: String) = List("-D" + property + "=" + value)

  /**
   * Pass <flag> directly to the runtime system.
   */
  def J(flag: String) = List("-J" + flag)

  def javaOutputVersion = List("-java-output-version")

  def scalaOutputVersion = List("-scala-output-version")

  def XimplicitSearchLimit = List("-Ximplicit-search-limit")

  /**
   * List of settings which exposed to the macros
   */
  def XmacroSettings = List("-Xmacro-settings")

  def XuncheckedJavaOutputVersion = List("-Xunchecked-java-output-version")

  /**
   * print nesting levels of symbols and type variables.
   */
  def YprintLevel = List("-Yprint-level")
}
