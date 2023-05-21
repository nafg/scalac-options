import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredCodec
import io.circe.{Codec, Encoder}
import sjsonnew.BasicJsonProtocol.{flatUnionFormat2, isoStringFormat}
import sjsonnew.{IsoString, JsonFormat}


sealed trait FlagSegment

object FlagSegment {
  case class Literal(text: String) extends FlagSegment

  case class Parameter(name: String) extends FlagSegment

  implicit val literalIsoString: IsoString[Literal] =
    IsoString.iso(_.text, Literal)
  implicit val parameterIsoString: IsoString[Parameter] =
    IsoString.iso(_.name, Parameter)
  implicit val flagSegmentFormat: JsonFormat[FlagSegment] =
    flatUnionFormat2[FlagSegment, Literal, Parameter]

  private def renamingConfig(f: PartialFunction[String, String]) =
    Configuration.default.copy(transformMemberNames = f.orElse { case s => s })

  implicit val codecLiteral: Codec[Literal] = {
    implicit val config = renamingConfig { case "text" => "lit" }
    deriveConfiguredCodec
  }

  implicit val codecParameter: Codec[Parameter] = {
    implicit val config = renamingConfig { case "name" => "param" }
    deriveConfiguredCodec
  }

  implicit val codec: Codec[FlagSegment] = Codec.from(
    codecLiteral.or(codecParameter.map(identity)),
    Encoder.instance {
      case literal: Literal     => codecLiteral(literal)
      case parameter: Parameter => codecParameter(parameter)
    }
  )
}
