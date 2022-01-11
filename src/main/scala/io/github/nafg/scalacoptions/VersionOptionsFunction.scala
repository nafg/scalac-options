package io.github.nafg.scalacoptions

import scala.reflect.ClassTag


trait VersionOptionsFunction {
  def toPartialFunction: PartialFunction[options.Common, List[String]]
}

object VersionOptionsFunction {
  implicit class FromClassTag[A <: options.Common : ClassTag](f: A => List[String]) extends VersionOptionsFunction {
    def toPartialFunction: PartialFunction[options.Common, List[String]] = {
      case a: A => f(a)
    }
  }

  case class Partial(partialFunction: PartialFunction[options.Common, List[String]]) extends VersionOptionsFunction {
    override def toPartialFunction: PartialFunction[options.Common, List[String]] =
      partialFunction
  }
}
