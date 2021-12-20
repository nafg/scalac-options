package io.github.nafg.scalacoptions.generator

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
}
