package io.github.nafg.scalacoptions.generator

import sbt.util.StampedFormat.lazyFormat
import sjsonnew.BasicJsonProtocol.{BooleanJsonFormat, StringJsonFormat, isolistFormat, optionFormat, seqFormat}
import sjsonnew._


case class Container(name: String, parent: Option[Container], settings: Seq[Setting], isConcrete: Boolean) {
  def inherited(setting: Setting): Option[Setting] =
    parent.flatMap { p =>
      def signature(setting: Setting) =
        (
          setting.name,
          setting.flagSegments.count(_.isInstanceOf[FlagSegment.Parameter])
        )

      p.settings
        .find(s => signature(s) == signature(setting))
        .orElse(p.inherited(setting))
    }

  def code = {
    val methods = settings.flatMap { setting =>
      inherited(setting) match {
        case Some(`setting`) => None
        case Some(_)         => Some(CodeGen.makeDef(setting, isOverride = true))
        case None            => Some(CodeGen.makeDef(setting, isOverride = false))
      }
    }
    "trait " + name +
      parent.fold("")(" extends " + _.name) +
      (if (methods.isEmpty) "" else methods.mkString(" {\n", "\n\n", "\n}")) +
      (if (!isConcrete) "" else s"\n\nobject $name extends $name")
  }
}

object Container {
  lazy val containerLListIso: IsoLList.Aux[
    Container,
    String :*: Option[Container] :*: Seq[Setting] :*: Boolean :*: LNil
  ] =
    LList.iso(
      { case Container(name, parent, settings, isConcrete) =>
        ("name" -> name) :*:
          ("parent" -> parent) :*:
          ("settings" -> settings) :*:
          ("isConcrete" -> isConcrete) :*:
          LNil
      },
      {
        case (_, name) :*: (_, parent) :*: (_, settings) :*: (
              _,
              isConcrete
            ) :*: LNil =>
          Container(name, parent, settings, isConcrete)
      }
    )
  implicit lazy val containerFormat: JsonFormat[Container] = lazyFormat(
    isolistFormat(containerLListIso)
  )
}
