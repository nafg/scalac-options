import fastparse.NoWhitespace._
import fastparse._
import pprint.pprintln

import scala.io.AnsiColor


object FastParseParser {
  private def spaces[_: P] = P(" ".rep)

  private def placeholder[_: P] = P(
    ("<<" ~ CharsWhile(_ != '>').! ~ ">>") |
      ("<" ~ CharsWhile(_ != '>').! ~ ">")
  )

  private def lineEnd[_: P] = P("\n" | End)

  private def untilLineEnd[_: P] = P(CharsWhile(_ != '\n', 0).! ~ lineEnd)

  private def extraLines[_: P] = P((" ".rep(10) ~ spaces ~ untilLineEnd).rep)

  private def letters[_: P] = P(CharsWhile(_.isLetter))

  private def paramFlagName[_: P]: P[List[FlagSegment]] = P(
    ("-" ~ letters ~ " ").!.map(FlagSegment.Literal)
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

  private def settingName[_: P] = P(
    spaces
      ~ !"-- "
      ~ (
        fileFlag
          | propertyFlag
          | paramFlagName
          | flagName
      )
  )

  private def settingLine[_: P] = P(
    (settingName
      ~ spaces
      ~ (untilLineEnd.? ~ extraLines).map { case (x, xs) =>
        (x.toSeq ++ xs).mkString(" ")
      })
      .map { case (flagSegments, description) =>
        val words =
          flagSegments
            .collect { case FlagSegment.Literal(text) => text }
            .flatMap(_.split("[:= -]"))
            .filter(_.nonEmpty)
        val name = words.head + words.tail.map(_.capitalize).mkString
        Setting(name, flagSegments, description)
      }
  )

  private def settingsCluster[_: P] = P(settingLine.rep(1))

  private def notSettingLine[_: P] = P(!settingLine ~ untilLineEnd)

  private def settingsGroup[_: P] = P(
    notSettingLine.!.rep(min = 1, max = 20).map(_.last) ~ settingsCluster
  )

  private def parser[_: P] =
    P(settingsGroup.rep(1) ~ notSettingLine.rep(min = 0, max = 6)).map(_._1)

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

  /**
   * @param text output of scalac help output
   * @return a Map of section descriptions to settings within them
   */
  def parse(text: String): Map[String, Seq[Setting]] =
    fastparse.parse(text, parser(_), verboseFailures = true) match {
      case Parsed.Success(groups, index) =>
        val asMap = groups.toMap.map { case (name, settings) =>
          if (name.trim == "Deprecated settings:")
            name -> settings.map(_.copy(isDeprecated = true))
          else name.trim -> settings
        }
        val all = asMap
        val remaining = text.drop(index)
        if (remaining.nonEmpty) {
          println("Remaining: ")
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
