// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_11_8_+ extends V2_11_7_+ {
  /**
   * Select tab-completion in the REPL. (pc,adhoc,none) default:pc
   */
  def Ycompletion(provider: String) = List("-Ycompletion:" + provider)

  /**
   * Allow use of the presentation compiler from any thread
   */
  def YpresentationAnyThread = List("-Ypresentation-any-thread")
}
