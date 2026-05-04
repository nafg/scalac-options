// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V3_0_0 extends V3_0_0_+ {
  /**
   * Generate a documentation snapshot for the current Dotty version
   */
  def docSnapshot = List("-doc-snapshot")

  /**
   * The name of the project.
   */
  def project = List("-project")

  /**
   * The file that contains the project's logo (in /images).
   */
  def projectLogo = List("-project-logo")

  /**
   * The current version of your project.
   */
  def projectVersion = List("-project-version")

  /**
   * A directory containing static files from which to generate documentation. Default: ./docs.
   */
  def siteroot = List("-siteroot")

  /**
   * Fail the compilation if there are any warnings.
   */
  def XfatalWarnings = List("-Xfatal-warnings")

  /**
   * Print out program after
   */
  def Xprint = List("-Xprint")

  /**
   * Cook the comments (type check `@usecase`, etc.)
   */
  def YcookComments = List("-Ycook-comments")

  /**
   * Drop comments when scanning source files.
   */
  def YdropComments = List("-Ydrop-comments")

  /**
   * Allow `*` as wildcard to be compatible with kind projector.
   */
  override def YkindProjector = List("-Ykind-projector")
}

object V3_0_0 extends V3_0_0
