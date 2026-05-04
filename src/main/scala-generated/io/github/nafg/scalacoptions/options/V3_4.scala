// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_4 extends V3 {
  /**
   * Specify where to find user class files. Default .
   */
  override def classpath = List("-classpath")

  /**
   * Colored output Default always Choices : always, never
   */
  override def color = List("-color")

  /**
   * Destination for coverage classfiles and instrumentation data.
   */
  def coverageOut = List("-coverage-out")

  /**
   * Specify character encoding used by source files. Default UTF-8
   */
  override def encoding(encoding: String) = List("-encoding", encoding)

  /**
   * Annotate all top-level definitions with @experimental. This enables the use of experimental features anywhere in the project.
   */
  def experimental = List("-experimental")

  def J(flag: String) = List("-J" + flag)

  /**
   * Compile code with classes specific to the given version of the Java platform available on the classpath and emit bytecode for this version. Corresponds to -release flag in javac. Choices : 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
   */
  def javaOutputVersion = List("-java-output-version")

  /**
   * Enable one or more language features.
   */
  override def language(features: String) = List("-language:" + features)

  /**
   * Set page width Default 20000
   */
  override def pagewidth = List("-pagewidth")

  /**
   * Show source code line numbers.
   */
  override def printLines = List("-print-lines")

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
   * source version Default 3.4 Choices : 3.0-migration, 3.0, 3.1, 3.2-migration, 3.2, 3.3-migration, 3.3, 3.4-migration, 3.4, 3.5-migration, 3.5, future-migration, future
   */
  override def source = List("-source")

  /**
   * Specify workspace root directory. Default .
   */
  override def sourceroot = List("-sourceroot")

  /**
   * Utilize the java.class.path in classpath resolution.
   */
  def usejavacp = List("-usejavacp")

  /**
   * Print a synopsis of verbose options.
   */
  def V = List("-V")

  /**
   * Print a synopsis of warning options.
   */
  def W = List("-W")

  /**
   * Configure compiler warnings.
   */
  def Wconf = List("-Wconf")

  /**
   * Fail the compilation if there are any warnings.
   */
  def Werror = List("-Werror")

  /**
   * Warn if comparison with a pattern value looks like it might always fail.
   */
  def WimplausiblePatterns = List("-Wimplausible-patterns")

  /**
   * Warn when block statements are non-Unit expressions.
   */
  def WnonunitStatement = List("-Wnonunit-statement")

  /**
   * Warn an inline methods has references to non-stable binary APIs.
   */
  def WunstableInlineAccessors = List("-WunstableInlineAccessors")

  /**
   * Enable or disable specific `unused` warnings Choices :  - nowarn,  - all,  - imports : 	Warn if an import selector is not referenced. 	NOTE : overrided by -Wunused:strict-no-implicit-warn,  - privates : 	Warn if a private member is unused,  - locals : 	Warn if a local definition is unused,  - explicits : 	Warn if an explicit parameter is unused,  - implicits : 	Warn if an implicit parameter is unused,  - params : 	Enable -Wunused:explicits,implicits,  - linted : 	Enable -Wunused:imports,privates,locals,implicits,  - strict-no-implicit-warn : 	Same as -Wunused:import, only for imports of explicit named members. 	NOTE : This overrides -Wunused:imports and NOT set by -Wunused:all,  - unsafe-warn-patvars : 	(UNSAFE) Warn if a variable bound in a pattern is unused. 	This warning can generate false positive, as warning cannot be 	suppressed yet.
   */
  def Wunused = List("-Wunused")

  /**
   * Warn when non-Unit expression results are unused.
   */
  def WvalueDiscard = List("-Wvalue-discard")

  /**
   * 2-macros  Ignore errors when compiling code that calls Scala2 macros, these will fail at runtime.
   */
  override def XignoreScala = List("-Xignore-scala")

  /**
   * Maximal number of expressions to be generated in an implicit search Default 50000
   */
  def XimplicitSearchLimit = List("-Ximplicit-search-limit")

  /**
   * Timeout (in ms) for searching for import suggestions when errors are reported. Default 8000
   */
  override def XimportSuggestionTimeout = List("-Ximport-suggestion-timeout")

  def Xlint = List("-Xlint")

  /**
   * List of settings which exposed to the macros
   */
  def XmacroSettings = List("-Xmacro-settings")

  /**
   * Class for manifest's Main-Class entry (only useful with -d <jar>)
   */
  def XmainClass = List("-Xmain-class")

  /**
   * Maximal number of inlined trees. Default 2000000
   */
  override def XmaxInlinedTrees = List("-Xmax-inlined-trees")

  /**
   * Maximal number of successive inlines. Default 32
   */
  override def XmaxInlines = List("-Xmax-inlines")

  /**
   * Generate forwarder methods in classes inhering concrete methods from traits. Default true Choices : true, junit, false
   */
  override def XmixinForceForwarders = List("-Xmixin-force-forwarders")

  /**
   * Path to search for plugin archives. Default misc/scala-devel/plugins
   */
  override def Xpluginsdir = List("-Xpluginsdir")

  /**
   *  Emit bytecode for the specified version of the Java platform. This might produce bytecode that will break at runtime. Corresponds to -target flag in javac. When on JDK 9+, consider -java-output-version as a safer alternative. Choices : 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
   */
  def XuncheckedJavaOutputVersion = List("-Xunchecked-java-output-version")

  /**
   * Allow outline TASTy to be loaded with the -from-tasty option.
   */
  def YallowOutlineFromTasty = List("-Yallow-outline-from-tasty")

  /**
   * maximum worker threads for backend Default 1 Choices : 1..16
   */
  def YbackendParallelism = List("-Ybackend-parallelism")

  /**
   * backend threads worker queue size Default 0 Choices : 0..1000
   */
  def YbackendWorkerQueue = List("-Ybackend-worker-queue")

  /**
   * Used in conjunction with captureChecking language import, debug info for captured references.
   */
  def YccDebug = List("-Ycc-debug")

  /**
   * Used in conjunction with captureChecking language import, print tracing and debug info
   */
  def YccLog = List("-Ycc-log")

  /**
   * Used in conjunction with captureChecking language import, try out new variants (debug option)
   */
  def YccNew = List("-Ycc-new")

  /**
   * Used in conjunction with captureChecking language import, print trees after cc.Setup phase
   */
  def YccPrintSetup = List("-Ycc-print-setup")

  /**
   * Check dependency tracking in constraints (used for testing the algorithm).
   */
  def YcheckConstraintDeps = List("-Ycheck-constraint-deps")

  /**
   * 2-library  Used when compiling the Scala 2 standard library.
   */
  def YcompileScala = List("-Ycompile-scala")

  /**
   * Cook the documentation (type check `@usecase`, etc.)
   */
  def YcookDocs = List("-Ycook-docs")

  /**
   * Show debug info when quote pattern match fails
   */
  def YdebugMacros = List("-Ydebug-macros")

  /**
   * Print the stack trace when the tree with the given id is created. Default -2147483648
   */
  override def YdebugTreeWithId = List("-Ydebug-tree-with-id")

  /**
   * Print the stack trace when an error occurs when reading Tasty.
   */
  def YdebugUnpickling = List("-Ydebug-unpickling")

  /**
   * Drop documentation when scanning source files.
   */
  def YdropDocs = List("-Ydrop-docs")

  /**
   * Dump the generated bytecode to .class files (useful for reflective compilation that utilizes in-memory classloaders).
   */
  override def YdumpClasses = List("-Ydump-classes")

  /**
   * List of `tasty` files in jar files that will not be loaded when using -from-tasty.
   */
  override def YfromTastyIgnoreList = List("-Yfrom-tasty-ignore-list")

  /**
   * Custom root imports. If set, none of scala.*, java.lang.*, or Predef.* will be imported unless explicitly included.
   */
  def Yimports = List("-Yimports")

  /**
   * compression level to use when writing jar files Default -1 Choices : -1..9
   */
  def YjarCompressionLevel = List("-Yjar-compression-level")

  /**
   * Pickler phase should compute pickles for .java defined symbols for use by build tools
   */
  def YjavaTasty = List("-Yjava-tasty")

  /**
   * (Internal use only!) destination for generated .tasty files containing Java type signatures.
   */
  def YjavaTastyOutput = List("-Yjava-tasty-output")

  /**
   * Allow `*` as type lambda placeholder to be compatible with kind projector. When invoked as -Ykind-projector:underscores will repurpose `_` to be a type parameter placeholder, this will disable usage of underscore as a wildcard. Default disable Choices : disable, , underscores
   */
  override def YkindProjector = List("-Ykind-projector")

  /**
   * Use legacy (pre 3.3.0) implementation of lazy vals.
   */
  def YlegacyLazyVals = List("-Ylegacy-lazy-vals")

  /**
   * Show raw StackOverflow stacktraces, instead of decoding them into triggering operations.
   */
  override def YnoDecodeStacktraces = List("-Yno-decode-stacktraces")

  /**
   * Show raw error messages, instead of enriching them with contextual information.
   */
  def YnoEnrichErrorMessages = List("-Yno-enrich-error-messages")

  def YnoExperimental = List("-Yno-experimental")

  /**
   * Suppress generation of generic signatures for Java.
   */
  override def YnoGenericSignatures = List("-Yno-generic-signatures")

  /**
   * Disable kind polymorphism.
   */
  override def YnoKindPolymorphism = List("-Yno-kind-polymorphism")

  /**
   * Used to only generate the TASTy file without the classfiles
   */
  def YoutputOnlyTasty = List("-Youtput-only-tasty")

  /**
   * print nesting levels of symbols and type variables.
   */
  def YprintLevel = List("-Yprint-level")

  /**
   * Prints the generated TASTY to stdout.
   */
  def YprintTasty = List("-Yprint-tasty")

  /**
   * Read documentation from tasty.
   */
  def YreadDocs = List("-Yread-docs")

  /**
   * Run basic rechecking (internal test only).
   */
  def YrecheckTest = List("-Yrecheck-test")

  /**
   * Warn if an operator is defined without a @targetName annotation.
   */
  override def YrequireTargetName = List("-Yrequire-targetName")

  /**
   * Resolve term conflicts Default error Choices : package, object, error
   */
  override def YresolveTermConflict = List("-Yresolve-term-conflict")

  /**
   * Ensure safe initialization of objects.
   */
  override def YsafeInit = List("-Ysafe-init")

  /**
   * Check safe initialization of global objects.
   */
  def YsafeInitGlobal = List("-Ysafe-init-global")

  /**
   * 2-unpickler  Control where we may get Scala 2 symbols from. This is either "always", "never", or a classpath. Default always
   */
  override def Yscala = List("-Yscala")
}
