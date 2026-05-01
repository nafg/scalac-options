// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_14_+ extends V2_13_13_+ {
  /**
   * Destination for generated artifacts.
   */
  override def d(`directory|jar`: String) = List("-d", `directory|jar`)

  /**
   * <file>              Generate phase graph to <file>-*.dot.
   */
  def VphaseGraph = List("-Vphase-graph")

  /**
   * Enable lint warnings on macro expansions. Default: `default`, `help` to list choices.
   */
  override def Wmacros(mode: String) = List("-Wmacros:" + mode)

  /**
   * Warn about unnamed boolean literals if there is more than one or defaults are used, unless parameter has @deprecatedName. [false]
   */
  def WunnamedBooleanLiteral = List("-Wunnamed-boolean-literal")

  /**
   * Warn about all unnamed boolean literals, unless parameter has @deprecatedName or the method has a single leading boolean parameter. [false]
   */
  def WunnamedBooleanLiteralStrict = List("-Wunnamed-boolean-literal-strict")

  /**
   * Do not use JLine for editing. [false]
   */
  override def Xnojline = List("-Xnojline")

  /**
   * Enable Scala 3 features under -Xsource:3: `-Xsource-features:help` for details.
   */
  def XsourceFeatures(features: String) = List("-Xsource-features:" + features)

  /**
   * 3-implicit-resolution          Use Scala-3-style downwards comparisons for implicit search and overloading resolution (see github.com/scala/scala/pull/6037). [false] deprecated: Use -Xsource:3 -Xsource-features:implicit-resolution instead
   */
  @deprecated("See doc comment", "") override def Yscala = List("-Yscala")
}
