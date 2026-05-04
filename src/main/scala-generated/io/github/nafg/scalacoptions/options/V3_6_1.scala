// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_6_1 extends V3_6_1_+ {
  /**
   * Enable one or more language features. Choices :  - help : 	Display all available features,  - noAutoTupling : 	Disable automatic tupling,  - dynamics : 	Allow direct or indirect subclasses of scala.Dynamic,  - unsafeNulls : 	Enable unsafe nulls for explicit nulls,  - postfixOps : 	Allow postfix operators (not recommended),  - strictEquality : 	Enable strict equality (disable canEqualAny),  - implicitConversions : 	Allow implicit conversions without warnings,  - adhocExtensions : 	Allow ad-hoc extension methods,  - experimental.namedTypeArguments : 	Allow named type arguments,  - experimental.genericNumberLiterals : 	Allow generic number literals,  - experimental.macros : 	Allow Scala 2 macros,  - experimental.dependent : 	Allow dependent method types,  - experimental.erasedDefinitions : 	Allow erased definitions,  - deprecated.symbolLiterals : 	Allow symbol literals,  - experimental.fewerBraces : 	Enable support for using indentation for arguments,  - experimental.saferExceptions : 	Enable safer exceptions,  - experimental.clauseInterleaving : 	Enable clause interleaving,  - experimental.pureFunctions : 	Enable pure functions for capture checking,  - experimental.captureChecking : 	Enable experimental capture checking,  - experimental.into : 	Allow into modifier on parameter types,  - experimental.modularity : 	Enable experimental modularity features,  - experimental.betterMatchTypeExtractors : 	Enable better match type extractors,  - experimental.betterFors : 	Enable improvements in `for` comprehensions
   */
  override def language(features: String) = List("-language:" + features)

  /**
   * Show raw StackOverflow stacktraces, instead of decoding them into triggering operations.
   */
  def XnoDecodeStacktraces = List("-Xno-decode-stacktraces")

  /**
   *  Emit bytecode for the specified version of the Java platform. This might produce bytecode that will break at runtime. Corresponds to -target flag in javac. When on JDK 9+, consider -java-output-version as a safer alternative. Choices : 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23
   */
  override def XuncheckedJavaOutputVersion = List("-Xunchecked-java-output-version")

  /**
   * Option deprecated. Use -Xno-decode-stacktraces instead. Show raw StackOverflow stacktraces, instead of decoding them into triggering operations.
   */
  override def YnoDecodeStacktraces = List("-Yno-decode-stacktraces")
}

object V3_6_1 extends V3_6_1
