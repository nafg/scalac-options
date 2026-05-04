// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_11_2_+ extends V2_11_1_+ {
  /**
   * Enable or disable language features: `_' for all, `-language:help' to list
   */
  override def language(features: String) = List("-language:" + features)

  /**
   * Enable or disable specific warnings: `_' for all, `-Xlint:help' to list
   */
  def Xlint(`_,warning,-warning`: String) = List("-Xlint:" + `_,warning,-warning`)

  /**
   * Warn when non-nullary `def f()' overrides nullary `def f'.
   */
  override def YwarnNullaryOverride = List("-Ywarn-nullary-override")
}
