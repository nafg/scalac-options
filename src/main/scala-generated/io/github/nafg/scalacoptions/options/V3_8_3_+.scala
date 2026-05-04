// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_8_3_+ extends V3_8_2_+ {
  /**
   * Enable one or more language features. Choices :  - help : 	Display all available features,  - noAutoTupling : 	Disable automatic tupling,  - dynamics : 	Allow direct or indirect subclasses of scala.Dynamic,  - unsafeNulls : 	Enable unsafe nulls for explicit nulls,  - postfixOps : 	Allow postfix operators (not recommended),  - strictEquality : 	Enable strict equality (disable canEqualAny),  - implicitConversions : 	Allow implicit conversions without warnings,  - adhocExtensions : 	Allow ad-hoc extension methods,  - experimental.namedTypeArguments : 	Allow named type arguments,  - experimental.genericNumberLiterals : 	Allow generic number literals,  - experimental.macros : 	Allow Scala 2 macros,  - experimental.dependent : 	Allow dependent method types,  - experimental.erasedDefinitions : 	Allow erased definitions,  - experimental.strictEqualityPatternMatching : 	relaxed CanEqual checks for ADT pattern matching,  - deprecated.symbolLiterals : 	Allow symbol literals,  - experimental.saferExceptions : 	Enable safer exceptions,  - experimental.pureFunctions : 	Enable pure functions for capture checking,  - experimental.captureChecking : 	Enable experimental capture checking,  - experimental.separationChecking : 	Enable experimental separation checking (requires captureChecking),  - experimental.into : 	Allow into modifier on parameter types,  - experimental.modularity : 	Enable experimental modularity features,  - experimental.packageObjectValues : 	Enable experimental package objects as values,  - experimental.multiSpreads : 	Enable experimental varargs with multi-spreads,  - experimental.subCases : 	Enable experimental match expressions with sub-cases,  - experimental.relaxedLambdaSyntax : 	Enable experimental relaxed lambda syntax,  - experimental.safe : 	Require safe mode
   */
  override def language(features: String) = List("-language:" + features)

  /**
   * Optimize generated JVM bytecode.
   */
  def opt = List("-opt")

  /**
   * Inlining requires a list of patterns defining where code can be inlined from: `-opt-inline:p1,p2`. (Use `-opt-inline:help` for more details)
   */
  def optInline = List("-opt-inline")

  /**
   * Option deprecated. Scheduled for removal in 3.9.0 Show source code line numbers.
   */
  override def printLines = List("-print-lines")

  /**
   * Enable optimizer warnings, `help` for details. Choices :  - all,  - at-inline-failed-summary : 	One-line summary if there were @inline method calls that could not be inlined.,  - at-inline-failed : 	A detailed warning for each @inline method call that could not be inlined.,  - any-inline-failed : 	A detailed warning for every callsite that was chosen for inlining by the heuristics, but could not be inlined.,  - no-inline-mixed : 	In mixed compilation, warn at callsites methods defined in java sources (the inlining decision cannot be made without bytecode).,  - no-inline-missing-bytecode : 	Warn if an inlining decision cannot be made because a the bytecode of a class or member cannot be found on the compilation classpath.,  - no-inline-missing-attribute : 	Warn if an inlining decision cannot be made because a Scala classfile does not have a ScalaInlineInfo attribute.
   */
  def Wopt = List("-Wopt")

  /**
   * Set the heuristics for inlining decisions. Default default Choices : at-inline-annotated, everything, default
   */
  def YoptInlineHeuristics = List("-Yopt-inline-heuristics")

  /**
   * Print a summary of inliner activity; `_` to print all, prefix match to select.
   */
  def YoptLogInline = List("-Yopt-log-inline")

  /**
   * Enable specific optimizations: `-Yopt-specific:unreachable-code`; `-Yopt-specific:help` for details. Choices :  - unreachable-code : 	Eliminate unreachable code, exception handlers guarding no instructions, redundant metadata (debug information, line numbers).,  - simplify-jumps : 	Simplify branching instructions, eliminate unnecessary ones.,  - compact-locals : 	Eliminate empty slots in the sequence of local variables.,  - copy-propagation : 	Eliminate redundant local variables and unused values (including closures). Enables unreachable-code.,  - redundant-casts : 	Eliminate redundant casts using a type propagation analysis.,  - box-unbox : 	Eliminate box-unbox pairs within the same method (also tuples, xRefs, value class instances). Enables unreachable-code.,  - nullness-tracking : 	Track nullness / non-nullness of local variables and apply optimizations.,  - closure-invocations : 	Rewrite closure invocations to the implementation method.,  - allow-skip-core-module-init : 	Allow eliminating unused module loads for core modules of the standard library (e.g., Predef, ClassTag).,  - assume-modules-non-null : 	Assume loading a module never results in null (happens if the module is accessed in its super constructor).,  - allow-skip-class-loading : 	Allow optimizations that can skip or delay class loading.
   */
  def YoptSpecific = List("-Yopt-specific")

  /**
   * Trace the optimizer progress for methods; `_` to print all, prefix match to select.
   */
  def YoptTrace = List("-Yopt-trace")
}
