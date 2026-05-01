// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_7_0 extends V3_7_0_+ {
  /**
   * Enable one or more language features. Choices :  - help : 	Display all available features,  - noAutoTupling : 	Disable automatic tupling,  - dynamics : 	Allow direct or indirect subclasses of scala.Dynamic,  - unsafeNulls : 	Enable unsafe nulls for explicit nulls,  - postfixOps : 	Allow postfix operators (not recommended),  - strictEquality : 	Enable strict equality (disable canEqualAny),  - implicitConversions : 	Allow implicit conversions without warnings,  - adhocExtensions : 	Allow ad-hoc extension methods,  - experimental.namedTypeArguments : 	Allow named type arguments,  - experimental.genericNumberLiterals : 	Allow generic number literals,  - experimental.macros : 	Allow Scala 2 macros,  - experimental.dependent : 	Allow dependent method types,  - experimental.erasedDefinitions : 	Allow erased definitions,  - deprecated.symbolLiterals : 	Allow symbol literals,  - experimental.saferExceptions : 	Enable safer exceptions,  - experimental.pureFunctions : 	Enable pure functions for capture checking,  - experimental.captureChecking : 	Enable experimental capture checking,  - experimental.into : 	Allow into modifier on parameter types,  - experimental.modularity : 	Enable experimental modularity features
   */
  override def language(features: String) = List("-language:" + features)

  /**
   * source version Default 3.7 Choices : 3.0-migration, 3.0, 3.1, 3.2-migration, 3.2, 3.3-migration, 3.3, 3.4-migration, 3.4, 3.5-migration, 3.5, 3.6-migration, 3.6, 3.7-migration, 3.7, 3.8-migration, 3.8, future-migration, future, never
   */
  override def source = List("-source")

  /**
   *  Emit bytecode for the specified version of the Java platform. This might produce bytecode that will break at runtime. Corresponds to -target flag in javac. When on JDK 9+, consider -java-output-version as a safer alternative. Choices : 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24
   */
  override def XuncheckedJavaOutputVersion = List("-Xunchecked-java-output-version")
}

object V3_7_0 extends V3_7_0
