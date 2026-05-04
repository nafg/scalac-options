import sjsonnew.BasicJsonProtocol.{flatUnionFormat2, isoStringFormat}
import sjsonnew.{IsoString, JsonFormat}
import zio.json.{DeriveJsonCodec, JsonCodec, jsonHint}


sealed trait FlagSegment

object FlagSegment {
  @jsonHint("lit")
  case class Literal(text: String) extends FlagSegment

  @jsonHint("param")
  case class Parameter(name: String) extends FlagSegment

  implicit val literalIsoString: IsoString[Literal]       =
    IsoString.iso(_.text, Literal)
  implicit val parameterIsoString: IsoString[Parameter]   =
    IsoString.iso(_.name, Parameter)
  implicit val flagSegmentFormat: JsonFormat[FlagSegment] =
    flatUnionFormat2[FlagSegment, Literal, Parameter]

  implicit val literalCodec: JsonCodec[Literal]     = JsonCodec.string.transform(Literal(_), _.text)
  implicit val parameterCodec: JsonCodec[Parameter] = JsonCodec.string.transform(Parameter(_), _.name)
  implicit val codec: JsonCodec[FlagSegment]        = DeriveJsonCodec.gen[FlagSegment]
}
