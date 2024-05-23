import scala.meta.*


object CodeGen {
  def makeDef(setting: Setting, isOverride: Boolean): String = {
    val params = setting.flagSegments.toList.collect {
      case FlagSegment.Parameter(name) => name
    }
    val elems = {
      case class State(finished: List[List[FlagSegment]], current: List[FlagSegment])
      val result =
        setting.flagSegments.foldLeft(State(Nil, Nil)) {
          case (State(finished, current), segment) =>
            segment match {
              case FlagSegment.Literal(text) if text.endsWith(" ") =>
                State(
                  finished =
                    finished :+ (current :+ FlagSegment.Literal(text.trim)),
                  current = Nil
                )
              case segment                                         =>
                State(finished = finished, current = current :+ segment)
            }
        }
      result.finished :+ result.current
    }

    def plusTerms(terms: Seq[Term]) = terms.reduceLeft((a, b) =>
      Term.ApplyInfix(a, Term.Name("+"), Type.ArgClause(Nil), Term.ArgClause(List(b)))
    )

    def option(segments: Seq[FlagSegment]) = plusTerms(segments.map {
      case FlagSegment.Literal(text)   => Lit.String(text)
      case FlagSegment.Parameter(name) => Term.Name(name)
    })

    def options(segments: List[Seq[FlagSegment]]) =
      Term.Apply(Term.Name("List"), Term.ArgClause(segments.map(option)))

    val deprecatedMods =
      if (!setting.isDeprecated) Nil
      else
        List(
          Mod.Annot(
            Init(
              Type.Name("deprecated"),
              Name.Anonymous(),
              List(Term.ArgClause(List(Lit.String("See doc comment"), Lit.String(""))))
            )
          )
        )

    val overrideMods = if (!isOverride) Nil else List(Mod.Override())

    val comment =
      if (setting.description.trim.isEmpty) ""
      else
        "  /**\n" +
          setting.description.linesWithSeparators
            .map("   * " + _.replace("\\u", "\\\\u"))
            .mkString + "\n   */\n"

    comment + "  " +
      Defn
        .Def(
          mods = deprecatedMods ++ overrideMods,
          name = Term.Name(setting.name),
          paramClauseGroups = List(
            Member.ParamClauseGroup(
              tparamClause = Type.ParamClause(Nil),
              paramClauses =
                if (params.isEmpty)
                  Nil
                else
                  List(
                    Term.ParamClause(
                      params.map { name =>
                        Term.Param(
                          Nil,
                          Term.Name(name),
                          Some(Type.Name("String")),
                          None
                        )
                      }
                    )
                  )
            )
          ),
          decltpe = None,
          body = options(elems)
        )
        .toString
  }
}
