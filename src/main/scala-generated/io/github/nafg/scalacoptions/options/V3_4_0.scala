// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_4_0 extends V3_4_0_+ {
  /**
   * Pass -Dproperty=value directly to the runtime system.
   */
  def D(property: String, value: String) = List("-D" + property + "=" + value)

  /**
   * Pass <flag> directly to the runtime system.
   */
  override def J(flag: String) = List("-J" + flag)

  /**
   * Enable or disable specific `lint` warnings Choices :  - all,  - private-shadow : 	Warn if a private field or class parameter shadows a superclass field,  - type-parameter-shadow : 	Warn when a type parameter shadows a type already in the scope
   */
  override def Xlint = List("-Xlint")

  /**
   * Make non-transparent inline methods inline when typing. Emulates the old inlining behavior of 3.0.0-M3.
   */
  def YforceInlineWhileTyping = List("-Yforce-inline-while-typing")

  /**
   * Disable experimental language features.
   */
  override def YnoExperimental = List("-Yno-experimental")
}

object V3_4_0 extends V3_4_0
