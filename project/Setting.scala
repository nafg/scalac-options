import sjsonnew.BasicJsonProtocol.{BooleanJsonFormat, StringJsonFormat, seqFormat}
import sjsonnew.{:*:, IsoLList, LList, LNil}


case class Setting(name: String, flagSegments: Seq[FlagSegment], description: String, isDeprecated: Boolean = false) {
  def mergeLiteralSegments = copy(flagSegments = flagSegments.foldRight(List.empty[FlagSegment]) {
    case (FlagSegment.Literal(text1), FlagSegment.Literal(text2) :: segments) =>
      FlagSegment.Literal(text1 + text2) :: segments
    case (segment, segments)                                                  =>
      segment :: segments
  })
}

object Setting {
  implicit val settingLListIso: IsoLList.Aux[Setting, String :*: Seq[FlagSegment] :*: String :*: Boolean :*: LNil] =
    LList.iso(
      { case Setting(name, flagSegments, description, isDeprecated) =>
        ("name" -> name) :*:
          ("flagSegments" -> flagSegments) :*:
          ("description" -> description) :*:
          ("isDeprecated" -> isDeprecated) :*:
          LNil
      },
      { case (_, name) :*: (_, flagSegments) :*: (_, description) :*: (_, isDeprecated) :*: LNil =>
        Setting(name, flagSegments, description, isDeprecated)
      }
    )
}
