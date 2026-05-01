// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_3_7_+ extends V3_3_6_+ {
  /**
   * Quit REPL after evaluating the init script.
   */
  def replQuitAfterInit = List("-repl-quit-after-init")

  /**
   * Warn when a method calls itself with a default argument.
   */
  def WrecurseWithDefault = List("-Wrecurse-with-default")

  /**
   * Warn a standard interpolator used toString on a reference type.
   */
  def WtostringInterpolated = List("-Wtostring-interpolated")

  /**
   * Enable or disable specific `unused` warnings Choices :  - nowarn,  - all,  - imports : 	Warn if an import selector is not referenced.,  - privates : 	Warn if a private member is unused,  - locals : 	Warn if a local definition is unused,  - explicits : 	Warn if an explicit parameter is unused,  - implicits : 	Warn if an implicit parameter is unused,  - params : 	Enable -Wunused:explicits,implicits,  - patvars : 	Warn if a variable bound in a pattern is unused,  - linted : 	Enable -Wunused:imports,privates,locals,implicits,  - strict-no-implicit-warn : 	Same as -Wunused:imports, only for imports of explicit named members. 	NOTE : This overrides -Wunused:imports and NOT set by -Wunused:all,  - unsafe-warn-patvars : 	Deprecated alias for `patvars`
   */
  override def Wunused = List("-Wunused")
}
