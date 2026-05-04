// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2 extends Common {
  /**
   * Override location of bootstrap class files.
   */
  def bootclasspath(path: String) = List("-bootclasspath", path)

  /**
   * Specify where to find user class files.
   */
  def classpath(path: String) = List("-classpath", path)

  /**
   * Pass -Dproperty=value directly to the runtime system.
   */
  def D(property: String, value: String) = List("-D" + property + "=" + value)

  def d(`directory|jar`: String) = List("-d", `directory|jar`)

  /**
   * Set dependency tracking file.
   */
  def dependencyfile(file: String) = List("-dependencyfile", file)

  /**
   * Specify character encoding used by source files.
   */
  override def encoding(encoding: String) = List("-encoding", encoding)

  def explaintypes = List("-explaintypes")

  /**
   * Override location of installed extensions.
   */
  def extdirs(path: String) = List("-extdirs", path)

  def g(level: String) = List("-g:" + level)

  /**
   * Pass <flag> directly to the runtime system.
   */
  def J(flag: String) = List("-J" + flag)

  /**
   * Override java boot classpath.
   */
  def javabootclasspath(path: String) = List("-javabootclasspath", path)

  /**
   * Override java extdirs classpath.
   */
  def javaextdirs(path: String) = List("-javaextdirs", path)

  def nobootcp = List("-nobootcp")

  def noSpecialization = List("-no-specialization")

  /**
   * Pass an option to a plugin
   */
  def P(plugin: String, opt: String) = List("-P:" + plugin + ":" + opt)

  def print = List("-print")

  /**
   * Specify location(s) of source files.
   */
  def sourcepath(path: String) = List("-sourcepath", path)

  /**
   * Add to the runner classpath.
   */
  def toolcp(path: String) = List("-toolcp", path)

  def usejavacp = List("-usejavacp")

  def usemanifestcp = List("-usemanifestcp")

  def Xcheckinit = List("-Xcheckinit")

  def Xdev = List("-Xdev")

  def XdisableAssertions = List("-Xdisable-assertions")

  def XelideBelow = List("-Xelide-below")

  /**
   * Custom settings for macros.
   */
  def XmacroSettings(option: String) = List("-Xmacro-settings:" + option)

  /**
   * Warn about constructs whose behavior may have changed since version.
   */
  def Xmigration(version: String) = List("-Xmigration:" + version)

  def Xnojline = List("-Xnojline")

  def XnoPatmatAnalysis = List("-Xno-patmat-analysis")

  /**
   * Load a plugin from each classpath.
   */
  def Xplugin(paths: String) = List("-Xplugin:" + paths)

  /**
   * Disable plugins by name.
   */
  def XpluginDisable(plugin: String) = List("-Xplugin-disable:" + plugin)

  /**
   * Abort if a named plugin is not loaded.
   */
  def XpluginRequire(plugin: String) = List("-Xplugin-require:" + plugin)

  /**
   * Path to search for plugin archives.
   */
  def Xpluginsdir(path: String) = List("-Xpluginsdir", path)

  def Xresident = List("-Xresident")

  /**
   * Treat the source file as a script and wrap it in a main method.
   */
  def Xscript(`object`: String) = List("-Xscript", `object`)

  def Xsource(version: String) = List("-Xsource:" + version)

  def XsourceReader = List("-Xsource-reader")

  def Xverify = List("-Xverify")

  def YbreakCycles = List("-Ybreak-cycles")

  /**
   * Check the tree at the end of <phases>
   */
  def Ycheck(phases: String) = List("-Ycheck:" + phases)

  def YcompactTrees = List("-Ycompact-trees")

  def Ydelambdafy(strategy: String) = List("-Ydelambdafy:" + strategy)

  def YgenAsmp = List("-Ygen-asmp")

  def YmacroExpand(policy: String) = List("-Ymacro-expand:" + policy)

  def YnoCompletion = List("-Yno-completion")

  def YpresentationDebug = List("-Ypresentation-debug")

  def YpresentationDelay = List("-Ypresentation-delay")

  def YpresentationLog = List("-Ypresentation-log")

  def YpresentationReplay = List("-Ypresentation-replay")

  def YpresentationStrict = List("-Ypresentation-strict")

  def YpresentationVerbose = List("-Ypresentation-verbose")

  def Yrangepos = List("-Yrangepos")

  /**
   * Set recursion depth used when locking symbols.
   */
  def Yrecursion(n: String) = List("-Yrecursion", n)

  def YreifyCopypaste = List("-Yreify-copypaste")

  def YreplClassBased = List("-Yrepl-class-based")

  def YreplOutdir = List("-Yrepl-outdir")

  def YresolveTermConflict(strategy: String) = List("-Yresolve-term-conflict:" + strategy)

  /**
   * Skip <phases>
   */
  def Yskip(phases: String) = List("-Yskip:" + phases)

  /**
   * Stop after <phases>
   */
  def YstopAfter(phases: String) = List("-Ystop-after:" + phases)

  /**
   * Stop before <phases>
   */
  def YstopBefore(phases: String) = List("-Ystop-before:" + phases)
}
