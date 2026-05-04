// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13 extends V2 {
  /**
   * Set level of generated debugging info. (none,source,line,[vars],notailcalls)
   */
  override def g(level: String) = List("-g:" + level)

  /**
   * Enable or disable language features
   */
  override def language(features: String) = List("-language:" + features)

  def opt(optimizations: String) = List("-opt:" + optimizations)

  /**
   * Patterns for classfile names from which to allow inlining, `help` for details.
   */
  def optInlineFrom(patterns: String) = List("-opt-inline-from:" + patterns)

  def optWarnings(warnings: String) = List("-opt-warnings:" + warnings)

  def V = List("-V")

  /**
   * Browse the abstract syntax tree after <phases>
   */
  def Vbrowse(phases: String) = List("-Vbrowse:" + phases)

  def Vclasspath = List("-Vclasspath")

  def Vdebug = List("-Vdebug")

  def Vdoc = List("-Vdoc")

  def VfreeTerms = List("-Vfree-terms")

  def VfreeTypes = List("-Vfree-types")

  def VhotStatistics = List("-Vhot-statistics")

  def Vide = List("-Vide")

  def VimplicitConversions = List("-Vimplicit-conversions")

  def Vimplicits = List("-Vimplicits")

  /**
   * Print a summary of inliner activity; `_` to print all, prefix match to select.
   */
  def Vinline(`package/Class.method`: String) = List("-Vinline", `package/Class.method`)

  /**
   * Log operations during <phases>
   */
  def Vlog(phases: String) = List("-Vlog:" + phases)

  def Vmacro = List("-Vmacro")

  def VmacroLite = List("-Vmacro-lite")

  /**
   * Trace the optimizer progress for methods; `_` to print all, prefix match to select.
   */
  def Vopt(`package/Class.method`: String) = List("-Vopt", `package/Class.method`)

  def Vpatmat = List("-Vpatmat")

  def Vphases = List("-Vphases")

  def Vpos = List("-Vpos")

  /**
   * <file>               Print all compiler arguments to the specified location. Use - to echo to the reporter.
   */
  def VprintArgs = List("-Vprint-args")

  def VprintPos = List("-Vprint-pos")

  def VprintTypes = List("-Vprint-types")

  def Vquasiquote = List("-Vquasiquote")

  def VreflectiveCalls = List("-Vreflective-calls")

  def Vreify = List("-Vreify")

  /**
   * (Requires -Xshow-class or -Xshow-object) Show after <phases>
   */
  def Vshow(phases: String) = List("-Vshow:" + phases)

  /**
   * <class>              Show internal representation of class.
   */
  def VshowClass = List("-Vshow-class")

  /**
   * <output style>  Show start and end positions of members (implies -Yrangepos)
   */
  def VshowMemberPos = List("-Vshow-member-pos")

  /**
   * <object>            Show internal representation of object.
   */
  def VshowObject = List("-Vshow-object")

  def VshowSymkinds = List("-Vshow-symkinds")

  def VshowSymowners = List("-Vshow-symowners")

  def Vstatistics = List("-Vstatistics")

  def Vsymbols = List("-Vsymbols")

  def Vtyper = List("-Vtyper")

  def W = List("-W")

  def WdeadCode = List("-Wdead-code")

  def Werror = List("-Werror")

  def WextraImplicit = List("-Wextra-implicit")

  def Wmacros(mode: String) = List("-Wmacros:" + mode)

  def WnumericWiden = List("-Wnumeric-widen")

  def WoctalLiteral = List("-Woctal-literal")

  /**
   * Warn if an explicit parameter is unused.
   */
  def WunusedExplicits = List("-Wunused:explicits")

  /**
   * Warn if an implicit parameter is unused.
   */
  def WunusedImplicits = List("-Wunused:implicits")

  /**
   * Warn if an import selector is not referenced.
   */
  def WunusedImports = List("-Wunused:imports")

  /**
   * -Xlint:unused.
   */
  def WunusedLinted = List("-Wunused:linted")

  /**
   * Warn if a local definition is unused.
   */
  def WunusedLocals = List("-Wunused:locals")

  def WunusedParams = List("-Wunused:params")

  /**
   * Warn if a variable bound in a pattern is unused.
   */
  def WunusedPatvars = List("-Wunused:patvars")

  /**
   * Warn if a private member is unused.
   */
  def WunusedPrivates = List("-Wunused:privates")

  def WvalueDiscard = List("-Wvalue-discard")

  /**
   * <n>                Calls to @elidable methods are omitted if method priority is lower than argument
   */
  override def XelideBelow = List("-Xelide-below")

  /**
   * Enable recommended warnings
   */
  def Xlint(warnings: String) = List("-Xlint:" + warnings)

  def XlintAdaptedArgs = List("-Xlint:adapted-args")

  def XlintConstant = List("-Xlint:constant")

  /**
   * Selecting member of DelayedInit.
   */
  def XlintDelayedinitSelect = List("-Xlint:delayedinit-select")

  def XlintDeprecation = List("-Xlint:deprecation")

  def XlintDocDetached = List("-Xlint:doc-detached")

  def XlintEtaSam = List("-Xlint:eta-sam")

  def XlintEtaZero = List("-Xlint:eta-zero")

  /**
   * Check @implicitNotFound and @implicitAmbiguous messages.
   */
  def XlintImplicitNotFound = List("-Xlint:implicit-not-found")

  /**
   * Warn about inaccessible types in method signatures.
   */
  def XlintInaccessible = List("-Xlint:inaccessible")

  def XlintInferAny = List("-Xlint:infer-any")

  /**
   * A string literal appears to be missing an interpolator id.
   */
  def XlintMissingInterpolator = List("-Xlint:missing-interpolator")

  /**
   * A return statement used an exception for flow control.
   */
  def XlintNonlocalReturn = List("-Xlint:nonlocal-return")

  def XlintNullaryUnit = List("-Xlint:nullary-unit")

  def XlintOptionImplicit = List("-Xlint:option-implicit")

  /**
   * Class or object defined in package object.
   */
  def XlintPackageObjectClasses = List("-Xlint:package-object-classes")

  /**
   * Parameterized overloaded implicit methods are not visible as view bounds.
   */
  def XlintPolyImplicitOverload = List("-Xlint:poly-implicit-overload")

  /**
   * A private field (or class parameter) shadows a superclass field.
   */
  def XlintPrivateShadow = List("-Xlint:private-shadow")

  /**
   * @SerialVersionUID on traits and non-serializable classes.
   */
  def XlintSerial = List("-Xlint:serial")

  def XlintStarsAlign = List("-Xlint:stars-align")

  /**
   * A local type parameter shadows a type already in scope.
   */
  def XlintTypeParameterShadow = List("-Xlint:type-parameter-shadow")

  def XlintUnused = List("-Xlint:unused")

  /**
   * Enable pattern checks in val definitions.
   */
  def XlintValpattern = List("-Xlint:valpattern")

  /**
   * Maximum errors to print
   */
  def Xmaxerrs(n: String) = List("-Xmaxerrs", n)

  /**
   * Maximum warnings to print
   */
  def Xmaxwarns(n: String) = List("-Xmaxwarns", n)

  def XmixinForceForwarders(mode: String) = List("-Xmixin-force-forwarders:" + mode)

  def Xreporter(classname: String) = List("-Xreporter", classname)

  /**
   * <classname>      Specify a custom method for reading source files.
   */
  override def XsourceReader = List("-Xsource-reader")

  /**
   * Configure XML parsing.
   */
  def Xxml(propertys: String) = List("-Xxml:" + propertys)

  /**
   * <n>             maximum worker threads for backend
   */
  def YbackendParallelism = List("-Ybackend-parallelism")

  /**
   * <n>            backend threads worker queue size
   */
  def YbackendWorkerQueue = List("-Ybackend-worker-queue")

  /**
   * Policy for caching class loaders for macros that are dynamically loaded. Default: `none`, `help` to list choices.
   */
  def YcacheMacroClassLoader(policy: String) = List("-Ycache-macro-class-loader:" + policy)

  /**
   * Policy for caching class loaders for compiler plugins that are dynamically loaded. Default: `none`, `help` to list choices.
   */
  def YcachePluginClassLoader(policy: String) = List("-Ycache-plugin-class-loader:" + policy)

  /**
   * Strategy used for translating lambdas into JVM code. (inline,[method])
   */
  override def Ydelambdafy(strategy: String) = List("-Ydelambdafy:" + strategy)

  /**
   * <dir>                  Dump the generated bytecode to .class files (useful for reflective compilation that utilizes in-memory classloaders).
   */
  override def YdumpClasses = List("-Ydump-classes")

  /**
   * <dir>                      Generate a parallel output directory of .asmp files (ie ASM Textifier output).
   */
  override def YgenAsmp = List("-Ygen-asmp")

  /**
   * Custom root imports, default is `java.lang,scala,scala.Predef`.
   */
  def Yimports(`import`: String) = List("-Yimports:" + `import`)

  /**
   * <n>           compression level to use when writing jar files
   */
  def YjarCompressionLevel = List("-Yjar-compression-level")

  def YmacroAnnotations = List("-Ymacro-annotations")

  /**
   * <path>              The classpath used to reflectively load macro implementations, default is the compilation classpath.
   */
  def YmacroClasspath = List("-Ymacro-classpath")

  /**
   * Control expansion of macros, useful for scaladoc and presentation compiler. ([normal],none,discard)
   */
  override def YmacroExpand(policy: String) = List("-Ymacro-expand:" + policy)

  def YmacroGlobalFreshNames = List("-Ymacro-global-fresh-names")

  def YnoFlatClasspathCache = List("-Yno-flat-classpath-cache")

  /**
   * Set the heuristics for inlining decisions. (at-inline-annotated,everything,[default])
   */
  def YoptInlineHeuristics(strategy: String) = List("-Yopt-inline-heuristics:" + strategy)

  /**
   * <n>            off
   */
  def YpatmatExhaustDepth = List("-Ypatmat-exhaust-depth")

  def YpresentationAnyThread = List("-Ypresentation-any-thread")

  /**
   * <n>              Wait number of ms after typing before starting typechecking
   */
  override def YpresentationDelay = List("-Ypresentation-delay")

  /**
   * <file>             Log presentation compiler events into file
   */
  override def YpresentationLog = List("-Ypresentation-log")

  /**
   * <file>          Replay presentation compiler events from file
   */
  override def YpresentationReplay = List("-Ypresentation-replay")

  def YprintTrees(style: String) = List("-Yprint-trees:" + style)

  /**
   * <file>          Profiling output - specify a file or `-` for console.
   */
  def YprofileDestination = List("-Yprofile-destination")

  def YprofileEnabled = List("-Yprofile-enabled")

  /**
   * [:phases]      Enable profiling for a phase using an external tool hook. Generally only useful for a single phase <phases> (default: typer)
   */
  def YprofileExternalTool = List("-Yprofile-external-tool")

  /**
   * [:phases]             Run a GC between phases - this allows heap size to be accurate at the expense of more time. Specify a list of phases, or all <phases> (default: _)
   */
  def YprofileRunGc = List("-Yprofile-run-gc")

  /**
   * <file>                Capture trace of compilation in Chrome Trace format
   */
  def YprofileTrace = List("-Yprofile-trace")

  /**
   * <path>                  Write repl-generated classfiles to given output directory (use "" to generate a temporary dir)
   */
  override def YreplOutdir = List("-Yrepl-outdir")

  /**
   * Resolve term conflicts. (package,object,[error])
   */
  override def YresolveTermConflict(strategy: String) = List("-Yresolve-term-conflict:" + strategy)

  /**
   * Specify a scala.tools.nsc.ScriptRunner (default, resident, shutdown, or a class name).
   */
  def Yscriptrunner(classname: String) = List("-Yscriptrunner", classname)

  /**
   * Validate positions after the given phases (implies -Yrangepos) <phases>
   */
  def YvalidatePos(phases: String) = List("-Yvalidate-pos:" + phases)
}
