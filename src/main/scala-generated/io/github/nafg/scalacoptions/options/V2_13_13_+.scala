// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_13_+ extends V2_13_12_+ {
  /**
   * Modules (objects) should not be Cloneable.
   */
  def XlintCloneable = List("-Xlint:cloneable")

  /**
   * Pattern variable id is also a term in scope.
   */
  def XlintPatternShadow = List("-Xlint:pattern-shadow")

  /**
   * Enable warnings and features for a future version.
   */
  override def Xsource(version: String) = List("-Xsource:" + version)

  /**
   * Expose platform packages hidden under --release
   */
  def Yrelease(packages: String) = List("-Yrelease:" + packages)
}
