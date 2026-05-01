// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_9_+ extends V2_13_8_+ {
  /**
   * Enable optimizations: `-opt:local`, `-opt:inline:<pattern>`; `-opt:help` for details.
   */
  override def opt(optimizations: String) = List("-opt:" + optimizations)

  /**
   * Compile for a version of the Java API and target class file. (8,9,10,11,12,13,14,15,16,17,18,19,20,[21])
   */
  def release(release: String) = List("-release:" + release)

  /**
   * Print the stack trace when any error is caught. [false]
   */
  def VdebugTypeError = List("-Vdebug-type-error")

  /**
   * Warn when if statements are non-Unit expressions, enabled by -Wnonunit-statement. [false]
   */
  def WnonunitIf = List("-Wnonunit-if")

  /**
   * Warn when block statements are non-Unit expressions. [false]
   */
  def WnonunitStatement = List("-Wnonunit-statement")

  /**
   * Enable optimizer warnings, `help` for details.
   */
  def Wopt(warnings: String) = List("-Wopt:" + warnings)

  /**
   * Enable or disable specific lints for performance
   */
  def Wperformance(warnings: String) = List("-Wperformance:" + warnings)

  def XlintUniversalMethods = List("-Xlint:universal-methods")

  /**
   * How to print trees when -Vprint is enabled. ([text],compact,format,text+format,diff)
   */
  override def YprintTrees(style: String) = List("-Yprint-trees:" + style)

  def Yscala = List("-Yscala")
}
