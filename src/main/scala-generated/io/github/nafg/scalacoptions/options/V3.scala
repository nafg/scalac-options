// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3 extends Common {
  /**
   * A text file containing compiler arguments (options and source files).
   */
  override def `@`(file: String) = List("@" + file)

  /**
   * Override location of bootstrap class files.
   */
  def bootclasspath = List("-bootclasspath")

  def classpath = List("-classpath")

  def color = List("-color")

  /**
   * Destination for generated classfiles.
   */
  def d = List("-d")

  /**
   * Emit warning and location for usages of deprecated APIs.
   */
  override def deprecation = List("-deprecation")

  /**
   * Explain errors in more detail.
   */
  def explain = List("-explain")

  /**
   * Explain type errors in more detail (deprecated, use -explain instead).
   */
  def explainTypes = List("-explain-types")

  /**
   * Override location of installed extensions.
   */
  def extdirs = List("-extdirs")

  /**
   * Emit warning and location for usages of features that should be imported explicitly.
   */
  override def feature = List("-feature")

  /**
   * Compile classes from tasty files. The arguments are .tasty or .jar files.
   */
  def fromTasty = List("-from-tasty")

  /**
   * Print a synopsis of standard options.
   */
  override def help = List("-help")

  /**
   * Together with -rewrite, remove {...} syntax when possible due to significant indentation.
   */
  def indent = List("-indent")

  /**
   * Override java boot classpath.
   */
  def javabootclasspath = List("-javabootclasspath")

  /**
   * Override java extdirs classpath.
   */
  def javaextdirs = List("-javaextdirs")

  /**
   * Require `then` and `do` in control expressions.
   */
  def newSyntax = List("-new-syntax")

  /**
   * Require classical {...} syntax, indentation is not significant.
   */
  def noIndent = List("-no-indent")

  /**
   * Silence all warnings.
   */
  override def nowarn = List("-nowarn")

  /**
   * Require `(...)` around conditions.
   */
  def oldSyntax = List("-old-syntax")

  /**
   * Pass an option to a plugin, e.g. -P:<plugin>:<opt>
   */
  def P = List("-P")

  def pagewidth = List("-pagewidth")

  def printLines = List("-print-lines")

  /**
   * Prints the raw tasty.
   */
  def printTasty = List("-print-tasty")

  /**
   * The source repository of your project.
   */
  def projectUrl = List("-project-url")

  /**
   * When used in conjunction with a `...-migration` source version, rewrites sources to migrate to new version.
   */
  def rewrite = List("-rewrite")

  /**
   * Compile in Scala.js mode (requires scalajs-library.jar on the classpath).
   */
  def scalajs = List("-scalajs")

  def scalajsGenStaticForwardersForNonTopLevelObjects = List("-scalajs-genStaticForwardersForNonTopLevelObjects")

  def scalajsMapSourceURI = List("-scalajs-mapSourceURI")

  /**
   * Specify an alternative output directory for SemanticDB files.
   */
  def semanticdbTarget = List("-semanticdb-target")

  def source = List("-source")

  /**
   * Specify location(s) of source files.
   */
  def sourcepath = List("-sourcepath")

  def sourceroot = List("-sourceroot")

  /**
   * Enable additional warnings where generated code depends on assumptions.
   */
  override def unchecked = List("-unchecked")

  /**
   * Uniquely tag all identifiers in debugging output.
   */
  override def uniqid = List("-uniqid")

  /**
   * Output messages about what the compiler is doing.
   */
  override def verbose = List("-verbose")

  /**
   * Print product version and exit.
   */
  override def version = List("-version")

  /**
   * Print a synopsis of advanced options.
   */
  override def X = List("-X")

  /**
   * Check some invariants of macro generated code while expanding macros
   */
  def XcheckMacros = List("-Xcheck-macros")

  def XignoreScala = List("-Xignore-scala")

  def XimportSuggestionTimeout = List("-Ximport-suggestion-timeout")

  def XmaxInlinedTrees = List("-Xmax-inlined-trees")

  def XmaxInlines = List("-Xmax-inlines")

  /**
   * Warn about constructs whose behavior may have changed since version.
   */
  def Xmigration = List("-Xmigration")

  def XmixinForceForwarders = List("-Xmixin-force-forwarders")

  /**
   * Do not generate static forwarders in mirror classes.
   */
  override def XnoForwarders = List("-Xno-forwarders")

  /**
   * Load a plugin from each classpath.
   */
  def Xplugin = List("-Xplugin")

  /**
   * Disable plugins by name.
   */
  def XpluginDisable = List("-Xplugin-disable")

  /**
   * Print a synopsis of loaded plugins.
   */
  override def XpluginList = List("-Xplugin-list")

  /**
   * Abort if a named plugin is not loaded.
   */
  def XpluginRequire = List("-Xplugin-require")

  def Xpluginsdir = List("-Xpluginsdir")

  /**
   * Print changed parts of the tree since last print.
   */
  def XprintDiff = List("-Xprint-diff")

  /**
   * Print changed parts of the tree since last print including deleted parts.
   */
  def XprintDiffDel = List("-Xprint-diff-del")

  /**
   * Show where inlined code comes from.
   */
  def XprintInline = List("-Xprint-inline")

  /**
   * Show when code is suspended until macros are compiled.
   */
  def XprintSuspension = List("-Xprint-suspension")

  /**
   * Print tree types (debugging option).
   */
  def XprintTypes = List("-Xprint-types")

  /**
   * Display a prompt after each error (debugging option).
   */
  override def Xprompt = List("-Xprompt")

  /**
   * Do not display definitions in REPL.
   */
  def XreplDisableDisplay = List("-Xrepl-disable-display")

  /**
   * Store information in SemanticDB.
   */
  def Xsemanticdb = List("-Xsemanticdb")

  /**
   * Verify generic signatures in generated bytecode.
   */
  def XverifySignatures = List("-Xverify-signatures")

  /**
   * Retains the Scala2 behavior of using Wiki Syntax in Scaladoc.
   */
  def XwikiSyntax = List("-Xwiki-syntax")

  /**
   * Print a synopsis of private options.
   */
  override def Y = List("-Y")

  /**
   * Check the tree at the end of
   */
  def Ycheck = List("-Ycheck")

  /**
   * Check exhaustivity and redundancy of all pattern matching (used for testing the algorithm).
   */
  def YcheckAllPatmat = List("-Ycheck-all-patmat")

  /**
   * Check that symbols and their defining trees have modifiers in sync.
   */
  def YcheckMods = List("-Ycheck-mods")

  /**
   * Check that compiled program does not contain vars that can be accessed from a global root.
   */
  def YcheckReentrant = List("-Ycheck-reentrant")

  /**
   * Increase the quantity of debugging output.
   */
  def Ydebug = List("-Ydebug")

  /**
   * Print the stack trace when any error is caught.
   */
  def YdebugError = List("-Ydebug-error")

  /**
   * Print all flags of definitions.
   */
  def YdebugFlags = List("-Ydebug-flags")

  /**
   * Print a stacktrace when a required symbol is missing.
   */
  def YdebugMissingRefs = List("-Ydebug-missing-refs")

  /**
   * Show internal representation of names.
   */
  def YdebugNames = List("-Ydebug-names")

  /**
   * Show full source positions including spans.
   */
  def YdebugPos = List("-Ydebug-pos")

  /**
   * Trace core operations.
   */
  def YdebugTrace = List("-Ydebug-trace")

  def YdebugTreeWithId = List("-Ydebug-tree-with-id")

  /**
   * Print the stack trace when a TypeError is caught
   */
  def YdebugTypeError = List("-Ydebug-type-error")

  /**
   * Show detailed internal compiler stats (needs Stats.enabled to be set to true).
   */
  def YdetailedStats = List("-Ydetailed-stats")

  /**
   * Do not cache flat classpath representation of classpath elements from jars across compiler instances.
   */
  def YdisableFlatCpCaching = List("-YdisableFlatCpCaching")

  /**
   * For every compiled foo.scala, output the API representation and dependencies used for sbt incremental compilation in foo.inc, implies -Yforce-sbt-phases.
   */
  def YdumpSbtInc = List("-Ydump-sbt-inc")

  /**
   * When explaining type errors, show types at a lower level.
   */
  def YexplainLowlevel = List("-Yexplain-lowlevel")

  /**
   * Make reference types non-nullable. Nullable types can be expressed with unions: e.g. String|Null.
   */
  def YexplicitNulls = List("-Yexplicit-nulls")

  /**
   * Run the phases used by sbt for incremental compilation (ExtractDependencies and ExtractAPI) even if the compiler is ran outside of sbt, for debugging.
   */
  def YforceSbtPhases = List("-Yforce-sbt-phases")

  def YfromTastyIgnoreList = List("-Yfrom-tasty-ignore-list")

  /**
   * Add instrumentation code that counts allocations and closure creations.
   */
  def Yinstrument = List("-Yinstrument")

  /**
   * Add instrumentation code that counts method calls; needs -Yinstrument to be set, too.
   */
  def YinstrumentDefs = List("-Yinstrument-defs")

  def YkindProjector = List("-Ykind-projector")

  /**
   * Log operations during
   */
  def Ylog = List("-Ylog")

  /**
   * Output information about what classpath is being applied.
   */
  def YlogClasspath = List("-Ylog-classpath")

  def YnoDecodeStacktraces = List("-Yno-decode-stacktraces")

  /**
   * Throw an exception on deep subtyping call stacks.
   */
  def YnoDeepSubtypes = List("-Yno-deep-subtypes")

  /**
   * Assert no namedtype is bound twice (should be enabled only if program is error-free).
   */
  def YnoDoubleBindings = List("-Yno-double-bindings")

  /**
   * Compile without importing scala.*, java.lang.*, or Predef.
   */
  override def YnoImports = List("-Yno-imports")

  def YnoKindPolymorphism = List("-Yno-kind-polymorphism")

  /**
   * Disable all pattern matching optimizations.
   */
  def YnoPatmatOpt = List("-Yno-patmat-opt")

  /**
   * Compile without importing Predef.
   */
  override def YnoPredef = List("-Yno-predef")

  /**
   * Pretty-print using a plain printer.
   */
  def YplainPrinter = List("-Yplain-printer")

  /**
   * When printing trees, print some extra information useful for debugging.
   */
  def YprintDebug = List("-Yprint-debug")

  /**
   * When printing trees, print owners of definitions.
   */
  def YprintDebugOwners = List("-Yprint-debug-owners")

  /**
   * Show tree positions.
   */
  def YprintPos = List("-Yprint-pos")

  /**
   * Show symbol definitions positions.
   */
  def YprintPosSyms = List("-Yprint-pos-syms")

  /**
   * When printing trees print info in symbols instead of corresponding info in trees.
   */
  def YprintSyms = List("-Yprint-syms")

  /**
   * Where to send profiling output - specify a file, default is to the console.
   */
  def YprofileDestination = List("-Yprofile-destination")

  /**
   * Enable profiling.
   */
  def YprofileEnabled = List("-Yprofile-enabled")

  /**
   * Enable profiling for a phase using an external tool hook. Generally only useful for a single phase.
   */
  def YprofileExternalTool = List("-Yprofile-external-tool")

  /**
   * Run a GC between phases - this allows heap size to be accurate at the expense of more time. Specify a list of phases, or *
   */
  def YprofileRunGc = List("-Yprofile-run-gc")

  def YrequireTargetName = List("-Yrequire-targetName")

  def YresolveTermConflict = List("-Yresolve-term-conflict")

  /**
   * Retain trees for top-level classes, accessible from ClassSymbol#tree
   */
  def YretainTrees = List("-Yretain-trees")

  def YsafeInit = List("-Ysafe-init")

  def Yscala = List("-Yscala")

  /**
   * Don't suppress exceptions thrown during tree printing.
   */
  def YshowPrintErrors = List("-Yshow-print-errors")

  /**
   * Also show follow-on errors and warnings that are normally suppressed.
   */
  def YshowSuppressedErrors = List("-Yshow-suppressed-errors")

  /**
   * Uniquely tag all tree nodes in debugging output.
   */
  def YshowTreeIds = List("-Yshow-tree-ids")

  /**
   * Print type variables with their bounds.
   */
  def YshowVarBounds = List("-Yshow-var-bounds")

  /**
   * Skip
   */
  def Yskip = List("-Yskip")

  /**
   * Stop after
   */
  def YstopAfter = List("-Ystop-after")

  /**
   * Stop before
   */
  def YstopBefore = List("-Ystop-before")

  /**
   * Self-test for pickling functionality; should be used with -Ystop-after:pickler.
   */
  def YtestPickler = List("-Ytest-pickler")
}
