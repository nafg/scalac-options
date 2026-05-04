// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_8_1 extends V3_8_1_+ {
  /**
   * Enable one or more language features. Choices :  - help : 	Display all available features,  - noAutoTupling : 	Disable automatic tupling,  - dynamics : 	Allow direct or indirect subclasses of scala.Dynamic,  - unsafeNulls : 	Enable unsafe nulls for explicit nulls,  - postfixOps : 	Allow postfix operators (not recommended),  - strictEquality : 	Enable strict equality (disable canEqualAny),  - implicitConversions : 	Allow implicit conversions without warnings,  - adhocExtensions : 	Allow ad-hoc extension methods,  - experimental.namedTypeArguments : 	Allow named type arguments,  - experimental.genericNumberLiterals : 	Allow generic number literals,  - experimental.macros : 	Allow Scala 2 macros,  - experimental.dependent : 	Allow dependent method types,  - experimental.erasedDefinitions : 	Allow erased definitions,  - experimental.strictEqualityPatternMatching : 	relaxed CanEqual checks for ADT pattern matching,  - deprecated.symbolLiterals : 	Allow symbol literals,  - experimental.saferExceptions : 	Enable safer exceptions,  - experimental.pureFunctions : 	Enable pure functions for capture checking,  - experimental.captureChecking : 	Enable experimental capture checking,  - experimental.separationChecking : 	Enable experimental separation checking (requires captureChecking),  - experimental.into : 	Allow into modifier on parameter types,  - experimental.modularity : 	Enable experimental modularity features,  - experimental.packageObjectValues : 	Enable experimental package objects as values,  - experimental.multiSpreads : 	Enable experimental varargs with multi-spreads,  - experimental.subCases : 	Enable experimental match expressions with sub-cases,  - experimental.relaxedLambdaSyntax : 	Enable experimental relaxed lambda syntax
   */
  override def language(features: String) = List("-language:" + features)

  /**
   * Show source code line numbers.
   */
  override def printLines = List("-print-lines")
}

object V3_8_1 extends V3_8_1
