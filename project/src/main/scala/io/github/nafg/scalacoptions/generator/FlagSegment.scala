package io.github.nafg.scalacoptions.generator


object FlagSegment {
  case class Literal(text: String) extends FlagSegment

  case class Parameter(name: String) extends FlagSegment
}

sealed trait FlagSegment
