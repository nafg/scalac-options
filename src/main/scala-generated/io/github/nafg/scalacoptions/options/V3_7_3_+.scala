// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_7_3_+ extends V3_7_2_+ {
  /**
   * Enable one or more language features. Choices :  - help : 	Display all available features,  - noAutoTupling : 	Disable automatic tupling,  - dynamics : 	Allow direct or indirect subclasses of scala.Dynamic,  - unsafeNulls : 	Enable unsafe nulls for explicit nulls,  - postfixOps : 	Allow postfix operators (not recommended),  - strictEquality : 	Enable strict equality (disable canEqualAny),  - implicitConversions : 	Allow implicit conversions without warnings,  - adhocExtensions : 	Allow ad-hoc extension methods,  - experimental.namedTypeArguments : 	Allow named type arguments,  - experimental.genericNumberLiterals : 	Allow generic number literals,  - experimental.macros : 	Allow Scala 2 macros,  - experimental.dependent : 	Allow dependent method types,  - experimental.erasedDefinitions : 	Allow erased definitions,  - deprecated.symbolLiterals : 	Allow symbol literals,  - experimental.saferExceptions : 	Enable safer exceptions,  - experimental.pureFunctions : 	Enable pure functions for capture checking,  - experimental.captureChecking : 	Enable experimental capture checking,  - experimental.separationChecking : 	Enable experimental separation checking (requires captureChecking),  - experimental.into : 	Allow into modifier on parameter types,  - experimental.modularity : 	Enable experimental modularity features,  - experimental.packageObjectValues : 	Enable experimental package objects as values
   */
  override def language(features: String) = List("-language:" + features)

  /**
   * Warn when a method calls itself with a default argument.
   */
  def WrecurseWithDefault = List("-Wrecurse-with-default")

  /**
   * Specify the magic header comment that marks the start of the actual code in generated wrapper scripts. Example: -Ymagic-offset-header:SOURCE_CODE_START. Then, in the source, the magic comment `///SOURCE_CODE_START:<ORIGINAL_FILE_PATH>` marks the start of user code. The comment should be suffixed by `:<ORIGINAL_FILE_PATH>` to indicate the original file.
   */
  def YmagicOffsetHeader = List("-Ymagic-offset-header")
}
