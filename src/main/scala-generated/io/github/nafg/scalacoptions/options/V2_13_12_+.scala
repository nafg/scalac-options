// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_13_12_+ extends V2_13_11_+ {
  /**
   * Apply quick fixes provided by the compiler for warnings and errors to source files
   */
  def quickfix(filters: String) = List("-quickfix:" + filters)
}
