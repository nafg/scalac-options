// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_1_+ extends V2_13_0_+ {
  /**
   * An argument list was modified to match the receiver.
   */
  override def XlintAdaptedArgs = List("-Xlint:adapted-args")

  /**
   * Evaluation of a constant arithmetic expression resulted in an error.
   */
  override def XlintConstant = List("-Xlint:constant")

  /**
   * Enable -deprecation and also check @deprecated annotations.
   */
  override def XlintDeprecation = List("-Xlint:deprecation")

  /**
   * When running scaladoc, warn if a doc comment is discarded.
   */
  override def XlintDocDetached = List("-Xlint:doc-detached")

  /**
   * Usage `f` of parameterless `def f()` resulted in eta-expansion, not empty application `f()`.
   */
  override def XlintEtaZero = List("-Xlint:eta-zero")

  /**
   * A type argument was inferred as Any.
   */
  override def XlintInferAny = List("-Xlint:infer-any")

  /**
   * `def f: Unit` looks like an accessor; add parens to look side-effecting.
   */
  override def XlintNullaryUnit = List("-Xlint:nullary-unit")

  /**
   * Option.apply used an implicit view.
   */
  override def XlintOptionImplicit = List("-Xlint:option-implicit")

  /**
   * In a pattern, a sequence wildcard `_*` should match all of a repeated parameter.
   */
  override def XlintStarsAlign = List("-Xlint:stars-align")

  /**
   * Specify a custom subclass of FilteringReporter for compiler messages.
   */
  override def Xreporter(classname: String) = List("-Xreporter", classname)

  /**
   * factory for jar files
   */
  def YjarFactory(classname: String) = List("-YjarFactory", classname)

  def YpresentationLocateSourceFile = List("-Ypresentation-locate-source-file")
}
