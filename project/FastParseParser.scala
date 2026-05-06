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

  private def settingLineStart[_: P] = P("--" | "-" ~ (CharIn("A-Z") | letters) | "@")

  // 8-space minimum captures both Scala 3.0+ continuations (typically 22-space indent) and
  // Scala 3.1/3.2 -Wunused continuations (9-space indent), where the "Choices:" line lives.
  private def extraLines[_: P] = P((" ".rep(8) ~ spaces ~ !settingLineStart ~ untilLineEnd).rep)

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

  def nameFor(flagSegments: Seq[FlagSegment]): String = {
    val words =
      flagSegments
        .collect { case FlagSegment.Literal(text) => text }
        .flatMap(_.split("[:= -]"))
        .filter(_.nonEmpty)
    words.head + words.tail.map(_.capitalize).mkString
  }

  private def settingLine[_: P] = P(
    (settingName
      ~ spaces
      ~ (untilLineEnd.? ~ extraLines).map { case (x, xs) =>
        (x.toSeq ++ xs).mkString(" ")
      })
      .map { case (flagSegments, description) =>
        Setting(nameFor(flagSegments), flagSegments, description)
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

  private val ansiRegex = "\u001B\\[[;\\d]*m".r

  // --- choices block parsing ---
  // Scalac help text describes multi-choice flags (-Wunused, -Wshadow, -color, ...) inline:
  //   3.3+: "Enable or disable specific `unused` warnings Choices :  - nowarn,  - all,  - imports : Warn ..."
  //   3.1/3.2: "Enable or disable specific `unused` warnings Choices: nowarn, all."
  // The parser splits this into a leading description plus a list of choice names. The dashed
  // form must match the full "- name" marker because choice descriptions can contain commas and
  // embedded examples like "Enable -Wunused:imports,privates" that must NOT be parsed as choices.

  private def lowerLetter[_: P]: P[Unit]     = P(CharPred(c => c >= 'a' && c <= 'z'))
  private def lowerOrDash[_: P]: P[Unit]     = P(CharPred(c => c >= 'a' && c <= 'z' || c == '-'))
  private def choiceWord[_: P]: P[String]    = P((lowerLetter ~ lowerOrDash.rep).!)

  private def skipUntilDash[_: P]: P[Unit]   = P((!"- " ~ AnyChar).rep)
  private def dashedItem[_: P]: P[String]    = P("- " ~ choiceWord)
  private def dashedChoices[_: P]: P[Seq[String]] =
    P(skipUntilDash ~ (dashedItem ~ skipUntilDash).rep(1)).map(_.toSeq)

  private def commaSep[_: P]: P[Unit]        = P("," ~ " ".rep)
  private def bareChoices[_: P]: P[Seq[String]] =
    P(choiceWord.rep(min = 1, sep = commaSep))

  private def beforeMarker[_: P]: P[String]  = P((!"Choices" ~ AnyChar).rep.!)
  private def marker[_: P]: P[Unit]          = P("Choices" ~ ":".? ~ " ".rep)
  private def descWithChoices[_: P]: P[(String, Seq[String])] =
    P(beforeMarker ~ marker ~ (dashedChoices | bareChoices))

  /** When a Setting's description contains a "Choices" block, replace it with one Setting per
    * choice. The bare-flag Setting is unusable (scalac requires the choice argument), so it's
    * dropped.
    */
  def expandChoices(setting: Setting): Seq[Setting] =
    fastparse.parse(setting.description, descWithChoices(_)) match {
      case Parsed.Success((mainDesc, names), _) if names.nonEmpty =>
        val baseFlag = setting.flagSegments.collect { case FlagSegment.Literal(t) => t }.mkString
        names.distinct.map { choice =>
          val newSegments = Seq(FlagSegment.Literal(s"$baseFlag:$choice"))
          Setting(
            name = nameFor(newSegments),
            flagSegments = newSegments,
            description =
              if (mainDesc.trim.isEmpty) s"$baseFlag:$choice"
              else s"${mainDesc.trim} ($choice)",
            isDeprecated = setting.isDeprecated
          )
        }
      case _                                                      => Seq(setting)
    }

  /** @param text
    *   output of scalac help output
    * @return
    *   a Map of section descriptions to settings within them
    */
  def parse(text: String, sourceName: String = "unknown source"): Map[String, Seq[Setting]] = {
    val textWithoutANSI = ansiRegex.replaceAllIn(text, "")
    fastparse.parse(textWithoutANSI, parser(_), verboseFailures = true) match {
      case Parsed.Success(groups, index) =>
        val asMap     = groups.toMap.map { case (name, settings) =>
          val expanded = settings.flatMap(expandChoices)
          if (name.trim == "Deprecated settings:")
            name         -> expanded.map(_.copy(isDeprecated = true))
          else name.trim -> expanded
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
