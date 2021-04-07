package io.github.nafg.scalacoptions.generator

case class Container(name: String,
                     parent: Option[Container],
                     settings: Seq[Setting],
                     isConcrete: Boolean) {
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
