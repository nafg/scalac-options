// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_7_+ extends V2_12_6_+ {
  /**
   * <output style>         Show start and end positions of members (implies -Yrangepos)
   */
  override def YshowMemberPos = List("-Yshow-member-pos")

  /**
   * Validate positions after the given phases (implies -Yrangepos) <phases>
   */
  def YvalidatePos(phases: String) = List("-Yvalidate-pos:" + phases)
}
