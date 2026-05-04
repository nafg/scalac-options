// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_0_1_+ extends V3_0_0_+ {
  /**
   * Cook the documentation (type check `@usecase`, etc.)
   */
  def YcookDocs = List("-Ycook-docs")

  /**
   * Drop documentation when scanning source files.
   */
  def YdropDocs = List("-Ydrop-docs")

  /**
   * Allow `*` as type lambda placeholder to be compatible with kind projector. When invoked as -Ykind-projector:underscores will repurpose `_` to be a type parameter placeholder, this will disable usage of underscore as a wildcard. Default: disable. Choices: disable, , underscores.
   */
  override def YkindProjector = List("-Ykind-projector")

  /**
   * Read documentation from tasty.
   */
  def YreadDocs = List("-Yread-docs")
}
