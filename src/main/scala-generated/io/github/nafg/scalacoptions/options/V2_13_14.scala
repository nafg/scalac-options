// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_14 extends V2_13_14_+ {
  /**
   * Generate no warnings. [false]
   */
  override def nowarn = List("-nowarn")

  /**
   * [:phases]             Print compiler statistics for specific phases <phases> (default: parser,typer,patmat,erasure,cleanup,jvm)
   */
  override def Vstatistics = List("-Vstatistics")

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

  /**
   * Generate forwarder methods in classes inhering concrete methods from traits. Default: `true`, `help` to list choices.
   */
  override def XmixinForceForwarders(mode: String) = List("-Xmixin-force-forwarders:" + mode)
}

object V2_13_14 extends V2_13_14
