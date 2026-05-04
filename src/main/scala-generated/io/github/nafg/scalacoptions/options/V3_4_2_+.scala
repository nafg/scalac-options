// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_4_2_+ extends V3_4_1_+ {
  /**
   * List of regexes for packages, classes and modules to exclude from coverage.
   */
  def coverageExcludeClasslikes = List("-coverage-exclude-classlikes")

  /**
   * List of regexes for files to exclude from coverage.
   */
  def coverageExcludeFiles = List("-coverage-exclude-files")

  /**
   * Pass -D<property=value> directly to the runtime system.
   */
  def D(`property=value`: String) = List("-D" + `property=value`)

  /**
   * Pass -J<flag> directly to the runtime system.
   */
  override def J(flag: String) = List("-J" + flag)

  /**
   * Warn when a comment ambiguously assigned to multiple enum cases is discarded.
   */
  def WenumCommentDiscard = List("-Wenum-comment-discard")

  /**
   * Enable or disable specific `shadow` warnings Choices :  - all,  - private-shadow : 	Warn if a private field or class parameter shadows a superclass field,  - type-parameter-shadow : 	Warn when a type parameter shadows a type already in the scope
   */
  def Wshadow = List("-Wshadow")

  /**
   * Enable or disable specific warnings
   */
  override def Xlint = List("-Xlint")

  /**
   * Disable experimental language features by default in NIGHTLY/SNAPSHOT versions of the compiler.
   */
  override def YnoExperimental = List("-Yno-experimental")

  /**
   * Self-test for pickling -print-tasty output; should be used with -Ytest-pickler.
   */
  def YtestPicklerCheck = List("-Ytest-pickler-check")
}
