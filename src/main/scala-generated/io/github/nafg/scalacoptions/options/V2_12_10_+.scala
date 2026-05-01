// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_10_+ extends V2_12_9_+ {
  /**
   * factory for jar files
   */
  def YjarFactory(classname: String) = List("-YjarFactory", classname)

  def YpresentationLocateSourceFile = List("-Ypresentation-locate-source-file")
}
