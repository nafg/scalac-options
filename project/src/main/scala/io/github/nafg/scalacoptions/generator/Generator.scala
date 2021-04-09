package io.github.nafg.scalacoptions.generator

import sjsonnew.BasicJsonProtocol._
import sjsonnew._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.AnsiColor


object Generator {
  lazy val parser = FastParseParser

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

  private def parseOutputs(outputs: Seq[String]) =
    outputs
      .flatMap { output =>
        parser.parse(output) match {
          case Right(all)  => List(all)
          case Left(index) =>
            printFailureLocation(output, index)
            Nil
        }
      }
      .flatten.toMap.values.flatten.toSeq
      .groupBy(_.name)
      .flatMap { case (_, settings) =>
        val unique = settings.map(_.mergeLiteralSegments).distinct
        if (unique.map(_.copy(isDeprecated = false)).distinct.length == 1)
          List(unique.head.copy(isDeprecated = unique.exists(_.isDeprecated)))
        else
          unique
      }
      .toSeq
      .sortBy(_.name)(Ordering.comparatorToOrdering(String.CASE_INSENSITIVE_ORDER))

  private def common(settingsGroups: Seq[Seq[Setting]]) =
    settingsGroups.map(_.map(setting => setting.name -> setting).toMap)
      .reduceLeft { (settings1, settings2) =>
        val commonNames = settings1.keySet.intersect(settings2.keySet)
        commonNames
          .flatMap { name =>
            val s1 = settings1(name)
            val s2 = settings2(name)
            if (s1.flagSegments != s2.flagSegments)
              None
            else
              Some(
                name ->
                  Setting(
                    name = name,
                    flagSegments = s1.flagSegments,
                    description = if (s1.description == s2.description) s1.description else "",
                    isDeprecated = s1.isDeprecated && s2.isDeprecated
                  )
              )
          }
          .toMap
      }
      .values
      .toSeq
      .sortBy(_.name)(Ordering.comparatorToOrdering(String.CASE_INSENSITIVE_ORDER))

  case class Result(allContainers: Seq[Container], versionMap: Map[String, String])

  object Result {
    implicit val resultLListIso: IsoLList.Aux[Result, Seq[Container] :*: Map[String, String] :*: LNil] =
      LList.iso(
        { case Result(allContainers, versionMap) =>
          ("allContainers" -> allContainers) :*: ("versionMap" -> versionMap) :*: LNil
        },
        { case (_, allContainers) :*: (_, versionMap) :*: LNil => Result(allContainers, versionMap) }
      )
  }

  def prefetch = GetHelpString.fetchAll(GetHelpString.versionsAndHelpFlags.map(_._1)).map(_ => ())

  def getOutputs =
    Future.traverse(GetHelpString.versionsAndHelpFlags) { case (version, flags) =>
      println(s"Getting output from $version")
      GetHelpString.runner(version)
        .map { runner =>
          val res = version -> flags.toSeq.map(flag => flag -> runner(flag))
          println(s"Finished getting output from $version")
          res
        }
    }

  type Outputs = Seq[(String, Seq[(String, String)])]

  def parseAllOutputs(outputs: Outputs) = {
    val allSettings =
      outputs.map { case (version, pages) =>
        println(s"Parsing settings for $version")
        version -> parseOutputs(pages.map(_._2))
      }

    val groupedMajor = allSettings.groupBy(_._1.split('.').take(1))
    val groupedMinor = allSettings.groupBy(_._1.split('.').take(2))

    val commonAll = common(allSettings.map(_._2))
    val commonMajor = groupedMajor.mapValues(settings => common(settings.map(_._2)))
    val commonMinor = groupedMinor.mapValues(settings => common(settings.map(_._2)))

    val commonContainer = Container("Common", None, commonAll, isConcrete = false)
    val majorContainers =
      commonMajor
        .map { case (major, settings) =>
          major.toList -> Container("V" + major.mkString("_"), Some(commonContainer), settings, isConcrete = false)
        }
    val minorContainers =
      commonMinor
        .map { case (minor, settings) =>
          val parent = majorContainers.get(minor.take(1).toList)
          minor.toList -> Container("V" + minor.mkString("_"), parent, settings, isConcrete = false)
        }
    val concreteContainers =
      allSettings
        .map { case (version, settings) =>
          val parent = minorContainers.get(version.split('.').take(2).toList)
          version -> Container("V" + version.replaceAll("[.-]", "_"), parent, settings, isConcrete = true)
        }
        .toMap

    Result(
      allContainers =
        List(commonContainer) ++ majorContainers.values ++ minorContainers.values ++ concreteContainers.values,
      versionMap = concreteContainers.mapValues(_.name)
    )
  }
}
