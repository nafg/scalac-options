// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_6_+ extends V2_13_5_+ {
  /**
   * Print dependent missing implicits. [false]
   */
  override def Vimplicits = List("-Vimplicits")

  /**
   * <n>       max chars for printing refined types, abbreviate to `F {...}`
   */
  def VimplicitsMaxRefined = List("-Vimplicits-max-refined")

  /**
   * Display all intermediate implicits in a chain. [false]
   */
  def VimplicitsVerboseTree = List("-Vimplicits-verbose-tree")

  /**
   * Print found/required error messages as colored diffs. [false]
   */
  def VtypeDiffs = List("-Vtype-diffs")
}
