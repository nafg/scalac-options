// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_3_1 extends V3_3_1_+ {
  /**
   *  Generate static forwarders even for non-top-level objects (Scala.js only)
   */
  override def scalajsGenStaticForwardersForNonTopLevelObjects = List("-scalajs-genStaticForwardersForNonTopLevelObjects")

  /**
   * rebases source URIs from uri1 to uri2 (or to a relative URI) for source maps (Scala.js only)
   */
  override def scalajsMapSourceURI = List("-scalajs-mapSourceURI")

  /**
   * Enable or disable specific `unused` warnings Choices :  - nowarn,  - all,  - imports : 	Warn if an import selector is not referenced. 	NOTE : overrided by -Wunused:strict-no-implicit-warn,  - privates : 	Warn if a private member is unused,  - locals : 	Warn if a local definition is unused,  - explicits : 	Warn if an explicit parameter is unused,  - implicits : 	Warn if an implicit parameter is unused,  - params : 	Enable -Wunused:explicits,implicits,  - linted : 	Enable -Wunused:imports,privates,locals,implicits,  - strict-no-implicit-warn : 	Same as -Wunused:import, only for imports of explicit named members. 	NOTE : This overrides -Wunused:imports and NOT set by -Wunused:all,  - unsafe-warn-patvars : 	(UNSAFE) Warn if a variable bound in a pattern is unused. 	This warning can generate false positive, as warning cannot be 	suppressed yet.
   */
  override def Wunused = List("-Wunused")

  /**
   *  Emit bytecode for the specified version of the Java platform. This might produce bytecode that will break at runtime. Corresponds to -target flag in javac. When on JDK 9+, consider -java-output-version as a safer alternative. Choices : 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
   */
  override def XuncheckedJavaOutputVersion = List("-Xunchecked-java-output-version")

  /**
   * Used in conjunction with captureChecking language import, debug info for captured references
   */
  override def YccDebug = List("-Ycc-debug")

  /**
   * Used in conjunction with captureChecking language import, suppress type abbreviations
   */
  override def YccNoAbbrev = List("-Ycc-no-abbrev")

  /**
   * List of `tasty` files in jar files that will not be loaded when using -from-tasty
   */
  override def YfromTastyIgnoreList = List("-Yfrom-tasty-ignore-list")

  /**
   * Use legacy (pre 3.3.0) implementation of lazy vals
   */
  override def YlegacyLazyVals = List("-Ylegacy-lazy-vals")

  /**
   * Disable experimental language features
   */
  override def YnoExperimental = List("-Yno-experimental")

  /**
   * Run basic rechecking (internal test only)
   */
  override def YrecheckTest = List("-Yrecheck-test")

  /**
   * Warn if an operator is defined without a @targetName annotation
   */
  override def YrequireTargetName = List("-Yrequire-targetName")

  /**
   * Ensure safe initialization of objects
   */
  override def YsafeInit = List("-Ysafe-init")
}

object V3_3_1 extends V3_3_1
