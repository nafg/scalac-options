// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_3 extends V2_13_3_+ {
  /**
   * destination for generated classfiles.
   */
  override def d(`directory|jar`: String) = List("-d", `directory|jar`)

  /**
   * Generate no warnings. [false]
   */
  override def nowarn = List("-nowarn")

  /**
   * Enable optimizations, `help` for details.
   */
  override def opt(optimizations: String) = List("-opt:" + optimizations)

  /**
   * Compile for a specific version of the Java platform. Supported targets: 6, 7, 8, 9
   */
  def release(release: String) = List("-release", release)

  /**
   * Target platform for object files. ([8],9,10,11,12)
   */
  def target(target: String) = List("-target:" + target)

  /**
   * Show more detail on why some implicits are not applicable. [false]
   */
  override def Vimplicits = List("-Vimplicits")

  /**
   * Print stack traces when a context issues an error. [false]
   */
  def Vissue = List("-Vissue")

  /**
   * Print out program after <phases>
   */
  def Vprint(phases: String) = List("-Vprint:" + phases)

  /**
   * [:phases]             Print compiler statistics for specific phases <phases> (default: parser,typer,patmat,erasure,cleanup,jvm)
   */
  override def Vstatistics = List("-Vstatistics")

  /**
   * Enable lint warnings on macro expansions. Default: `before`, `help` to list choices.
   */
  override def Wmacros(mode: String) = List("-Wmacros:" + mode)

  /**
   * Enable -Wunused:explicits,implicits.
   */
  override def WunusedParams = List("-Wunused:params")

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
   * The Java-defined target interface for eta-expansion was not annotated @FunctionalInterface.
   */
  override def XlintEtaSam = List("-Xlint:eta-sam")

  /**
   * Infix operator was defined or used with multiarg operand.
   */
  def XlintMultiargInfix = List("-Xlint:multiarg-infix")

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
   * How to print trees when -Vprint is enabled. ([text],compact,format,text+format)
   */
  override def YprintTrees(style: String) = List("-Yprint-trees:" + style)

  /**
   * Use range positions for syntax trees. [false]
   */
  override def Yrangepos = List("-Yrangepos")
}

object V2_13_3 extends V2_13_3
