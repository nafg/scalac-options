import scala.collection.immutable.SortedMap

import Versions.VersionConfig.PatchRange
import sbt.io.IO
import sbt.io.syntax.file
import sjsonnew.BasicJsonProtocol.*
import sjsonnew.{:*:, IsoLList, LList, LNil}
import zio.json.*
import zio.json.yaml.*


object Versions {
  case class Minor(
    epoch: Int,
    major: Int,
    minor: Int,
    prerelease: Option[(String, Int)],
    helpFlags: Seq[String],
    settings: Map[String, Seq[FlagSegment]] = Map.empty) {
    lazy val prereleaseString = prerelease.map { case (let, num) => let + num }
    lazy val versionString    =
      s"$epoch.$major.$minor" + prereleaseString.fold("")("-" + _)

    override def toString = versionString
  }

  object Minor {
    implicit val minorLListIso: IsoLList.Aux[
      Minor,
      (Int, Int, Int) :*: Option[(String, Int)] :*: Seq[String] :*: Map[String, Seq[FlagSegment]] :*: LNil
    ] =
      LList.iso(
        { case Minor(epoch, major, minor, prerelease, helpFlags, settings) =>
          "version"      -> (epoch, major, minor) :*:
            "prerelease" -> prerelease :*:
            "helpFlags"  -> helpFlags :*:
            "settings"   -> settings :*:
            LNil
        },
        { case (_, (epoch, major, minor)) :*: (_, prerelease) :*: (_, helpFlags) :*: (_, settings) :*: LNil =>
          Minor(epoch, major, minor, prerelease, helpFlags, settings)
        }
      )

    implicit val ordering: Ordering[Minor] =
      Ordering.by { case Minor(epoch, major, minor, prerelease, _, _) =>
        (epoch, major, minor, prerelease.getOrElse("" -> 0))
      }
  }

  case class Major(epoch: Int, major: Int, minors: Seq[Minor])

  case class Epoch(epoch: Int, majors: Seq[Major]) {
    def allMinors = majors.flatMap(_.minors)
  }

  @jsonExplicitEmptyCollections(encoding = false, decoding = false)
  case class VersionConfig(
    helpFlags: Seq[String],
    settings: Map[String, Seq[FlagSegment]] = Map.empty)
  object VersionConfig {
    case class PatchRange(first: Int, last: Int) {
      def values = first to last
    }
    object PatchRange                            {
      implicit val ordering: Ordering[PatchRange] = Ordering.by(r => (r.first, r.last))
    }
    private object AsInt                         {
      def unapply(s: String): Option[Int] =
        try Some(s.toInt)
        catch { case _: NumberFormatException => None }
    }
    implicit val patchRangeFieldEncoder: JsonFieldEncoder[PatchRange] =
      JsonFieldEncoder.string.contramap { case PatchRange(f, l) => s"$f..$l" }
    implicit val patchRangeFieldDecoder: JsonFieldDecoder[PatchRange] = {
      val RangeRegex = """(\d+)\.\.(\d+)""".r
      JsonFieldDecoder.string.mapOrFail {
        case RangeRegex(AsInt(first), AsInt(last)) => Right(PatchRange(first, last))
        case other                                 => Left(s"Invalid patch range: $other")
      }
    }

    implicit val codec: JsonCodec[VersionConfig] = DeriveJsonCodec.gen[VersionConfig]
  }

  case class VersionFile(epochs: SortedMap[Int, VersionFile.EpochVersionConfig]) {
    def addVersions(versions: Map[Int, Map[Int, Set[Int]]]): VersionFile = {
      val (toMerge, toAdd) = versions.partition { case (epoch, _) => epochs.contains(epoch) }
      VersionFile(
        epochs ++
          toMerge.transform { case (epoch, majors) => epochs(epoch).addVersions(majors) } ++
          toAdd.transform { case (epoch, majors) =>
            VersionFile.EpochVersionConfig(SortedMap())
              .addVersions(
                majors,
                epochs.until(epoch).values.lastOption
                  .flatMap(_.majors.values.lastOption.flatMap(_.ranges.values.lastOption))
                  .orElse(
                    epochs.from(epoch).values.headOption
                      .flatMap(_.majors.values.headOption.flatMap(_.ranges.values.headOption))
                  )
                  .getOrElse(VersionConfig(Nil))
              )
          }
      )
    }
  }
  object VersionFile                                                             {
    case class EpochVersionConfig(majors: SortedMap[Int, EpochVersionConfig.MajorVersionConfig]) {
      def addVersions(versions: Map[Int, Set[Int]], defaultVersionConfig: VersionConfig = VersionConfig(Nil))
        : EpochVersionConfig = {
        val (toMerge, toAdd) = versions.partition { case (major, _) => majors.contains(major) }

        EpochVersionConfig(
          majors ++
            toMerge.transform { case (major, patches) => majors(major).addVersions(patches) } ++
            toAdd.transform { case (major, patches) =>
              EpochVersionConfig.MajorVersionConfig(SortedMap())
                .addVersions(
                  patches,
                  majors.until(major).values.lastOption.flatMap(_.ranges.values.lastOption)
                    .orElse(majors.from(major).values.headOption.flatMap(_.ranges.values.headOption))
                    .getOrElse(defaultVersionConfig)
                )
            }
        )
      }
    }
    object EpochVersionConfig                                                                    {
      case class MajorVersionConfig(ranges: SortedMap[PatchRange, VersionConfig]) {
        def addVersions(
          versions: Set[Int],
          defaultVersionConfig: VersionConfig = VersionConfig(Nil)): MajorVersionConfig = {
          val expanded = ranges.flatMap { case (range, config) => range.values.map(_ -> config) }
          val missing  = versions -- expanded.keySet
          val filled   =
            expanded ++
              missing.map { patch =>
                patch ->
                  expanded.until(patch).values.lastOption
                    .orElse(expanded.from(patch).values.headOption)
                    .getOrElse(defaultVersionConfig)
              }
          MajorVersionConfig(
            SortedMap(
              filled.foldLeft(Vector.empty[(PatchRange, VersionConfig)]) {
                case (xs :+ ((PatchRange(first, last), vc1)), (patch, vc2)) if (last + 1, vc1) == (patch, vc2) =>
                  xs :+ PatchRange(first, patch) -> vc2
                case (xs, (patch, vc))                                                                         =>
                  xs :+ (PatchRange(patch, patch) -> vc)
              }*
            )
          )
        }
      }
      object MajorVersionConfig                                                   {
        implicit val majorVersionConfigEncoder: JsonEncoder[MajorVersionConfig] =
          JsonEncoder[SortedMap[PatchRange, VersionConfig]].contramap(_.ranges)
        implicit val majorVersionConfigDecoder: JsonDecoder[MajorVersionConfig] =
          JsonDecoder[SortedMap[PatchRange, VersionConfig]].map(MajorVersionConfig(_))
      }

      implicit val epochVersionConfigEncoder: JsonEncoder[EpochVersionConfig] =
        JsonEncoder[SortedMap[Int, EpochVersionConfig.MajorVersionConfig]].contramap(_.majors)
      implicit val epochVersionConfigDecoder: JsonDecoder[EpochVersionConfig] =
        JsonDecoder[SortedMap[Int, EpochVersionConfig.MajorVersionConfig]].map(EpochVersionConfig(_))
    }

    implicit val versionFileEncoder: JsonEncoder[VersionFile] =
      JsonEncoder[SortedMap[Int, EpochVersionConfig]].contramap(_.epochs)
    implicit val versionFileDecoder: JsonDecoder[VersionFile] =
      JsonDecoder[SortedMap[Int, EpochVersionConfig]].map(VersionFile(_))
  }

  implicit def sortedMapEncoder[K : JsonFieldEncoder, V : JsonEncoder]: JsonEncoder[SortedMap[K, V]]            =
    JsonEncoder[Map[K, V]].contramap(identity)
  implicit def sortedMapDecoder[K : JsonFieldDecoder : Ordering, V : JsonDecoder]: JsonDecoder[SortedMap[K, V]] =
    JsonDecoder[Map[K, V]].map(m => SortedMap.empty[K, V] ++ m)

  def parseFile(content: String): VersionFile =
    content.fromYaml[VersionFile] match {
      case Right(vf) => vf
      case Left(err) => sys.error(s"Failed to parse versions.yaml: $err")
    }

  def loadVersions() = {
    val data = parseFile(IO.read(file("versions.yaml")))
    for ((epoch, data) <- data.epochs.toSeq)
      yield Epoch(
        epoch = epoch,
        majors =
          for ((major, data) <- data.majors.toSeq)
            yield Major(
              epoch = epoch,
              major = major,
              minors =
                for {
                  (range, config) <- data.ranges.toSeq
                  minor           <- range.values
                } yield Minor(
                  epoch = epoch,
                  major = major,
                  minor = minor,
                  prerelease = None,
                  helpFlags = config.helpFlags,
                  settings = config.settings
                )
            )
      )
  }
}
