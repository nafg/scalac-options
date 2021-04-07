package io.github.nafg.scalacoptions.generator


case class Setting(name: String,
                   flagSegments: Seq[FlagSegment],
                   description: String,
                   isDeprecated: Boolean = false) {
  def mergeLiteralSegments = copy(flagSegments = flagSegments.foldRight(List.empty[FlagSegment]) {
    case (FlagSegment.Literal(text1), FlagSegment.Literal(text2) :: segments) =>
      FlagSegment.Literal(text1 + text2) :: segments
    case (segment, segments)                                                  =>
      segment :: segments
  })
}
