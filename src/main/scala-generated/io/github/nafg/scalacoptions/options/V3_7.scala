// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_7 extends V3 {
  /**
   * Specify where to find user class files. Default .
   */
  override def classpath = List("-classpath")

  /**
   * Colored output Default always Choices : always, never
   */
  override def color = List("-color")

  /**
   * List of regexes for packages, classes and modules to exclude from coverage.
   */
  def coverageExcludeClasslikes = List("-coverage-exclude-classlikes")

  /**
   * List of regexes for files to exclude from coverage.
   */
  def coverageExcludeFiles = List("-coverage-exclude-files")

  /**
   * Destination for coverage classfiles and instrumentation data.
   */
  def coverageOut = List("-coverage-out")

  /**
   * Pass -D<property=value> directly to the runtime system.
   */
  def D(`property=value`: String) = List("-D" + `property=value`)

  /**
   * Specify character encoding used by source files. Default UTF-8
   */
  override def encoding(encoding: String) = List("-encoding", encoding)

  /**
   * Annotate all top-level definitions with @experimental. This enables the use of experimental features anywhere in the project.
   */
  def experimental = List("-experimental")

  /**
   * Explain cyclic reference errors in more detail.
   */
  def explainCyclic = List("-explain-cyclic")

  /**
   * Pass -J<flag> directly to the runtime system.
   */
  def J(flag: String) = List("-J" + flag)

  /**
   * Compile code with classes specific to the given version of the Java platform available on the classpath and emit bytecode for this version. Corresponds to -release flag in javac. Choices : 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
   */
  def javaOutputVersion = List("-java-output-version")

  /**
   * Set page width Default 20000
   */
  override def pagewidth = List("-pagewidth")

  /**
   * Enable the use of preview features anywhere in the project.
   */
  def preview = List("-preview")

  /**
   * Show source code line numbers.
   */
  override def printLines = List("-print-lines")

  /**
   * The code will be run on REPL startup.
   */
  def replInitScript = List("-repl-init-script")

  /**
   * Quit REPL after evaluating the init script.
   */
  def replQuitAfterInit = List("-repl-quit-after-init")

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
   * Enable all warning settings.
   */
  def Wall = List("-Wall")

  /**
   * Configure compiler warnings.
   */
  def Wconf = List("-Wconf")

  /**
   * Warn when a comment ambiguously assigned to multiple enum cases is discarded.
   */
  def WenumCommentDiscard = List("-Wenum-comment-discard")

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
   * Ensure safe initialization of objects.
   */
  def WsafeInit = List("-Wsafe-init")

  /**
   * Enable or disable specific `shadow` warnings Choices :  - all,  - private-shadow : 	Warn if a private field or class parameter shadows a superclass field,  - type-parameter-shadow : 	Warn when a type parameter shadows a type already in the scope
   */
  def Wshadow = List("-Wshadow")

  /**
   * Warn an inline methods has references to non-stable binary APIs.
   */
  def WunstableInlineAccessors = List("-WunstableInlineAccessors")

  /**
   * Enable or disable specific `unused` warnings Choices :  - nowarn,  - all,  - imports : 	Warn if an import selector is not referenced.,  - privates : 	Warn if a private member is unused,  - locals : 	Warn if a local definition is unused,  - explicits : 	Warn if an explicit parameter is unused,  - implicits : 	Warn if an implicit parameter is unused,  - params : 	Enable -Wunused:explicits,implicits,  - patvars : 	Warn if a variable bound in a pattern is unused,  - linted : 	Enable -Wunused:imports,privates,locals,implicits,  - strict-no-implicit-warn : 	Same as -Wunused:imports, only for imports of explicit named members. 	NOTE : This overrides -Wunused:imports and NOT set by -Wunused:all,  - unsafe-warn-patvars : 	Deprecated alias for `patvars`
   */
  def Wunused = List("-Wunused")

  /**
   * Warn when non-Unit expression results are unused.
   */
  def WvalueDiscard = List("-Wvalue-discard")

  /**
   * Allow outline TASTy to be loaded with the -from-tasty option.
   */
  def XallowOutlineFromTasty = List("-Xallow-outline-from-tasty")

  /**
   * Cook the documentation (type check `@usecase`, etc.)
   */
  def XcookDocs = List("-Xcook-docs")

  /**
   * Show debug info when quote pattern match fails
   */
  def XdebugMacros = List("-Xdebug-macros")

  /**
   * Drop documentation when scanning source files.
   */
  def XdropDocs = List("-Xdrop-docs")

  /**
   * Dump the generated bytecode to .class files (useful for reflective compilation that utilizes in-memory classloaders).
   */
  def XdumpClasses = List("-Xdump-classes")

  /**
   * Destination to write generated .tasty files to for use in pipelined compilation.
   */
  def XearlyTastyOutput = List("-Xearly-tasty-output")

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

  /**
   * compression level to use when writing jar files Default -1 Choices : -1..9
   */
  def XjarCompressionLevel = List("-Xjar-compression-level")

  /**
   * Pickler phase should compute TASTy for .java defined symbols for use by build tools
   */
  def XjavaTasty = List("-Xjava-tasty")

  /**
   * Allow `*` as type lambda placeholder to be compatible with kind projector. When invoked as -Xkind-projector:underscores will repurpose `_` to be a type parameter placeholder, this will disable usage of underscore as a wildcard. Default disable Choices : disable, , underscores
   */
  def XkindProjector = List("-Xkind-projector")

  /**
   * Option deprecated. Use -Wshadow to enable shadowing lints. Scheduled for removal. Enable or disable specific warnings
   */
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
   * Show raw error messages, instead of enriching them with contextual information.
   */
  def XnoEnrichErrorMessages = List("-Xno-enrich-error-messages")

  /**
   * Suppress generation of generic signatures for Java.
   */
  def XnoGenericSignatures = List("-Xno-generic-signatures")

  /**
   * Path to search for plugin archives. Default misc/scala-devel/plugins
   */
  override def Xpluginsdir = List("-Xpluginsdir")

  /**
   * Read documentation from tasty.
   */
  def XreadDocs = List("-Xread-docs")

  /**
   * Resolve term conflicts Default error Choices : package, object, error
   */
  def XresolveTermConflict = List("-Xresolve-term-conflict")

  def XuncheckedJavaOutputVersion = List("-Xunchecked-java-output-version")

  /**
   * maximum worker threads for backend Default 1 Choices : 1..16
   */
  def YbackendParallelism = List("-Ybackend-parallelism")

  /**
   * backend threads worker queue size Default 0 Choices : 0..1000
   */
  def YbackendWorkerQueue = List("-Ybackend-worker-queue")

  /**
   * Enable best-effort compilation attempting to produce betasty to the META-INF/best-effort directory, regardless of errors, as part of the pickler phase.
   */
  def YbestEffort = List("-Ybest-effort")

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
   * Option deprecated. Use -Xcook-docs instead. Cook the documentation (type check `@usecase`, etc.)
   */
  def YcookDocs = List("-Ycook-docs")

  /**
   * Print the stack trace when a cyclic reference error occurs.
   */
  def YdebugCyclic = List("-Ydebug-cyclic")

  /**
   * Option deprecated. Use -Xdebug-macros instead. Show debug info when quote pattern match fails
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
   * Option deprecated. Use -Xdrop-docs instead. Drop documentation when scanning source files.
   */
  def YdropDocs = List("-Ydrop-docs")

  /**
   * Option deprecated. Use -Xdump-classes instead. Dump the generated bytecode to .class files (useful for reflective compilation that utilizes in-memory classloaders).
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
   * Option deprecated. Use -Xjar-compression-level instead. compression level to use when writing jar files Default -1 Choices : -1..9
   */
  def YjarCompressionLevel = List("-Yjar-compression-level")

  /**
   * Option deprecated. Use -Xkind-projector instead. Allow `*` as type lambda placeholder to be compatible with kind projector. When invoked as -Ykind-projector:underscores will repurpose `_` to be a type parameter placeholder, this will disable usage of underscore as a wildcard. Default disable Choices : disable, , underscores
   */
  override def YkindProjector = List("-Ykind-projector")

  /**
   * Use legacy (pre 3.3.0) implementation of lazy vals.
   */
  def YlegacyLazyVals = List("-Ylegacy-lazy-vals")

  /**
   * Option deprecated. Use -Xno-enrich-error-messages instead. Show raw StackOverflow stacktraces, instead of decoding them into triggering operations.
   */
  override def YnoDecodeStacktraces = List("-Yno-decode-stacktraces")

  /**
   * Option deprecated. Use -Xno-enrich-error-messages instead. Show raw error messages, instead of enriching them with contextual information.
   */
  def YnoEnrichErrorMessages = List("-Yno-enrich-error-messages")

  /**
   * Disable turning nullable Java return types and parameter types into flexible types, which behave like abstract types with a nullable lower bound and non-nullable upper bound.
   */
  def YnoFlexibleTypes = List("-Yno-flexible-types")

  /**
   * Option deprecated. Use -Xno-generic-signatures instead. Suppress generation of generic signatures for Java.
   */
  override def YnoGenericSignatures = List("-Yno-generic-signatures")

  /**
   * Option deprecated. Scheduled for removal. Disable kind polymorphism. (This flag has no effect)
   */
  override def YnoKindPolymorphism = List("-Yno-kind-polymorphism")

  /**
   * Do not suspend units, e.g. when calling a macro defined in the same run. This will error instead of suspending.
   */
  def YnoSuspendedUnits = List("-Yno-suspended-units")

  /**
   * Option deprecated. Scheduled for removal. Used to only generate the TASTy file without the classfiles
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
   * Capture trace of compilation in JSON Chrome Trace format to the specified file. This option requires -Yprofile-enabled. The output file can be visualized using https://ui.perfetto.dev/.
   */
  def YprofileTrace = List("-Yprofile-trace")

  /**
   * Option deprecated. Use -Xread-docs instead. Read documentation from tasty.
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
   * Option deprecated. Use -Xresolve-term-conflict instead. Resolve term conflicts Default error Choices : package, object, error
   */
  override def YresolveTermConflict = List("-Yresolve-term-conflict")

  /**
   * Option deprecated. Use -Wsafe-init instead. Ensure safe initialization of objects.
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

  /**
   * Self-test for pickling -print-tasty output; should be used with -Ytest-pickler.
   */
  def YtestPicklerCheck = List("-Ytest-pickler-check")

  /**
   * Allow to compile using best-effort tasty files. If such file is used, the compiler will stop after the pickler phase.
   */
  def YwithBestEffortTasty = List("-Ywith-best-effort-tasty")
}
