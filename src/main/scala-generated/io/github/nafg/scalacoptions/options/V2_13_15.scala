// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_15 extends V2_13_15_+ {
  /**
   * Infix operator was defined or used with multiarg operand.
   */
  def XlintMultiargInfix = List("-Xlint:multiarg-infix")

  /**
   * Require arg to is/asInstanceOf. No Unit receiver.
   */
  override def XlintUniversalMethods = List("-Xlint:universal-methods")

  /**
   * <path>              Class for manifest's Main-Class entry (only useful with -d <jar>)
   */
  def XmainClass = List("-Xmain-class")
}

object V2_13_15 extends V2_13_15
