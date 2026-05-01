// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_11 extends V2 {
  /**
   * A text file containing compiler arguments (options and source files)
   */
  override def `@`(file: String) = List("@" + file)

  /**
   * destination for generated classfiles.
   */
  override def d(`directory|jar`: String) = List("-d", `directory|jar`)

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
   * Set level of generated debugging info. (none,source,line,vars,notailcalls) default:vars
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
   * Generates faster bytecode by applying optimisations to the program
   */
  def optimise = List("-optimise")

  /**
   * Print program with Scala-specific features removed.
   */
  override def print = List("-print")

  def target(target: String) = List("-target:" + target)

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
   * <n>              Calls to @elidable methods are omitted if method priority is lower than argument
   */
  override def XelideBelow = List("-Xelide-below")

  /**
   * Enable experimental extensions.
   */
  def Xexperimental = List("-Xexperimental")

  /**
   * Fail the compilation if there are any warnings.
   */
  def XfatalWarnings = List("-Xfatal-warnings")

  /**
   * Retains pre 2.10 behavior of less aggressive truncation of least upper bounds.
   */
  def XfullLubs = List("-Xfull-lubs")

  /**
   * Turn on future language features.
   */
  def Xfuture = List("-Xfuture")

  /**
   * <file>  Generate the phase graphs (outputs .dot files) to fileX.dot.
   */
  def XgeneratePhaseGraph = List("-Xgenerate-phase-graph")

  /**
   * Print a message when reification creates a free term.
   */
  def XlogFreeTerms = List("-Xlog-free-terms")

  /**
   * Print a message when reification resorts to generating a free type.
   */
  def XlogFreeTypes = List("-Xlog-free-types")

  /**
   * Print a message whenever an implicit conversion is inserted.
   */
  def XlogImplicitConversions = List("-Xlog-implicit-conversions")

  /**
   * Show more detail on why some implicits are not applicable.
   */
  def XlogImplicits = List("-Xlog-implicits")

  /**
   * Print a message when a reflective method call is generated
   */
  def XlogReflectiveCalls = List("-Xlog-reflective-calls")

  /**
   * <path>            Class for manifest's Main-Class entry (only useful with -d <jar>)
   */
  def XmainClass = List("-Xmain-class")

  /**
   * <n>       Maximum filename length for generated classes
   */
  def XmaxClassfileName = List("-Xmax-classfile-name")

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
  def XnoUescape = List("-Xno-uescape")

  /**
   * Print a synopsis of loaded plugins.
   */
  override def XpluginList = List("-Xplugin-list")

  /**
   * Print out program after <phases>
   */
  def Xprint(phases: String) = List("-Xprint:" + phases)

  /**
   * [:phases]         Log internal icode to *.icode files after <phases> (default: icode)
   */
  def XprintIcode = List("-Xprint-icode")

  /**
   * Print tree positions, as offsets.
   */
  def XprintPos = List("-Xprint-pos")

  /**
   * Print tree types (debugging option).
   */
  def XprintTypes = List("-Xprint-types")

  /**
   * Display a prompt after each error (debugging option).
   */
  override def Xprompt = List("-Xprompt")

  /**
   * Compiler stays resident: read source filenames from standard input.
   */
  override def Xresident = List("-Xresident")

  /**
   * <class>           Show internal representation of class.
   */
  def XshowClass = List("-Xshow-class")

  /**
   * <object>         Show internal representation of object.
   */
  def XshowObject = List("-Xshow-object")

  /**
   * Print a synopsis of compiler phases.
   */
  def XshowPhases = List("-Xshow-phases")

  /**
   * Treat compiler input as Scala source for the specified version, see SI-8126.
   */
  override def Xsource(version: String) = List("-Xsource:" + version)

  /**
   * <classname>    Specify a custom method for reading source files.
   */
  override def XsourceReader = List("-Xsource-reader")

  /**
   * Don't infer known-unsound types
   */
  def XstrictInference = List("-Xstrict-inference")

  /**
   * Verify generic signatures in generated bytecode (asm backend only.)
   */
  override def Xverify = List("-Xverify")

  /**
   * Print a synopsis of private options.
   */
  override def Y = List("-Y")

  /**
   * Choice of bytecode emitter. (GenASM,GenBCode) default:GenASM
   */
  def Ybackend(`choice of bytecode emitter`: String) = List("-Ybackend:" + `choice of bytecode emitter`)

  /**
   * Attempt to break cycles encountered during typing
   */
  override def YbreakCycles = List("-Ybreak-cycles")

  /**
   * Browse the abstract syntax tree after <phases>
   */
  def Ybrowse(phases: String) = List("-Ybrowse:" + phases)

  /**
   * Perform closure elimination.
   */
  def YclosureElim = List("-Yclosure-elim")

  /**
   * Use compact tree printer when displaying trees.
   */
  override def YcompactTrees = List("-Ycompact-trees")

  /**
   * Perform optimization with constant values.
   */
  def YconstOpt = List("-Yconst-opt")

  /**
   * Perform dead code elimination.
   */
  def YdeadCode = List("-Ydead-code")

  /**
   * Increase the quantity of debugging output.
   */
  def Ydebug = List("-Ydebug")

  /**
   * Strategy used for translating lambdas into JVM code. (inline,method) default:inline
   */
  override def Ydelambdafy(strategy: String) = List("-Ydelambdafy:" + strategy)

  /**
   * Disable the prevention of unreachable blocks in code generation.
   */
  def YdisableUnreachablePrevention = List("-Ydisable-unreachable-prevention")

  /**
   * Trace all scaladoc activity.
   */
  def YdocDebug = List("-Ydoc-debug")

  /**
   * <dir>                    Dump the generated bytecode to .class files (useful for reflective compilation that utilizes in-memory classloaders).
   */
  override def YdumpClasses = List("-Ydump-classes")

  /**
   * Eta-expand varargs methods to T* rather than Seq[T].  This is a temporary option to ease transition. deprecated: This flag is scheduled for removal in 2.12. If you have a case where you need this flag then please report a bug.
   */
  @deprecated("See doc comment", "") def YetaExpandKeepsStar = List("-Yeta-expand-keeps-star")

  /**
   * <dir>                        Generate a parallel output directory of .asmp files (ie ASM Textifier output).
   */
  override def YgenAsmp = List("-Ygen-asmp")

  /**
   * <dir>                       Generate a parallel output directory of .javap files.
   */
  def YgenJavap = List("-Ygen-javap")

  /**
   * Generate, validate and output trees using the interactive compiler.
   */
  def YideDebug = List("-Yide-debug")

  def YinferArgumentTypes = List("-Yinfer-argument-types")

  /**
   * Allow inference of by-name types. This is a temporary option to ease transition. See SI-7899. deprecated: This flag is scheduled for removal in 2.12. If you have a case where you need this flag then please report a bug.
   */
  @deprecated("See doc comment", "") def YinferByName = List("-Yinfer-by-name")

  /**
   * Trace type inference and implicit search. deprecated: Use -Ytyper-debug
   */
  @deprecated("See doc comment", "") def YinferDebug = List("-Yinfer-debug")

  /**
   * Perform inlining when possible.
   */
  def Yinline = List("-Yinline")

  /**
   * Perform exception handler inlining when possible.
   */
  def YinlineHandlers = List("-Yinline-handlers")

  def YinlineWarnings = List("-Yinline-warnings")

  /**
   * Print stack traces when a context issues an error.
   */
  def YissueDebug = List("-Yissue-debug")

  /**
   * Linearizer to use (normal,dfs,rpo,dump) default:rpo
   */
  def Ylinearizer(which: String) = List("-Ylinearizer:" + which)

  /**
   * Log operations during <phases>
   */
  def Ylog(phases: String) = List("-Ylog:" + phases)

  /**
   * Output information about what classpath is being applied.
   */
  def YlogClasspath = List("-Ylog-classpath")

  /**
   * Trace essential macro-related activities.
   */
  def YmacroDebugLite = List("-Ymacro-debug-lite")

  /**
   * Trace all macro-related activities: compilation, generation of synthetics, classloading, expansion, exceptions.
   */
  def YmacroDebugVerbose = List("-Ymacro-debug-verbose")

  /**
   * Control expansion of macros, useful for scaladoc and presentation compiler (normal,none,discard) default:normal
   */
  override def YmacroExpand(policy: String) = List("-Ymacro-expand:" + policy)

  /**
   * Don't expand macros. Might be useful for scaladoc and presentation compiler, but will crash anything which uses macros and gets past typer. deprecated: Use -Ymacro-expand:none
   */
  @deprecated("See doc comment", "") def YmacroNoExpand = List("-Ymacro-no-expand")

  /**
   * Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
   */
  def YnoAdaptedArgs = List("-Yno-adapted-args")

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
   * Do not load $class.class files.
   */
  def YnoLoadImplClass = List("-Yno-load-impl-class")

  /**
   * Clears all the flags set by -optimise. Useful for testing optimizations in isolation.
   */
  def Ynooptimise = List("-Ynooptimise")

  /**
   * Compile without importing Predef.
   */
  override def YnoPredef = List("-Yno-predef")

  /**
   * Allow member objects to be overridden.
   */
  def YoverrideObjects = List("-Yoverride-objects")

  /**
   * Allow vars to be overridden.
   */
  def YoverrideVars = List("-Yoverride-vars")

  /**
   * Trace pattern matching translation.
   */
  def YpatmatDebug = List("-Ypatmat-debug")

  /**
   * Trace position validation.
   */
  def YposDebug = List("-Ypos-debug")

  /**
   * Enable debugging output for the presentation compiler.
   */
  override def YpresentationDebug = List("-Ypresentation-debug")

  /**
   * <n>                Wait number of ms after typing before starting typechecking
   */
  override def YpresentationDelay = List("-Ypresentation-delay")

  /**
   * <file>               Log presentation compiler events into file
   */
  override def YpresentationLog = List("-Ypresentation-log")

  /**
   * <file>            Replay presentation compiler events from file
   */
  override def YpresentationReplay = List("-Ypresentation-replay")

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
  def YquasiquoteDebug = List("-Yquasiquote-debug")

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
  def YreifyDebug = List("-Yreify-debug")

  /**
   * Use classes to wrap REPL snippets instead of objects
   */
  override def YreplClassBased = List("-Yrepl-class-based")

  /**
   * <path>                    Write repl-generated classfiles to given output directory (use "" to generate a temporary dir)
   */
  override def YreplOutdir = List("-Yrepl-outdir")

  /**
   * Do not use asynchronous code for repl startup
   */
  def YreplSync = List("-Yrepl-sync")

  /**
   * Resolve term conflicts (package,object,error) default:error
   */
  override def YresolveTermConflict(strategy: String) = List("-Yresolve-term-conflict:" + strategy)

  /**
   * (Requires -Xshow-class or -Xshow-object) Show after <phases>
   */
  def Yshow(phases: String) = List("-Yshow:" + phases)

  /**
   * <output style>        Show start and end positions of members
   */
  def YshowMemberPos = List("-Yshow-member-pos")

  /**
   * Print abbreviated symbol kinds next to symbol names.
   */
  def YshowSymkinds = List("-Yshow-symkinds")

  /**
   * Print owner identifiers next to symbol names.
   */
  def YshowSymowners = List("-Yshow-symowners")

  /**
   * Print the AST symbol hierarchy after each phase.
   */
  def YshowSyms = List("-Yshow-syms")

  /**
   * (Requires -Xprint:) Print detailed ASTs in formatted form.
   */
  def YshowTrees = List("-Yshow-trees")

  /**
   * (Requires -Xprint:) Print detailed ASTs in compact form.
   */
  def YshowTreesCompact = List("-Yshow-trees-compact")

  /**
   * (Requires -Xprint:) Print stringifications along with detailed ASTs.
   */
  def YshowTreesStringified = List("-Yshow-trees-stringified")

  /**
   * Trace all type assignments.
   */
  def YtyperDebug = List("-Ytyper-debug")

  /**
   * Warn if an argument list is modified to match the receiver.
   */
  def YwarnAdaptedArgs = List("-Ywarn-adapted-args")

  /**
   * Warn when dead code is identified.
   */
  def YwarnDeadCode = List("-Ywarn-dead-code")

  /**
   * Warn about inaccessible types in method signatures.
   */
  def YwarnInaccessible = List("-Ywarn-inaccessible")

  /**
   * Warn when a type argument is inferred to be `Any`.
   */
  def YwarnInferAny = List("-Ywarn-infer-any")

  def YwarnNullaryOverride = List("-Ywarn-nullary-override")

  /**
   * Warn when nullary methods return Unit.
   */
  def YwarnNullaryUnit = List("-Ywarn-nullary-unit")

  /**
   * Warn when numerics are widened.
   */
  def YwarnNumericWiden = List("-Ywarn-numeric-widen")

  def YwarnUnused = List("-Ywarn-unused")

  def YwarnUnusedImport = List("-Ywarn-unused-import")

  /**
   * Warn when non-Unit expression results are unused.
   */
  def YwarnValueDiscard = List("-Ywarn-value-discard")
}
