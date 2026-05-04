// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_3_4_+ extends V3_3_3_+ {
  /**
   * List of regexes for packages, classes and modules to exclude from coverage.
   */
  def coverageExcludeClasslikes = List("-coverage-exclude-classlikes")

  /**
   * List of regexes for files to exclude from coverage.
   */
  def coverageExcludeFiles = List("-coverage-exclude-files")

  /**
   * Explain cyclic reference errors in more detail.
   */
  def explainCyclic = List("-explain-cyclic")

  /**
   * Warn when a comment ambiguously assigned to multiple enum cases is discarded.
   */
  def WenumCommentDiscard = List("-Wenum-comment-discard")

  /**
   * Enable or disable specific `lint` warnings Choices :  - all,  - private-shadow : 	Warn if a private field or class parameter shadows a superclass field,  - type-parameter-shadow : 	Warn when a type parameter shadows a type already in the scope
   */
  def Xlint = List("-Xlint")

  /**
   * maximum worker threads for backend Default 1 Choices : 1..16
   */
  def YbackendParallelism = List("-Ybackend-parallelism")

  /**
   * backend threads worker queue size Default 0 Choices : 0..1000
   */
  def YbackendWorkerQueue = List("-Ybackend-worker-queue")

  /**
   * Print the stack trace when a cyclic reference error occurs.
   */
  def YdebugCyclic = List("-Ydebug-cyclic")

  /**
   * compression level to use when writing jar files Default -1 Choices : -1..9
   */
  def YjarCompressionLevel = List("-Yjar-compression-level")
}
