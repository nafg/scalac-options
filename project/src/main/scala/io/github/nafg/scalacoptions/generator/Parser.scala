package io.github.nafg.scalacoptions.generator


trait Parser {
  def parse(text: String): Either[Int, Map[String, Seq[Setting]]]
}
