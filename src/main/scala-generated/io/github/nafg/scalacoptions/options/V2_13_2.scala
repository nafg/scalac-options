// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_2 extends V2_13_2_+ {
  /**
   * A text file containing compiler arguments (options and source files)
   */
  override def `@`(file: String) = List("@" + file)

  /**
   * destination for generated classfiles.
   */
  override def d(`directory|jar`: String) = List("-d", `directory|jar`)

  /**
   * Emit warning and location for usages of deprecated APIs. See also -Wconf.
   */
  override def deprecation = List("-deprecation")

  /**
   * Explain type errors in more detail.
   */
  override def explaintypes = List("-explaintypes")

  /**
   * Emit warning and location for usages of features that should be imported explicitly. See also -Wconf.
   */
  override def feature = List("-feature")

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
   * Enable optimizations, `help` for details.
   */
  override def opt(optimizations: String) = List("-opt:" + optimizations)

  /**
   * Print program with Scala-specific features removed.
   */
  override def print = List("-print")

  /**
   * Compile for a specific version of the Java platform. Supported targets: 6, 7, 8, 9
   */
  def release(release: String) = List("-release", release)

  /**
   * Target platform for object files. ([8],9,10,11,12)
   */
  def target(target: String) = List("-target:" + target)

  /**
   * Enable additional warnings where generated code depends on assumptions. See also -Wconf.
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
   * Print a synopsis of verbose options.
   */
  override def V = List("-V")

  /**
   * Output information about what classpath is being applied.
   */
  override def Vclasspath = List("-Vclasspath")

  /**
   * Increase the quantity of debugging output.
   */
  override def Vdebug = List("-Vdebug")

  /**
   * Trace scaladoc activity.
   */
  override def Vdoc = List("-Vdoc")

  /**
   * Output messages about what the compiler is doing.
   */
  override def verbose = List("-verbose")

  /**
   * Print product version and exit.
   */
  override def version = List("-version")

  /**
   * Print a message when reification creates a free term.
   */
  override def VfreeTerms = List("-Vfree-terms")

  /**
   * Print a message when reification resorts to generating a free type.
   */
  override def VfreeTypes = List("-Vfree-types")

  /**
   * Enable `-Vstatistics` to also print hot statistics.
   */
  override def VhotStatistics = List("-Vhot-statistics")

  /**
   * Generate, validate and output trees using the interactive compiler.
   */
  override def Vide = List("-Vide")

  /**
   * Print a message whenever an implicit conversion is inserted.
   */
  override def VimplicitConversions = List("-Vimplicit-conversions")

  /**
   * Show more detail on why some implicits are not applicable.
   */
  override def Vimplicits = List("-Vimplicits")

  /**
   * Print stack traces when a context issues an error.
   */
  def Vissue = List("-Vissue")

  /**
   * Trace macro activities: compilation, generation of synthetics, classloading, expansion, exceptions.
   */
  override def Vmacro = List("-Vmacro")

  /**
   * Trace macro activities with less output.
   */
  override def VmacroLite = List("-Vmacro-lite")

  /**
   * Trace pattern matching translation.
   */
  override def Vpatmat = List("-Vpatmat")

  /**
   * Print a synopsis of compiler phases.
   */
  override def Vphases = List("-Vphases")

  /**
   * Trace position validation.
   */
  override def Vpos = List("-Vpos")

  /**
   * Print out program after <phases>
   */
  def Vprint(phases: String) = List("-Vprint:" + phases)

  /**
   * Print tree positions, as offsets.
   */
  override def VprintPos = List("-Vprint-pos")

  /**
   * Print tree types (debugging option).
   */
  override def VprintTypes = List("-Vprint-types")

  /**
   * Trace quasiquotations.
   */
  override def Vquasiquote = List("-Vquasiquote")

  /**
   * Print a message when a reflective method call is generated
   */
  override def VreflectiveCalls = List("-Vreflective-calls")

  /**
   * Trace reification.
   */
  override def Vreify = List("-Vreify")

  /**
   * Print abbreviated symbol kinds next to symbol names.
   */
  override def VshowSymkinds = List("-Vshow-symkinds")

  /**
   * Print owner identifiers next to symbol names.
   */
  override def VshowSymowners = List("-Vshow-symowners")

  /**
   * [:phases]             Print compiler statistics for specific phases <phases> (default: parser,typer,patmat,erasure,cleanup,jvm)
   */
  override def Vstatistics = List("-Vstatistics")

  /**
   * Print the AST symbol hierarchy after each phase.
   */
  override def Vsymbols = List("-Vsymbols")

  /**
   * Trace type assignments.
   */
  override def Vtyper = List("-Vtyper")

  /**
   * Print a synopsis of warning options.
   */
  override def W = List("-W")

  /**
   * Warn when dead code is identified.
   */
  override def WdeadCode = List("-Wdead-code")

  /**
   * Fail the compilation if there are any warnings.
   */
  override def Werror = List("-Werror")

  /**
   * Warn when more than one implicit parameter section is defined.
   */
  override def WextraImplicit = List("-Wextra-implicit")

  /**
   * Enable lint warnings on macro expansions. Default: `before`, `help` to list choices.
   */
  override def Wmacros(mode: String) = List("-Wmacros:" + mode)

  /**
   * Warn when numerics are widened.
   */
  override def WnumericWiden = List("-Wnumeric-widen")

  /**
   * Warn on obsolete octal syntax.
   */
  override def WoctalLiteral = List("-Woctal-literal")

  /**
   * Warn when an implicit resolves to an enclosing self-definition.
   */
  def WselfImplicit = List("-Wself-implicit")

  /**
   * Enable -Wunused:explicits,implicits.
   */
  override def WunusedParams = List("-Wunused:params")

  /**
   * Warn when non-Unit expression results are unused.
   */
  override def WvalueDiscard = List("-Wvalue-discard")

  /**
   * Print a synopsis of advanced options.
   */
  override def X = List("-X")

  /**
   * Wrap field accessors to throw an exception on uninitialized access.
   */
  override def Xcheckinit = List("-Xcheckinit")

  /**
   * Issue warnings about anything which seems amiss in compiler internals. Intended for compiler developers
   */
  override def Xdev = List("-Xdev")

  /**
   * Generate no assertions or assumptions.
   */
  override def XdisableAssertions = List("-Xdisable-assertions")

  /**
   * Former graveyard for language-forking extensions. deprecated: Not used since 2.13.
   */
  @deprecated("See doc comment", "") def Xexperimental = List("-Xexperimental")

  /**
   * Replaced by -Xsource. deprecated: Not used since 2.13.
   */
  @deprecated("See doc comment", "") def Xfuture = List("-Xfuture")

  /**
   * <file>    Generate the phase graphs (outputs .dot files) to fileX.dot.
   */
  def XgeneratePhaseGraph = List("-Xgenerate-phase-graph")

  /**
   * Select JLine mode. Default: `emacs`, `help` to list choices.
   */
  def Xjline(mode: String) = List("-Xjline:" + mode)

  /**
   * The Java-defined target interface for eta-expansion was not annotated @FunctionalInterface.
   */
  override def XlintEtaSam = List("-Xlint:eta-sam")

  /**
   * Non-nullary `def f()` overrides nullary `def f`.
   */
  def XlintNullaryOverride = List("-Xlint:nullary-override")

  /**
   * <path>              Class for manifest's Main-Class entry (only useful with -d <jar>)
   */
  def XmainClass = List("-Xmain-class")

  /**
   * Generate forwarder methods in classes inhering concrete methods from traits. Default: `true`, `help` to list choices.
   */
  override def XmixinForceForwarders(mode: String) = List("-Xmixin-force-forwarders:" + mode)

  /**
   * Do not generate static forwarders in mirror classes.
   */
  override def XnoForwarders = List("-Xno-forwarders")

  /**
   * Do not use JLine for editing. deprecated: Replaced by -Xjline:off
   */
  @deprecated("See doc comment", "") override def Xnojline = List("-Xnojline")

  /**
   * Don't perform exhaustivity/unreachability analysis. Also, ignore @switch annotation.
   */
  override def XnoPatmatAnalysis = List("-Xno-patmat-analysis")

  /**
   * Print a synopsis of loaded plugins.
   */
  override def XpluginList = List("-Xplugin-list")

  /**
   * Display a prompt after each error (debugging option).
   */
  override def Xprompt = List("-Xprompt")

  /**
   * Compiler stays resident: read source filenames from standard input.
   */
  override def Xresident = List("-Xresident")

  /**
   * Enable features that will be available in a future version of Scala, for purposes of early migration and alpha testing.
   */
  override def Xsource(version: String) = List("-Xsource:" + version)

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
   * Enable support for macro annotations, formerly in macro paradise.
   */
  override def YmacroAnnotations = List("-Ymacro-annotations")

  /**
   * Should fresh names in macros be unique across all compilation units
   */
  override def YmacroGlobalFreshNames = List("-Ymacro-global-fresh-names")

  /**
   * Disable tab-completion in the REPL.
   */
  override def YnoCompletion = List("-Yno-completion")

  /**
   * Do not cache flat classpath representation of classpath elements from jars across compiler instances.
   */
  override def YnoFlatClasspathCache = List("-Yno-flat-classpath-cache")

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
   * Allow use of the presentation compiler from any thread
   */
  override def YpresentationAnyThread = List("-Ypresentation-any-thread")

  /**
   * Enable debugging output for the presentation compiler.
   */
  override def YpresentationDebug = List("-Ypresentation-debug")

  /**
   * Enables legacy code in the classfile parser to locate a .scala file in the output directories corresponding to the SourceFile attribute .class file.
   */
  override def YpresentationLocateSourceFile = List("-Ypresentation-locate-source-file")

  /**
   * Do not report type errors in sources with syntax errors.
   */
  override def YpresentationStrict = List("-Ypresentation-strict")

  /**
   * Print information about presentation compiler tasks.
   */
  override def YpresentationVerbose = List("-Ypresentation-verbose")

  /**
   * How to print trees when -Vprint is enabled. ([text],compact,format,text+format)
   */
  override def YprintTrees(style: String) = List("-Yprint-trees:" + style)

  /**
   * Enable profiling.
   */
  override def YprofileEnabled = List("-Yprofile-enabled")

  /**
   * Use range positions for syntax trees.
   */
  override def Yrangepos = List("-Yrangepos")

  /**
   * Dump the reified trees in copypasteable representation.
   */
  override def YreifyCopypaste = List("-Yreify-copypaste")

  /**
   * Use classes to wrap REPL snippets instead of objects
   */
  override def YreplClassBased = List("-Yrepl-class-based")

  /**
   * In the code that wraps REPL snippets, use magic imports rather than nesting wrapper object/classes
   */
  override def YreplUseMagicImports = List("-Yrepl-use-magic-imports")
}

object V2_13_2 extends V2_13_2
