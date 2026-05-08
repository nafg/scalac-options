import fastparse.NoWhitespace._
import fastparse._
import pprint.pprintln

import scala.io.AnsiColor


object FastParseParser {
  private object Text {
    def spaces[_: P]       = P(" ".rep)
    def lineEnd[_: P]      = P("\n" | End)
    def untilLineEnd[_: P] = P(CharsWhile(_ != '\n', 0).! ~ lineEnd)
    def letters[_: P]      = P(CharsWhile(_.isLetter))
  }

  private object Flags {
    def settingLineStart[_: P] = P("--" | "-" ~ (CharIn("A-Z") | Text.letters) | "@")

    private def placeholder[_: P] = P(
      ("<<" ~ CharsWhile(_ != '>').! ~ ">>") |
        ("<" ~ CharsWhile(_ != '>').! ~ ">")
    )

    private def paramFlagName[_: P]: P[List[FlagSegment]] = P(
      ("-" ~ Text.letters ~ " ").!.map(FlagSegment.Literal)
        ~ placeholder.map(FlagSegment.Parameter)
    ).map { case (l, p) => List(l, p) }

    private def flagName[_: P]: P[Seq[FlagSegment]] = P(
      "-".!.map(FlagSegment.Literal)
        ~ (
          CharsWhile(c => c == '-' || c == ':' || c.isLetter).!.map(FlagSegment.Literal) |
            placeholder.map(FlagSegment.Parameter)
        )
          .rep(1)
    ).map { case (segment, segments) => segment +: segments }

    private def propertyFlag[_: P] = P(
      ("-D" + "property=value").!.map { _ =>
        List(
          FlagSegment.Literal("-D"),
          FlagSegment.Parameter("property"),
          FlagSegment.Literal("="),
          FlagSegment.Parameter("value")
        )
      }
    )

    private def fileFlag[_: P] = P {
      ("@".!.map(FlagSegment.Literal) ~ placeholder.map(FlagSegment.Parameter))
        .map { case (l, p) => List(l, p) }
    }

    def settingName[_: P] = P(
      Text.spaces
        ~ !"-- "
        ~ (fileFlag | propertyFlag | paramFlagName | flagName)
    )

  }

  private object Descriptions {
    // 8-space minimum captures both Scala 3.0+ continuations (typically 22-space indent) and
    // Scala 3.1/3.2 -Wunused continuations (9-space indent), where the "Choices:" line lives.
    private def continuationLineStart[_: P] = P(" ".rep(8) ~ Text.spaces ~ !Flags.settingLineStart)

    private def continuation[_: P]: P[String] =
      P("\n" ~ continuationLineStart).map(_ => " ")

    private def descriptionChar[_: P]: P[String] =
      P(CharPred(_ != '\n').! | continuation)

    private def descriptionUntilChoices[_: P]: P[String] =
      P((!"Choices" ~ descriptionChar).rep.map(_.mkString))

    private def descriptionEnd[_: P]: P[Unit] = P(End | "\n" ~ !continuationLineStart)

    private def choicesMarker[_: P]: P[Unit] =
      P("Choices" ~ Text.spaces ~ ":".? ~ Text.spaces ~ ("\n" ~ continuationLineStart).? ~ Text.spaces)

    private def choiceBlockBody[_: P]: P[Unit] =
      P((!descriptionEnd ~ AnyChar).rep)

    private def descriptionWithChoices[_: P]: P[(String, Boolean)] =
      P(descriptionUntilChoices ~ choicesMarker ~ choiceBlockBody ~ Text.lineEnd)
        .map(_.trim -> true)

    private def plainDescription[_: P]: P[(String, Boolean)] =
      P(descriptionChar.rep.map(_.mkString) ~ Text.lineEnd)
        .map(_ -> false)

    def description[_: P]: P[(String, Boolean)] =
      P(descriptionWithChoices | plainDescription)
  }

  private object Sections {
    private def settingLine[_: P] = P(
      (Flags.settingName ~ Text.spaces ~ Descriptions.description)
        .map { case (flagSegments, (descText, hasChoices)) =>
          def literals(flagSegments: Seq[FlagSegment]) = flagSegments.collect { case FlagSegment.Literal(text) => text }
          val actualFlagSegments                       =
            if (hasChoices && !flagSegments.exists(_.isInstanceOf[FlagSegment.Parameter]))
              Seq(FlagSegment.Literal(s"${literals(flagSegments).mkString}:"), FlagSegment.Parameter("choice"))
            else
              flagSegments
          Setting(
            name = {
              val words = literals(actualFlagSegments).flatMap(_.split("[:= -]")).filter(_.nonEmpty)
              words.head + words.tail.map(_.capitalize).mkString
            },
            flagSegments = actualFlagSegments,
            description = descText
          )
        }
    )

    private def settingsCluster[_: P] = P(settingLine.rep(1))
    private def notSettingLine[_: P]  = P(!settingLine ~ Text.untilLineEnd)
    private def sectionHeader[_: P]   = P(notSettingLine.!.rep(min = 1, max = 20).map(_.last))
    private def settingsGroup[_: P]   = P(sectionHeader ~ settingsCluster)

    def parser[_: P] =
      P(settingsGroup.rep(1) ~ notSettingLine.rep(min = 0, max = 6)).map(_._1)
  }

  private def printFailureLocation(text: String, index: Int): Unit = {
    var n = 0
    for (line <- text.linesIterator if n < index) {
      if (index <= n || index > n + line.length) {
        Console.err.println(line)
      } else {
        Console.err.println(AnsiColor.RED + line + AnsiColor.RESET)
        Console.err.println(
          " " * (index - n) + AnsiColor.RED + "^" + AnsiColor.RESET
        )
      }

      n += line.length
    }
  }

  private val ansiRegex = "\u001B\\[[;\\d]*m".r

  /** @param text
    *   output of scalac help output
    * @return
    *   a Map of section descriptions to settings within them
    */
  def parse(text: String, sourceName: String = "unknown source"): Map[String, Seq[Setting]] = {
    val textWithoutANSI = ansiRegex.replaceAllIn(text, "")
    fastparse.parse(textWithoutANSI, Sections.parser(_), verboseFailures = true) match {
      case Parsed.Success(groups, index) =>
        val asMap     = groups.toMap.map { case (name, settings) =>
          if (name.trim == "Deprecated settings:")
            name         -> settings.map(_.copy(isDeprecated = true))
          else name.trim -> settings
        }
        val all       = asMap
        val remaining = textWithoutANSI.drop(index)
        if (remaining.nonEmpty) {
          println(s"Remaining from $sourceName: ")
          pprintln(remaining)
        }
        all
      case failure: Parsed.Failure       =>
        Console.err.println(
          failure.trace(enableLogging = true).longAggregateMsg
        )
        printFailureLocation(text, failure.index)
        throw new Exception(s"Parse Error, ${failure.msg}")
    }
  }
}
