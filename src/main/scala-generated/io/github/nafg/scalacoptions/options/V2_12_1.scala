// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_1 extends V2_12_1_+ {
  /**
   * A text file containing compiler arguments (options and source files)
   */
  override def `@`(file: String) = List("@" + file)

  /**
   * Emit warning and location for usages of deprecated APIs.
   */
  override def deprecation = List("-deprecation")

  /**
   * Explain type errors in more detail.
   */
  override def explaintypes = List("-explaintypes")

  /**
   * Emit warning and location for usages of features that should be imported explicitly.
   */
  override def feature = List("-feature")

  /**
   * Set level of generated debugging info. Choices: (none,source,line,vars,notailcalls), default: vars.
   */
  override def g(level: String) = List("-g:" + level)

  /**
   * Print a synopsis of standard options
   */
  override def help = List("-help")

  /**
   * Do not use the boot classpath for the scala jars.
   */
  override def nobootcp = List("-nobootcp")

  /**
   * Ignore @specialize annotations.
   */
  override def noSpecialization = List("-no-specialization")

  /**
   * Generate no warnings.
   */
  override def nowarn = List("-nowarn")

  /**
   * Print program with Scala-specific features removed.
   */
  override def print = List("-print")

  /**
   * Target platform for object files. All JVM 1.5 - 1.7 targets are deprecated. Choices: (jvm-1.5,jvm-1.6,jvm-1.7,jvm-1.8), default: jvm-1.8.
   */
  override def target(target: String) = List("-target:" + target)

  /**
   * Enable additional warnings where generated code depends on assumptions.
   */
  override def unchecked = List("-unchecked")

  /**
   * Uniquely tag all identifiers in debugging output.
   */
  override def uniqid = List("-uniqid")

  /**
   * Utilize the java.class.path in classpath resolution.
   */
  override def usejavacp = List("-usejavacp")

  /**
   * Utilize the manifest in classpath resolution.
   */
  override def usemanifestcp = List("-usemanifestcp")

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
   * Wrap field accessors to throw an exception on uninitialized access.
   */
  override def Xcheckinit = List("-Xcheckinit")

  /**
   * Indicates user is a developer - issue warnings about anything which seems amiss
   */
  override def Xdev = List("-Xdev")

  /**
   * Generate no assertions or assumptions.
   */
  override def XdisableAssertions = List("-Xdisable-assertions")

  /**
   * Enable experimental extensions.
   */
  override def Xexperimental = List("-Xexperimental")

  /**
   * Fail the compilation if there are any warnings.
   */
  override def XfatalWarnings = List("-Xfatal-warnings")

  /**
   * Retains pre 2.10 behavior of less aggressive truncation of least upper bounds.
   */
  override def XfullLubs = List("-Xfull-lubs")

  /**
   * Turn on future language features.
   */
  override def Xfuture = List("-Xfuture")

  /**
   * Print a message when reification creates a free term.
   */
  override def XlogFreeTerms = List("-Xlog-free-terms")

  /**
   * Print a message when reification resorts to generating a free type.
   */
  override def XlogFreeTypes = List("-Xlog-free-types")

  /**
   * Print a message whenever an implicit conversion is inserted.
   */
  override def XlogImplicitConversions = List("-Xlog-implicit-conversions")

  /**
   * Show more detail on why some implicits are not applicable.
   */
  override def XlogImplicits = List("-Xlog-implicits")

  /**
   * Print a message when a reflective method call is generated
   */
  override def XlogReflectiveCalls = List("-Xlog-reflective-calls")

  /**
   * Generate forwarder methods in classes inhering concrete methods from traits. Default: `true', `help' to list choices.
   */
  override def XmixinForceForwarders(mode: String) = List("-Xmixin-force-forwarders:" + mode)

  /**
   * Do not generate static forwarders in mirror classes.
   */
  override def XnoForwarders = List("-Xno-forwarders")

  /**
   * Do not use JLine for editing.
   */
  override def Xnojline = List("-Xnojline")

  /**
   * Don't perform exhaustivity/unreachability analysis. Also, ignore @switch annotation.
   */
  override def XnoPatmatAnalysis = List("-Xno-patmat-analysis")

  /**
   * Disable handling of \\u unicode escapes.
   */
  override def XnoUescape = List("-Xno-uescape")

  /**
   * Print a synopsis of loaded plugins.
   */
  override def XpluginList = List("-Xplugin-list")

  /**
   * Print tree positions, as offsets.
   */
  override def XprintPos = List("-Xprint-pos")

  /**
   * Print tree types (debugging option).
   */
  override def XprintTypes = List("-Xprint-types")

  /**
   * Display a prompt after each error (debugging option).
   */
  override def Xprompt = List("-Xprompt")

  /**
   * Specify a custom reporter for compiler messages.
   */
  override def Xreporter(classname: String) = List("-Xreporter", classname)

  /**
   * Compiler stays resident: read source filenames from standard input.
   */
  override def Xresident = List("-Xresident")

  /**
   * Print a synopsis of compiler phases.
   */
  override def XshowPhases = List("-Xshow-phases")

  /**
   * Treat compiler input as Scala source for the specified version, see SI-8126.
   */
  override def Xsource(version: String) = List("-Xsource:" + version)

  /**
   * Don't infer known-unsound types
   */
  override def XstrictInference = List("-Xstrict-inference")

  /**
   * Verify generic signatures in generated bytecode.
   */
  override def Xverify = List("-Xverify")

  /**
   * Print a synopsis of private options.
   */
  override def Y = List("-Y")

  /**
   * Attempt to break cycles encountered during typing
   */
  override def YbreakCycles = List("-Ybreak-cycles")

  /**
   * Use compact tree printer when displaying trees.
   */
  override def YcompactTrees = List("-Ycompact-trees")

  /**
   * Increase the quantity of debugging output.
   */
  override def Ydebug = List("-Ydebug")

  /**
   * Strategy used for translating lambdas into JVM code. Choices: (inline,method), default: method.
   */
  override def Ydelambdafy(strategy: String) = List("-Ydelambdafy:" + strategy)

  /**
   * Do not cache flat classpath representation of classpath elements from jars across compiler instances.
   */
  def YdisableFlatCpCaching = List("-YdisableFlatCpCaching")

  /**
   * Trace all scaladoc activity.
   */
  override def YdocDebug = List("-Ydoc-debug")

  /**
   * Eta-expand varargs methods to T* rather than Seq[T].  This is a temporary option to ease transition. deprecated: This flag is scheduled for removal in 2.12. If you have a case where you need this flag then please report a bug.
   */
  @deprecated("See doc comment", "") def YetaExpandKeepsStar = List("-Yeta-expand-keeps-star")

  /**
   * Generate, validate and output trees using the interactive compiler.
   */
  override def YideDebug = List("-Yide-debug")

  /**
   * Infer types for arguments of overridden methods.
   */
  override def YinferArgumentTypes = List("-Yinfer-argument-types")

  /**
   * Allow inference of by-name types. This is a temporary option to ease transition. See SI-7899. deprecated: This flag is scheduled for removal in 2.12. If you have a case where you need this flag then please report a bug.
   */
  @deprecated("See doc comment", "") def YinferByName = List("-Yinfer-by-name")

  /**
   * Print stack traces when a context issues an error.
   */
  override def YissueDebug = List("-Yissue-debug")

  /**
   * Output information about what classpath is being applied.
   */
  override def YlogClasspath = List("-Ylog-classpath")

  /**
   * Trace essential macro-related activities.
   */
  override def YmacroDebugLite = List("-Ymacro-debug-lite")

  /**
   * Trace all macro-related activities: compilation, generation of synthetics, classloading, expansion, exceptions.
   */
  override def YmacroDebugVerbose = List("-Ymacro-debug-verbose")

  /**
   * Control expansion of macros, useful for scaladoc and presentation compiler. Choices: (normal,none,discard), default: normal.
   */
  override def YmacroExpand(policy: String) = List("-Ymacro-expand:" + policy)

  /**
   * Don't expand macros. Might be useful for scaladoc and presentation compiler, but will crash anything which uses macros and gets past typer. deprecated: Use -Ymacro-expand:none
   */
  @deprecated("See doc comment", "") override def YmacroNoExpand = List("-Ymacro-no-expand")

  /**
   * Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
   */
  override def YnoAdaptedArgs = List("-Yno-adapted-args")

  /**
   * Disable tab-completion in the REPL.
   */
  override def YnoCompletion = List("-Yno-completion")

  /**
   * Suppress generation of generic signatures for Java.
   */
  override def YnoGenericSignatures = List("-Yno-generic-signatures")

  /**
   * Compile without importing scala.*, java.lang.*, or Predef.
   */
  override def YnoImports = List("-Yno-imports")

  /**
   * Compile without importing Predef.
   */
  override def YnoPredef = List("-Yno-predef")

  /**
   * Set the heuristics for inlining decisions. Choices: (at-inline-annotated,everything,default), default: default.
   */
  override def YoptInlineHeuristics(strategy: String) = List("-Yopt-inline-heuristics:" + strategy)

  /**
   * Allow member objects to be overridden.
   */
  override def YoverrideObjects = List("-Yoverride-objects")

  /**
   * Allow vars to be overridden.
   */
  override def YoverrideVars = List("-Yoverride-vars")

  /**
   * Enable partial unification in type constructor inference
   */
  override def YpartialUnification = List("-Ypartial-unification")

  /**
   * Trace pattern matching translation.
   */
  override def YpatmatDebug = List("-Ypatmat-debug")

  /**
   * Trace position validation.
   */
  override def YposDebug = List("-Ypos-debug")

  /**
   * Allow use of the presentation compiler from any thread
   */
  override def YpresentationAnyThread = List("-Ypresentation-any-thread")

  /**
   * Enable debugging output for the presentation compiler.
   */
  override def YpresentationDebug = List("-Ypresentation-debug")

  /**
   * Do not report type errors in sources with syntax errors.
   */
  override def YpresentationStrict = List("-Ypresentation-strict")

  /**
   * Print information about presentation compiler tasks.
   */
  override def YpresentationVerbose = List("-Ypresentation-verbose")

  /**
   * Trace quasiquote-related activities.
   */
  override def YquasiquoteDebug = List("-Yquasiquote-debug")

  /**
   * Use range positions for syntax trees.
   */
  override def Yrangepos = List("-Yrangepos")

  /**
   * Dump the reified trees in copypasteable representation.
   */
  override def YreifyCopypaste = List("-Yreify-copypaste")

  /**
   * Trace reification.
   */
  override def YreifyDebug = List("-Yreify-debug")

  /**
   * Use classes to wrap REPL snippets instead of objects
   */
  override def YreplClassBased = List("-Yrepl-class-based")

  /**
   * Do not use asynchronous code for repl startup
   */
  override def YreplSync = List("-Yrepl-sync")

  /**
   * Resolve term conflicts. Choices: (package,object,error), default: error.
   */
  override def YresolveTermConflict(strategy: String) = List("-Yresolve-term-conflict:" + strategy)

  /**
   * <output style>         Show start and end positions of members
   */
  override def YshowMemberPos = List("-Yshow-member-pos")

  /**
   * Print abbreviated symbol kinds next to symbol names.
   */
  override def YshowSymkinds = List("-Yshow-symkinds")

  /**
   * Print owner identifiers next to symbol names.
   */
  override def YshowSymowners = List("-Yshow-symowners")

  /**
   * Print the AST symbol hierarchy after each phase.
   */
  override def YshowSyms = List("-Yshow-syms")

  /**
   * (Requires -Xprint:) Print detailed ASTs in formatted form.
   */
  override def YshowTrees = List("-Yshow-trees")

  /**
   * (Requires -Xprint:) Print detailed ASTs in compact form.
   */
  override def YshowTreesCompact = List("-Yshow-trees-compact")

  /**
   * (Requires -Xprint:) Print stringifications along with detailed ASTs.
   */
  override def YshowTreesStringified = List("-Yshow-trees-stringified")

  /**
   * Print compiler statistics for specific phases: `_' for all, `-Ystatistics:help' to list choices.
   */
  def Ystatistics(`_,phase,-phase`: String) = List("-Ystatistics:" + `_,phase,-phase`)

  /**
   * Trace all type assignments.
   */
  override def YtyperDebug = List("-Ytyper-debug")

  /**
   * Enable pattern matcher virtualization
   */
  override def Yvirtpatmat = List("-Yvirtpatmat")

  /**
   * Warn if an argument list is modified to match the receiver.
   */
  override def YwarnAdaptedArgs = List("-Ywarn-adapted-args")

  /**
   * Warn when dead code is identified.
   */
  override def YwarnDeadCode = List("-Ywarn-dead-code")

  /**
   * Warn about inaccessible types in method signatures.
   */
  override def YwarnInaccessible = List("-Ywarn-inaccessible")

  /**
   * Warn when a type argument is inferred to be `Any`.
   */
  override def YwarnInferAny = List("-Ywarn-infer-any")

  /**
   * Warn when non-nullary `def f()' overrides nullary `def f'.
   */
  override def YwarnNullaryOverride = List("-Ywarn-nullary-override")

  /**
   * Warn when nullary methods return Unit.
   */
  override def YwarnNullaryUnit = List("-Ywarn-nullary-unit")

  /**
   * Warn when numerics are widened.
   */
  override def YwarnNumericWiden = List("-Ywarn-numeric-widen")

  /**
   * Warn when local and private vals, vars, defs, and types are unused.
   */
  def YwarnUnused = List("-Ywarn-unused")

  /**
   * Warn when imports are unused.
   */
  override def YwarnUnusedImport = List("-Ywarn-unused-import")

  /**
   * Warn when non-Unit expression results are unused.
   */
  override def YwarnValueDiscard = List("-Ywarn-value-discard")
}

object V2_12_1 extends V2_12_1
