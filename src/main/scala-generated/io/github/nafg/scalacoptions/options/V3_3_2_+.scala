// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_3_2_+ extends V3_3_1_+ {
  /**
   *  Generate static forwarders even for non-top-level objects (Scala.js only).
   */
  override def scalajsGenStaticForwardersForNonTopLevelObjects = List("-scalajs-genStaticForwardersForNonTopLevelObjects")

  /**
   * rebases source URIs from uri1 to uri2 (or to a relative URI) for source maps (Scala.js only).
   */
  override def scalajsMapSourceURI = List("-scalajs-mapSourceURI")

  /**
   * Specifies whether to include source code in SemanticDB files or not.
   */
  def semanticdbText = List("-semanticdb-text")

  /**
   * Used in conjunction with captureChecking language import, debug info for captured references.
   */
  override def YccDebug = List("-Ycc-debug")

  /**
   * Used in conjunction with captureChecking language import, suppress type abbreviations.
   */
  override def YccNoAbbrev = List("-Ycc-no-abbrev")

  /**
   * Show debug info when quote pattern match fails
   */
  def YdebugMacros = List("-Ydebug-macros")

  /**
   * List of `tasty` files in jar files that will not be loaded when using -from-tasty.
   */
  override def YfromTastyIgnoreList = List("-Yfrom-tasty-ignore-list")

  /**
   * Use legacy (pre 3.3.0) implementation of lazy vals.
   */
  override def YlegacyLazyVals = List("-Ylegacy-lazy-vals")

  /**
   * Show raw error messages, instead of enriching them with contextual information.
   */
  def YnoEnrichErrorMessages = List("-Yno-enrich-error-messages")

  /**
   * Disable experimental language features.
   */
  override def YnoExperimental = List("-Yno-experimental")

  /**
   * Prints the generated TASTY to stdout.
   */
  def YprintTasty = List("-Yprint-tasty")

  /**
   * Run basic rechecking (internal test only).
   */
  override def YrecheckTest = List("-Yrecheck-test")

  /**
   * Warn if an operator is defined without a @targetName annotation.
   */
  override def YrequireTargetName = List("-Yrequire-targetName")

  /**
   * Ensure safe initialization of objects.
   */
  override def YsafeInit = List("-Ysafe-init")
}
