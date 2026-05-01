// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_11 extends V2_13_11_+ {
  /**
   * destination for generated classfiles.
   */
  override def d(`directory|jar`: String) = List("-d", `directory|jar`)

  /**
   * Generate no warnings. [false]
   */
  override def nowarn = List("-nowarn")

  /**
   * [:phases]             Print compiler statistics for specific phases <phases> (default: parser,typer,patmat,erasure,cleanup,jvm)
   */
  override def Vstatistics = List("-Vstatistics")

  /**
   * Enable lint warnings on macro expansions. Default: `before`, `help` to list choices.
   */
  override def Wmacros(mode: String) = List("-Wmacros:" + mode)

  /**
   * Former graveyard for language-forking extensions. [false] deprecated: Not used since 2.13.
   */
  @deprecated("See doc comment", "") def Xexperimental = List("-Xexperimental")

  /**
   * Replaced by -Xsource. [false] deprecated: Not used since 2.13.
   */
  @deprecated("See doc comment", "") def Xfuture = List("-Xfuture")

  /**
   * <file>    Generate the phase graphs (outputs .dot files) to fileX.dot.
   */
  def XgeneratePhaseGraph = List("-Xgenerate-phase-graph")

  /**
   * Select JLine mode. Default: `emacs`, `help` to list choices.
   */
  def Xjline(mode: String) = List("-Xjline:" + mode)

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

  /**
   * Do not use JLine for editing. [false] deprecated: Replaced by -Xjline:off
   */
  @deprecated("See doc comment", "") override def Xnojline = List("-Xnojline")

  /**
   * Enable features that will be available in a future version of Scala, for purposes of early migration and alpha testing.
   */
  override def Xsource(version: String) = List("-Xsource:" + version)

  /**
   * 3-implicit-resolution          Use Scala-3-style downwards comparisons for implicit search and overloading resolution (see https://github.com/scala/bug/issues/12437). [false]
   */
  override def Yscala = List("-Yscala")
}

object V2_13_11 extends V2_13_11
