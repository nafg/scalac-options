// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_2_+ extends V2_13_1_+ {
  /**
   * Enable optimizer warnings, `help` for details.
   */
  override def optWarnings(warnings: String) = List("-opt-warnings:" + warnings)

  /**
   * The absolute path of the project root directory, usually the git/scm checkout. Used by -Wconf.
   */
  def rootdir(path: String) = List("-rootdir", path)

  /**
   * Configure reporting of compiler warnings; use `help` for details.
   */
  def Wconf(patterns: String) = List("-Wconf:" + patterns)

  /**
   * Warn if a @nowarn annotation does not suppress any warnings.
   */
  def WunusedNowarn = List("-Wunused:nowarn")

  /**
   * Block adapted by implicit with by-name parameter.
   */
  def XlintBynameImplicit = List("-Xlint:byname-implicit")

  /**
   * Recursive call used default argument.
   */
  def XlintRecurseWithDefault = List("-Xlint:recurse-with-default")

  /**
   * Warn for specialization of Unit in parameter position.
   */
  def XlintUnitSpecial = List("-Xlint:unit-special")

  /**
   * Enable -Wunused:imports,privates,locals,implicits,nowarn.
   */
  override def XlintUnused = List("-Xlint:unused")

  def YreplUseMagicImports = List("-Yrepl-use-magic-imports")
}
