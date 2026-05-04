// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_1_0 extends V3_1_0_+ {
  /**
   * Specify where to find user class files. Default: ..
   */
  override def classpath = List("-classpath")

  /**
   * Colored output Default: always. Choices: always, never.
   */
  override def color = List("-color")

  /**
   * Specify character encoding used by source files. Default: UTF-8.
   */
  override def encoding(encoding: String) = List("-encoding", encoding)

  /**
   * Set page width Default: 20000.
   */
  override def pagewidth = List("-pagewidth")

  /**
   * Compile code with classes specific to the given version of the Java platform available on the classpath and emit bytecode for this version. Choices: 8, 9, 10, 11, 12, 13, 14, 15, 16, 17.
   */
  def release = List("-release")

  /**
   * source version Default: 3.0. Choices: 3.0, 3.1, future, 3.0-migration, future-migration.
   */
  override def source = List("-source")

  /**
   * Specify workspace root directory. Default: ..
   */
  override def sourceroot = List("-sourceroot")

  /**
   * Enable or disable specific `unused` warnings
   */
  override def Wunused = List("-Wunused")

  /**
   * 2-macros      Ignore errors when compiling code that calls Scala2 macros, these will fail at runtime.
   */
  override def XignoreScala = List("-Xignore-scala")

  /**
   * Timeout (in ms) for searching for import suggestions when errors are reported. Default: 8000.
   */
  override def XimportSuggestionTimeout = List("-Ximport-suggestion-timeout")

  /**
   * Maximal number of inlined trees. Default: 2000000.
   */
  override def XmaxInlinedTrees = List("-Xmax-inlined-trees")

  /**
   * Maximal number of successive inlines. Default: 32.
   */
  override def XmaxInlines = List("-Xmax-inlines")

  /**
   * Generate forwarder methods in classes inhering concrete methods from traits. Default: true. Choices: true, junit, false.
   */
  override def XmixinForceForwarders = List("-Xmixin-force-forwarders")

  /**
   * Path to search for plugin archives. Default: misc/scala-devel/plugins.
   */
  override def Xpluginsdir = List("-Xpluginsdir")

  /**
   * Print all compiler phases.
   */
  def XshowPhases = List("-Xshow-phases")

  /**
   * Emit bytecode for the specified version of the Java platform. This might produce bytecode that will break at runtime. When on JDK 9+, consider -release as a safer alternative. Choices: 8, 9, 10, 11, 12, 13, 14, 15, 16, 17.
   */
  def Xtarget = List("-Xtarget")

  /**
   * Print the stack trace when the tree with the given id is created. Default: -2147483648.
   */
  override def YdebugTreeWithId = List("-Ydebug-tree-with-id")

  /**
   * Allow `*` as type lambda placeholder to be compatible with kind projector. When invoked as -Ykind-projector:underscores will repurpose `_` to be a type parameter placeholder, this will disable usage of underscore as a wildcard. Default: disable. Choices: disable, , underscores.
   */
  override def YkindProjector = List("-Ykind-projector")

  /**
   * Resolve term conflicts Default: error. Choices: package, object, error.
   */
  override def YresolveTermConflict = List("-Yresolve-term-conflict")

  /**
   * 2-unpickler          Control where we may get Scala 2 symbols from. This is either "always", "never", or a classpath. Default: always.
   */
  override def Yscala = List("-Yscala")
}

object V3_1_0 extends V3_1_0
