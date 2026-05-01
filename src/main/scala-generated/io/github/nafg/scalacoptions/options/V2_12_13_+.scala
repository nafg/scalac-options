// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_13_+ extends V2_12_12_+ {
  /**
   * A text file containing compiler arguments (options and source files) [false]
   */
  override def `@`(file: String) = List("@" + file)

  /**
   * Emit warning and location for usages of deprecated APIs. See also -Wconf. [false]
   */
  override def deprecation = List("-deprecation")

  /**
   * Explain type errors in more detail. [false]
   */
  override def explaintypes = List("-explaintypes")

  /**
   * Emit warning and location for usages of features that should be imported explicitly. See also -Wconf. [false]
   */
  override def feature = List("-feature")

  /**
   * Set level of generated debugging info. (none,source,line,[vars],notailcalls)
   */
  override def g(level: String) = List("-g:" + level)

  /**
   * Print a synopsis of standard options [false]
   */
  override def help = List("-help")

  /**
   * Do not use the boot classpath for the scala jars. [false]
   */
  override def nobootcp = List("-nobootcp")

  /**
   * Ignore @specialize annotations. [false]
   */
  override def noSpecialization = List("-no-specialization")

  /**
   * Generate no warnings. [false]
   */
  override def nowarn = List("-nowarn")

  /**
   * Print program with Scala-specific features removed. [false]
   */
  override def print = List("-print")

  /**
   * The absolute path of the project root directory, usually the git/scm checkout. Used by -Wconf.
   */
  def rootdir(path: String) = List("-rootdir", path)

  /**
   * Enable additional warnings where generated code depends on assumptions. See also -Wconf. [false]
   */
  override def unchecked = List("-unchecked")

  /**
   * Uniquely tag all identifiers in debugging output. [false]
   */
  override def uniqid = List("-uniqid")

  /**
   * Utilize the java.class.path in classpath resolution. [false]
   */
  override def usejavacp = List("-usejavacp")

  /**
   * Utilize the manifest in classpath resolution. [false]
   */
  override def usemanifestcp = List("-usemanifestcp")

  /**
   * Output messages about what the compiler is doing. [false]
   */
  override def verbose = List("-verbose")

  /**
   * Print product version and exit. [false]
   */
  override def version = List("-version")

  /**
   * Configure reporting of compiler warnings; use `help` for details.
   */
  def Wconf(patterns: String) = List("-Wconf:" + patterns)

  /**
   * Print a synopsis of advanced options. [false]
   */
  override def X = List("-X")

  /**
   * Enable the async phase for scala.async.Async.{async,await}. [false]
   */
  override def Xasync = List("-Xasync")

  /**
   * Wrap field accessors to throw an exception on uninitialized access. [false]
   */
  override def Xcheckinit = List("-Xcheckinit")

  /**
   * Indicates user is a developer - issue warnings about anything which seems amiss [false]
   */
  override def Xdev = List("-Xdev")

  /**
   * Generate no assertions or assumptions. [false]
   */
  override def XdisableAssertions = List("-Xdisable-assertions")

  /**
   * Enable experimental extensions. [false]
   */
  override def Xexperimental = List("-Xexperimental")

  /**
   * Fail the compilation if there are any warnings. [false]
   */
  override def XfatalWarnings = List("-Xfatal-warnings")

  /**
   * Retains pre 2.10 behavior of less aggressive truncation of least upper bounds. [false]
   */
  override def XfullLubs = List("-Xfull-lubs")

  /**
   * Turn on future language features. [false]
   */
  override def Xfuture = List("-Xfuture")

  /**
   * Print a message when reification creates a free term. [false]
   */
  override def XlogFreeTerms = List("-Xlog-free-terms")

  /**
   * Print a message when reification resorts to generating a free type. [false]
   */
  override def XlogFreeTypes = List("-Xlog-free-types")

  /**
   * Print a message whenever an implicit conversion is inserted. [false]
   */
  override def XlogImplicitConversions = List("-Xlog-implicit-conversions")

  /**
   * Show more detail on why some implicits are not applicable. [false]
   */
  override def XlogImplicits = List("-Xlog-implicits")

  /**
   * Print a message when a reflective method call is generated [false]
   */
  override def XlogReflectiveCalls = List("-Xlog-reflective-calls")

  /**
   * Generate forwarder methods in classes inhering concrete methods from traits. Default: `true`, `help` to list choices.
   */
  override def XmixinForceForwarders(mode: String) = List("-Xmixin-force-forwarders:" + mode)

  /**
   * Do not generate static forwarders in mirror classes. [false]
   */
  override def XnoForwarders = List("-Xno-forwarders")

  /**
   * Do not use JLine for editing. [false]
   */
  override def Xnojline = List("-Xnojline")

  /**
   * Don't perform exhaustivity/unreachability analysis. Also, ignore @switch annotation. [false]
   */
  override def XnoPatmatAnalysis = List("-Xno-patmat-analysis")

  /**
   * Disable handling of \\u unicode escapes. [false]
   */
  override def XnoUescape = List("-Xno-uescape")

  /**
   * Print a synopsis of loaded plugins. [false]
   */
  override def XpluginList = List("-Xplugin-list")

  /**
   * Print tree positions, as offsets. [false]
   */
  override def XprintPos = List("-Xprint-pos")

  /**
   * Print tree types (debugging option). [false]
   */
  override def XprintTypes = List("-Xprint-types")

  /**
   * Display a prompt after each error (debugging option). [false]
   */
  override def Xprompt = List("-Xprompt")

  /**
   * Specify a custom subclass of FilteringReporter for compiler messages.
   */
  override def Xreporter(classname: String) = List("-Xreporter", classname)

  /**
   * Compiler stays resident: read source filenames from standard input. [false]
   */
  override def Xresident = List("-Xresident")

  /**
   * Print a synopsis of compiler phases. [false]
   */
  override def XshowPhases = List("-Xshow-phases")

  /**
   * Don't infer known-unsound types [false]
   */
  override def XstrictInference = List("-Xstrict-inference")

  /**
   * Verify generic signatures in generated bytecode. [false]
   */
  override def Xverify = List("-Xverify")

  /**
   * Print a synopsis of private options. [false]
   */
  override def Y = List("-Y")

  /**
   * Attempt to break cycles encountered during typing [false]
   */
  override def YbreakCycles = List("-Ybreak-cycles")

  /**
   * Policy for caching class loaders for macros that are dynamically loaded. Default: `none`, `help` to list choices.
   */
  override def YcacheMacroClassLoader(policy: String) = List("-Ycache-macro-class-loader:" + policy)

  /**
   * Policy for caching class loaders for compiler plugins that are dynamically loaded. Default: `none`, `help` to list choices.
   */
  override def YcachePluginClassLoader(policy: String) = List("-Ycache-plugin-class-loader:" + policy)

  /**
   * Use compact tree printer when displaying trees. [false]
   */
  override def YcompactTrees = List("-Ycompact-trees")

  /**
   * Increase the quantity of debugging output. [false]
   */
  override def Ydebug = List("-Ydebug")

  /**
   * Strategy used for translating lambdas into JVM code. (inline,[method],method-ref)
   */
  override def Ydelambdafy(strategy: String) = List("-Ydelambdafy:" + strategy)

  /**
   * Do not cache flat classpath representation of classpath elements from jars across compiler instances. [false]
   */
  override def YdisableFlatCpCaching = List("-Ydisable-flat-cp-caching")

  /**
   * Trace all scaladoc activity. [false]
   */
  override def YdocDebug = List("-Ydoc-debug")

  /**
   * Enable `-Ystatistics` to print hot statistics. [false]
   */
  override def YhotStatisticsEnabled = List("-Yhot-statistics-enabled")

  /**
   * Generate, validate and output trees using the interactive compiler. [false]
   */
  override def YideDebug = List("-Yide-debug")

  /**
   * Infer types for arguments of overridden methods. [false]
   */
  override def YinferArgumentTypes = List("-Yinfer-argument-types")

  /**
   * Print stack traces when a context issues an error. [false]
   */
  override def YissueDebug = List("-Yissue-debug")

  /**
   * Output information about what classpath is being applied. [false]
   */
  override def YlogClasspath = List("-Ylog-classpath")

  /**
   * Trace essential macro-related activities. [false]
   */
  override def YmacroDebugLite = List("-Ymacro-debug-lite")

  /**
   * Trace all macro-related activities: compilation, generation of synthetics, classloading, expansion, exceptions. [false]
   */
  override def YmacroDebugVerbose = List("-Ymacro-debug-verbose")

  /**
   * Control expansion of macros, useful for scaladoc and presentation compiler. ([normal],none,discard)
   */
  override def YmacroExpand(policy: String) = List("-Ymacro-expand:" + policy)

  /**
   * Should fresh names in macros be unique across all compilation units [false]
   */
  override def YmacroGlobalFreshNames = List("-Ymacro-global-fresh-names")

  /**
   * Don't expand macros. Might be useful for scaladoc and presentation compiler, but will crash anything which uses macros and gets past typer. [false] deprecated: Use -Ymacro-expand:none
   */
  @deprecated("See doc comment", "") override def YmacroNoExpand = List("-Ymacro-no-expand")

  /**
   * Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver. [false]
   */
  override def YnoAdaptedArgs = List("-Yno-adapted-args")

  /**
   * Disable tab-completion in the REPL. [false]
   */
  override def YnoCompletion = List("-Yno-completion")

  /**
   * Suppress generation of generic signatures for Java. [false]
   */
  override def YnoGenericSignatures = List("-Yno-generic-signatures")

  /**
   * Compile without importing scala.*, java.lang.*, or Predef. [false]
   */
  override def YnoImports = List("-Yno-imports")

  /**
   * Compile without importing Predef. [false]
   */
  override def YnoPredef = List("-Yno-predef")

  /**
   * Set the heuristics for inlining decisions. (at-inline-annotated,everything,[default])
   */
  override def YoptInlineHeuristics(strategy: String) = List("-Yopt-inline-heuristics:" + strategy)

  /**
   * Allow member objects to be overridden. [false]
   */
  override def YoverrideObjects = List("-Yoverride-objects")

  /**
   * Allow vars to be overridden. [false]
   */
  override def YoverrideVars = List("-Yoverride-vars")

  /**
   * Enable partial unification in type constructor inference [false]
   */
  override def YpartialUnification = List("-Ypartial-unification")

  /**
   * Trace pattern matching translation. [false]
   */
  override def YpatmatDebug = List("-Ypatmat-debug")

  /**
   * Trace position validation. [false]
   */
  override def YposDebug = List("-Ypos-debug")

  /**
   * Allow use of the presentation compiler from any thread [false]
   */
  override def YpresentationAnyThread = List("-Ypresentation-any-thread")

  /**
   * Enable debugging output for the presentation compiler. [false]
   */
  override def YpresentationDebug = List("-Ypresentation-debug")

  /**
   * Enables legacy code in the classfile parser to locate a .scala file in the output directories corresponding to the SourceFile attribute .class file. [false]
   */
  override def YpresentationLocateSourceFile = List("-Ypresentation-locate-source-file")

  /**
   * Do not report type errors in sources with syntax errors. [false]
   */
  override def YpresentationStrict = List("-Ypresentation-strict")

  /**
   * Print information about presentation compiler tasks. [false]
   */
  override def YpresentationVerbose = List("-Ypresentation-verbose")

  /**
   * Enable profiling. [false]
   */
  override def YprofileEnabled = List("-Yprofile-enabled")

  /**
   * Trace quasiquote-related activities. [false]
   */
  override def YquasiquoteDebug = List("-Yquasiquote-debug")

  /**
   * Use range positions for syntax trees. [false]
   */
  override def Yrangepos = List("-Yrangepos")

  /**
   * Dump the reified trees in copypasteable representation. [false]
   */
  override def YreifyCopypaste = List("-Yreify-copypaste")

  /**
   * Trace reification. [false]
   */
  override def YreifyDebug = List("-Yreify-debug")

  /**
   * Use classes to wrap REPL snippets instead of objects [false]
   */
  override def YreplClassBased = List("-Yrepl-class-based")

  /**
   * Do not use asynchronous code for repl startup [false]
   */
  override def YreplSync = List("-Yrepl-sync")

  /**
   * In the code that wraps REPL snippets, use magic imports rather than nesting wrapper object/classes [false]
   */
  override def YreplUseMagicImports = List("-Yrepl-use-magic-imports")

  /**
   * Resolve term conflicts. (package,object,[error])
   */
  override def YresolveTermConflict(strategy: String) = List("-Yresolve-term-conflict:" + strategy)

  /**
   * Print abbreviated symbol kinds next to symbol names. [false]
   */
  override def YshowSymkinds = List("-Yshow-symkinds")

  /**
   * Print owner identifiers next to symbol names. [false]
   */
  override def YshowSymowners = List("-Yshow-symowners")

  /**
   * Print the AST symbol hierarchy after each phase. [false]
   */
  override def YshowSyms = List("-Yshow-syms")

  /**
   * (Requires -Xprint:) Print detailed ASTs in formatted form. [false]
   */
  override def YshowTrees = List("-Yshow-trees")

  /**
   * (Requires -Xprint:) Print detailed ASTs in compact form. [false]
   */
  override def YshowTreesCompact = List("-Yshow-trees-compact")

  /**
   * (Requires -Xprint:) Print stringifications along with detailed ASTs. [false]
   */
  override def YshowTreesStringified = List("-Yshow-trees-stringified")

  /**
   * Record references to in unit.depends. Deprecated feature that supports SBT 0.13 with incOptions.withNameHashing(false) only. [true]
   */
  override def YtrackDependencies = List("-Ytrack-dependencies")

  /**
   * Trace all type assignments. [false]
   */
  override def YtyperDebug = List("-Ytyper-debug")

  /**
   * Enable pattern matcher virtualization [false]
   */
  override def Yvirtpatmat = List("-Yvirtpatmat")

  /**
   * Warn if an argument list is modified to match the receiver. [false]
   */
  override def YwarnAdaptedArgs = List("-Ywarn-adapted-args")

  /**
   * Warn when dead code is identified. [false]
   */
  override def YwarnDeadCode = List("-Ywarn-dead-code")

  /**
   * Warn when more than one implicit parameter section is defined. [false]
   */
  override def YwarnExtraImplicit = List("-Ywarn-extra-implicit")

  /**
   * Warn about inaccessible types in method signatures. [false]
   */
  override def YwarnInaccessible = List("-Ywarn-inaccessible")

  /**
   * Warn when a type argument is inferred to be `Any`. [false]
   */
  override def YwarnInferAny = List("-Ywarn-infer-any")

  /**
   * Enable lint warnings on macro expansions. Default: `before`, `help` to list choices.
   */
  override def YwarnMacros(mode: String) = List("-Ywarn-macros:" + mode)

  /**
   * Warn when non-nullary `def f()' overrides nullary `def f'. [false]
   */
  override def YwarnNullaryOverride = List("-Ywarn-nullary-override")

  /**
   * Warn when nullary methods return Unit. [false]
   */
  override def YwarnNullaryUnit = List("-Ywarn-nullary-unit")

  /**
   * Warn when numerics are widened. [false]
   */
  override def YwarnNumericWiden = List("-Ywarn-numeric-widen")

  /**
   * Warn when an implicit resolves to an enclosing self-definition. [false]
   */
  override def YwarnSelfImplicit = List("-Ywarn-self-implicit")

  /**
   * Warn when imports are unused. [false]
   */
  override def YwarnUnusedImport = List("-Ywarn-unused-import")

  /**
   * Warn when non-Unit expression results are unused. [false]
   */
  override def YwarnValueDiscard = List("-Ywarn-value-discard")
}
