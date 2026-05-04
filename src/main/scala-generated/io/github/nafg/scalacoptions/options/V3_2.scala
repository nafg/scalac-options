// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_2 extends V3 {
  /**
   * Specify where to find user class files. Default .
   */
  override def classpath = List("-classpath")

  /**
   * Colored output Default always Choices always, never
   */
  override def color = List("-color")

  /**
   * Destination for coverage classfiles and instrumentation data.
   */
  def coverageOut = List("-coverage-out")

  /**
   * Pass -Dproperty=value directly to the runtime system.
   */
  def D(property: String, value: String) = List("-D" + property + "=" + value)

  /**
   * Specify character encoding used by source files. Default UTF-8
   */
  override def encoding(encoding: String) = List("-encoding", encoding)

  /**
   * Pass <flag> directly to the runtime system.
   */
  def J(flag: String) = List("-J" + flag)

  /**
   * Compile code with classes specific to the given version of the Java platform available on the classpath and emit bytecode for this version. Corresponds to -release flag in javac. Choices 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19
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
   *  Generate static forwarders even for non-top-level objects (Scala.js only)
   */
  override def scalajsGenStaticForwardersForNonTopLevelObjects = List("-scalajs-genStaticForwardersForNonTopLevelObjects")

  /**
   * rebases source URIs from uri1 to uri2 (or to a relative URI) for source maps (Scala.js only)
   */
  override def scalajsMapSourceURI = List("-scalajs-mapSourceURI")

  /**
   * source version Default 3.2 Choices 3.0-migration, 3.0, 3.1, 3.2-migration, 3.2, future-migration, future
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
   * Enable or disable specific `unused` warnings Choices nowarn, all
   */
  def Wunused = List("-Wunused")

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
   * Generate forwarder methods in classes inhering concrete methods from traits. Default true Choices true, junit, false
   */
  override def XmixinForceForwarders = List("-Xmixin-force-forwarders")

  /**
   * Path to search for plugin archives. Default misc/scala-devel/plugins
   */
  override def Xpluginsdir = List("-Xpluginsdir")

  /**
   *  Emit bytecode for the specified version of the Java platform. This might produce bytecode that will break at runtime. Corresponds to -target flag in javac. When on JDK 9+, consider -java-output-version as a safer alternative. Choices 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19
   */
  def XuncheckedJavaOutputVersion = List("-Xunchecked-java-output-version")

  /**
   * Cook the documentation (type check `@usecase`, etc.)
   */
  def YcookDocs = List("-Ycook-docs")

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
   * Make non-transparent inline methods inline when typing. Emulates the old inlining behavior of 3.0.0-M3.
   */
  def YforceInlineWhileTyping = List("-Yforce-inline-while-typing")

  /**
   * List of `tasty` files in jar files that will not be loaded when using -from-tasty
   */
  override def YfromTastyIgnoreList = List("-Yfrom-tasty-ignore-list")

  /**
   * (disabled: use -language:experimental.fewerBraces instead)
   */
  def YindentColons = List("-Yindent-colons")

  /**
   * Allow `*` as type lambda placeholder to be compatible with kind projector. When invoked as -Ykind-projector:underscores will repurpose `_` to be a type parameter placeholder, this will disable usage of underscore as a wildcard. Default disable Choices disable, , underscores
   */
  override def YkindProjector = List("-Ykind-projector")

  /**
   * Show raw StackOverflow stacktraces, instead of decoding them into triggering operations.
   */
  override def YnoDecodeStacktraces = List("-Yno-decode-stacktraces")

  /**
   * Disable experimental language features
   */
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
   * print nesting levels of symbols and type variables.
   */
  def YprintLevel = List("-Yprint-level")

  /**
   * Read documentation from tasty.
   */
  def YreadDocs = List("-Yread-docs")

  /**
   * Warn if an operator is defined without a @targetName annotation
   */
  override def YrequireTargetName = List("-Yrequire-targetName")

  /**
   * Resolve term conflicts Default error Choices package, object, error
   */
  override def YresolveTermConflict = List("-Yresolve-term-conflict")

  /**
   * Ensure safe initialization of objects
   */
  override def YsafeInit = List("-Ysafe-init")

  /**
   * 2-unpickler  Control where we may get Scala 2 symbols from. This is either "always", "never", or a classpath. Default always
   */
  override def Yscala = List("-Yscala")
}
