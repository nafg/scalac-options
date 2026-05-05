import coursier.core.{Module, ModuleName, Organization}
import coursier.{Dependency, Fetch}

import scala.collection.immutable.{ListMap, SortedMap}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}


object Generator {
  lazy val parser = FastParseParser

  private def parseOutputs(version: Versions.Minor, outputs: Seq[(String, String)]) =
    outputs
      .flatMap { case (flag, output) =>
        parser.parse(output, s"$version $flag")
      }
      .toMap
      .values
      .flatten
      .toSeq
      .groupBy(_.name)
      .flatMap { case (_, settings) =>
        val unique = settings.map(_.mergeLiteralSegments).distinct
        if (unique.map(_.copy(isDeprecated = false)).distinct.length == 1)
          List(unique.head.copy(isDeprecated = unique.exists(_.isDeprecated)))
        else
          unique
      }
      .toSeq
      .sortBy(_.name)(
        Ordering.comparatorToOrdering(String.CASE_INSENSITIVE_ORDER)
      )

  private def common(settingsGroups: Seq[Seq[Setting]]) =
    settingsGroups
      .map(_.map(setting => setting.name -> setting).toMap)
      .reduceLeft { (settings1, settings2) =>
        val commonNames = settings1.keySet.intersect(settings2.keySet)
        commonNames.flatMap { name =>
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
                  description =
                    if (s1.description == s2.description) s1.description
                    else "",
                  isDeprecated = s1.isDeprecated && s2.isDeprecated
                )
            )
        }.toMap
      }
      .values
      .toSeq
      .sortBy(_.name)(
        Ordering.comparatorToOrdering(String.CASE_INSENSITIVE_ORDER)
      )

  case class Result(allContainers: Seq[Container], versionMap: ListMap[String, String])

  private def versions = Versions.loadVersions()

  private def dependency(version: Versions.Minor) =
    Dependency(
      Module(
        Organization("org.scala-lang"),
        ModuleName(
          version match {
            case Versions.Minor(2, _, _, _, _, _)       => "scala-compiler"
            case Versions.Minor(3, _, _, Some(_), _, _) => s"scala3-compiler_${version.versionString}"
            case Versions.Minor(3, _, _, None, _, _)    => "scala3-compiler_3"
            case _                                      => sys.error("Unsupported version: " + version)
          }
        ),
        Map.empty
      ),
      version.versionString
    )

  def prefetch: Future[Unit] = prefetch(versions.flatMap(_.allMinors))

  def prefetch(versions: Seq[Versions.Minor]) =
    Fetch()
      .addDependencies(versions.map(dependency)*)
      .future().map(_ => ())

  def getOutputs(versions: Seq[Versions.Minor])(runScalac: (Versions.Minor, String) => String) = Await.result(
    Future.traverse(versions) { version =>
      println(s"Getting output from $version")
      Future
        .traverse(version.helpFlags)(flag => Future(runScalac(version, flag)).map(flag -> _))
        .map(pages => version -> pages)
        .andThen {
          case Success(_) => println(s"Finished getting output from $version")
          case Failure(e) => Console.err.println(s"Failed to get output from $version: ${e.getMessage}")
        }
    },
    Duration.Inf
  )

  type Outputs = Seq[(Versions.Minor, Seq[(String, String)])]

  def parseAllOutputs(outputs: Outputs) = {
    val allSettings =
      outputs.map { case (version, pages) =>
        val settings =
          parseOutputs(version, pages)
            .map { s =>
              version.settings.get(s.name) match {
                case None           => s
                case Some(segments) => s.copy(flagSegments = segments)
              }
            }
        println(s"Parsed ${settings.length} settings for $version")
        version -> settings
      }

    val groupedEpoch = allSettings.groupBy(_._1.epoch)
    val groupedMajor = allSettings.groupBy(t => (t._1.epoch, t._1.major))
    val groupedRange =
      SortedMap.empty[Versions.Minor, Seq[(Versions.Minor, Seq[Setting])]] ++
        groupedMajor.flatMap { case (_, settings) =>
          settings.tails.filterNot(_.isEmpty).map(t => t.head._1 -> t)
        }

    val commonAll   = common(allSettings.map(_._2))
    val commonEpoch = groupedEpoch.mapValues(settings => common(settings.map(_._2)))
    val commonMajor = groupedMajor.mapValues(settings => common(settings.map(_._2)))
    val commonRange = groupedRange.mapValues(settings => common(settings.map(_._2)))

    println(s"All: ${commonAll.length} common settings")
    for ((v, s) <- commonEpoch) println(s"$v: ${s.length} common settings")
    for (((e, m), s) <- commonMajor)
      println(s"$e.$m: ${s.length} common settings")
    for ((v, s) <- commonRange)
      println(s"${v.versionString}+ ${s.length} common settings")

    val commonContainer    =
      Container("Common", None, commonAll, isConcrete = false)
    val epochContainers    = commonEpoch.transform { (epoch, settings) =>
      Container(s"V$epoch", Some(commonContainer), settings, isConcrete = false)
    }
    val majorContainers    = commonMajor.transform {
      case ((epoch, major), settings) =>
        val parent = epochContainers(epoch)
        Container(
          s"V${epoch}_$major",
          Some(parent),
          settings,
          isConcrete = false
        )
    }
    val rangeContainers    =
      commonRange.foldLeft(Map.empty[Versions.Minor, Container]) {
        case (map, (version @ Versions.Minor(epoch, major, minor, prerelease, _, _), settings)) =>
          val parent =
            prerelease
              .flatMap { case (alpha, num) =>
                map.get(version.copy(prerelease = Some((alpha, num - 1))))
              }
              .orElse(map.get(version.copy(minor = minor - 1)))
              .getOrElse(majorContainers((epoch, major)))
          val name   =
            s"V${epoch}_${major}_$minor" + version.prereleaseString.fold("")("_" + _) + "_+"
          map + (version -> Container(name, Some(parent), settings, isConcrete = false))
      }
    val concreteContainers = ListMap(allSettings*).transform {
      case (version @ Versions.Minor(epoch, major, minor, _, _, _), settings) =>
        val parent = rangeContainers(version)
        val name   = s"V${epoch}_${major}_$minor" + version.prereleaseString.fold("")("_" + _)
        Container(name, Some(parent), settings, isConcrete = true)
    }

    Result(
      allContainers =
        List(commonContainer) ++
          epochContainers.values ++
          majorContainers.values ++
          rangeContainers.values ++
          concreteContainers.values,
      versionMap = concreteContainers.map { case (version, container) =>
        (version.versionString, container.name)
      }
    )
  }
}
