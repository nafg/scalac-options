// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_11_5_+ extends V2_11_4_+ {
  /**
   * Choose classpath scanning method. (recursive,flat) default:recursive
   */
  def YclasspathImpl(implementation: String) = List("-YclasspathImpl:" + implementation)

  /**
   * Do not cache flat classpath representation of classpath elements from jars across compiler instances.
   */
  def YdisableFlatCpCaching = List("-YdisableFlatCpCaching")
}
