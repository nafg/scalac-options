// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_3_+ extends V2_13_2_+ {
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
   * Print program with Scala-specific features removed. [false]
   */
  override def print = List("-print")

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
   * Print a synopsis of verbose options. [false]
   */
  override def V = List("-V")

  /**
   * Output information about what classpath is being applied. [false]
   */
  override def Vclasspath = List("-Vclasspath")

  /**
   * Increase the quantity of debugging output. [false]
   */
  override def Vdebug = List("-Vdebug")

  /**
   * Trace scaladoc activity. [false]
   */
  override def Vdoc = List("-Vdoc")

  /**
   * Output messages about what the compiler is doing. [false]
   */
  override def verbose = List("-verbose")

  /**
   * Print product version and exit. [false]
   */
  override def version = List("-version")

  /**
   * Print a message when reification creates a free term. [false]
   */
  override def VfreeTerms = List("-Vfree-terms")

  /**
   * Print a message when reification resorts to generating a free type. [false]
   */
  override def VfreeTypes = List("-Vfree-types")

  /**
   * Enable `-Vstatistics` to also print hot statistics. [false]
   */
  override def VhotStatistics = List("-Vhot-statistics")

  /**
   * Generate, validate and output trees using the interactive compiler. [false]
   */
  override def Vide = List("-Vide")

  /**
   * Print a message whenever an implicit conversion is inserted. [false]
   */
  override def VimplicitConversions = List("-Vimplicit-conversions")

  /**
   * Trace macro activities: compilation, generation of synthetics, classloading, expansion, exceptions. [false]
   */
  override def Vmacro = List("-Vmacro")

  /**
   * Trace macro activities with less output. [false]
   */
  override def VmacroLite = List("-Vmacro-lite")

  /**
   * Trace pattern matching translation. [false]
   */
  override def Vpatmat = List("-Vpatmat")

  /**
   * Print a synopsis of compiler phases. [false]
   */
  override def Vphases = List("-Vphases")

  /**
   * Trace position validation. [false]
   */
  override def Vpos = List("-Vpos")

  /**
   * Print tree positions, as offsets. [false]
   */
  override def VprintPos = List("-Vprint-pos")

  /**
   * Print tree types (debugging option). [false]
   */
  override def VprintTypes = List("-Vprint-types")

  /**
   * Trace quasiquotations. [false]
   */
  override def Vquasiquote = List("-Vquasiquote")

  /**
   * Print a message when a reflective method call is generated [false]
   */
  override def VreflectiveCalls = List("-Vreflective-calls")

  /**
   * Trace reification. [false]
   */
  override def Vreify = List("-Vreify")

  /**
   * Print abbreviated symbol kinds next to symbol names. [false]
   */
  override def VshowSymkinds = List("-Vshow-symkinds")

  /**
   * Print owner identifiers next to symbol names. [false]
   */
  override def VshowSymowners = List("-Vshow-symowners")

  /**
   * Print the AST symbol hierarchy after each phase. [false]
   */
  override def Vsymbols = List("-Vsymbols")

  /**
   * Trace type assignments. [false]
   */
  override def Vtyper = List("-Vtyper")

  /**
   * Print a synopsis of warning options. [false]
   */
  override def W = List("-W")

  /**
   * Warn when dead code is identified. [false]
   */
  override def WdeadCode = List("-Wdead-code")

  /**
   * Fail the compilation if there are any warnings. [false]
   */
  override def Werror = List("-Werror")

  /**
   * Warn when more than one implicit parameter section is defined. [false]
   */
  override def WextraImplicit = List("-Wextra-implicit")

  /**
   * Warn when numerics are widened. [false]
   */
  override def WnumericWiden = List("-Wnumeric-widen")

  /**
   * Warn on obsolete octal syntax. [false]
   */
  override def WoctalLiteral = List("-Woctal-literal")

  /**
   * Warn when non-Unit expression results are unused. [false]
   */
  override def WvalueDiscard = List("-Wvalue-discard")

  /**
   * Print a synopsis of advanced options. [false]
   */
  override def X = List("-X")

  /**
   * Enable the async phase for scala.async.Async.{async,await}. [false]
   */
  def Xasync = List("-Xasync")

  /**
   * Wrap field accessors to throw an exception on uninitialized access. [false]
   */
  override def Xcheckinit = List("-Xcheckinit")

  /**
   * Issue warnings about anything which seems amiss in compiler internals. Intended for compiler developers [false]
   */
  override def Xdev = List("-Xdev")

  /**
   * Generate no assertions or assumptions. [false]
   */
  override def XdisableAssertions = List("-Xdisable-assertions")

  /**
   * Implicit resolves to an enclosing definition.
   */
  def XlintImplicitRecursion = List("-Xlint:implicit-recursion")

  /**
   * Do not generate static forwarders in mirror classes. [false]
   */
  override def XnoForwarders = List("-Xno-forwarders")

  /**
   * Don't perform exhaustivity/unreachability analysis. Also, ignore @switch annotation. [false]
   */
  override def XnoPatmatAnalysis = List("-Xno-patmat-analysis")

  /**
   * Print a synopsis of loaded plugins. [false]
   */
  override def XpluginList = List("-Xplugin-list")

  /**
   * Display a prompt after each error (debugging option). [false]
   */
  override def Xprompt = List("-Xprompt")

  /**
   * Compiler stays resident: read source filenames from standard input. [false]
   */
  override def Xresident = List("-Xresident")

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
   * Use compact tree printer when displaying trees. [false]
   */
  override def YcompactTrees = List("-Ycompact-trees")

  /**
   * Enable support for macro annotations, formerly in macro paradise. [false]
   */
  override def YmacroAnnotations = List("-Ymacro-annotations")

  /**
   * Should fresh names in macros be unique across all compilation units [false]
   */
  override def YmacroGlobalFreshNames = List("-Ymacro-global-fresh-names")

  /**
   * Disable tab-completion in the REPL. [false]
   */
  override def YnoCompletion = List("-Yno-completion")

  /**
   * Do not cache flat classpath representation of classpath elements from jars across compiler instances. [false]
   */
  override def YnoFlatClasspathCache = List("-Yno-flat-classpath-cache")

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
   * Dump the reified trees in copypasteable representation. [false]
   */
  override def YreifyCopypaste = List("-Yreify-copypaste")

  /**
   * Use classes to wrap REPL snippets instead of objects [true]
   */
  override def YreplClassBased = List("-Yrepl-class-based")

  /**
   * In the code that wraps REPL snippets, use magic imports rather than nesting wrapper object/classes [true]
   */
  override def YreplUseMagicImports = List("-Yrepl-use-magic-imports")

  /**
   * Record references to in unit.depends. Deprecated feature that supports SBT 0.13 with incOptions.withNameHashing(false) only. [true]
   */
  def YtrackDependencies = List("-Ytrack-dependencies")
}
