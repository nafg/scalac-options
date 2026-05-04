// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_3_6 extends V3_3_6_+ {
  /**
   * Enable or disable specific `unused` warnings Choices :  - nowarn,  - all,  - imports : 	Warn if an import selector is not referenced. 	NOTE : overrided by -Wunused:strict-no-implicit-warn,  - privates : 	Warn if a private member is unused,  - locals : 	Warn if a local definition is unused,  - explicits : 	Warn if an explicit parameter is unused,  - implicits : 	Warn if an implicit parameter is unused,  - params : 	Enable -Wunused:explicits,implicits,  - linted : 	Enable -Wunused:imports,privates,locals,implicits,  - strict-no-implicit-warn : 	Same as -Wunused:import, only for imports of explicit named members. 	NOTE : This overrides -Wunused:imports and NOT set by -Wunused:all,  - unsafe-warn-patvars : 	(UNSAFE) Warn if a variable bound in a pattern is unused. 	This warning can generate false positive, as warning cannot be 	suppressed yet.
   */
  override def Wunused = List("-Wunused")
}

object V3_3_6 extends V3_3_6
