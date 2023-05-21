import cats.data
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import io.circe.yaml.parser
import sbt.io.IO
import sbt.io.syntax.file
import sjsonnew.BasicJsonProtocol.*
import sjsonnew.{:*:, IsoLList, LList, LNil}
import scala.collection.immutable.SortedMap

import io.circe.generic.extras.{Configuration, semiauto}


object Versions {
  case class Minor(
    epoch: Int,
    major: Int,
    minor: Int,
    prerelease: Option[(String, Int)],
    helpFlags: Seq[String],
    settings: Map[String, Seq[FlagSegment]] = Map.empty) {
    lazy val prereleaseString = prerelease.map { case (let, num) => let + num }
    lazy val versionString =
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
          "version" -> (epoch, major, minor) :*:
            "prerelease" -> prerelease :*:
            "helpFlags" -> helpFlags :*:
            "settings" -> settings :*:
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

  case class VersionConfig(helpFlags: Seq[String], settings: Map[String, Seq[FlagSegment]] = Map.empty)
  object VersionConfig {
    private implicit val config = Configuration.default.withDefaults
    implicit val decoder: Decoder[VersionConfig] = semiauto.deriveConfiguredDecoder
  }
  type VersionFile = SortedMap[Int, SortedMap[Int, Map[String, VersionConfig]]]

  def versions = {
    val data =
      parser.parse(IO.read(file("versions.yaml")))
        .flatMap(Decoder[VersionFile].decodeJson(_))
        .toTry.get
    val regex = """(\d+)\.\.(\d+)""".r
    for ((epoch, data) <- data.toSeq)
      yield Epoch(
        epoch = epoch,
        majors =
          for ((major, data) <- data.toSeq)
            yield Major(
              epoch = epoch,
              major = major,
              minors =
                for {
                  case (regex(minorFirst, minorLast), config) <- data.toSeq
                  minor <- minorFirst.toInt to minorLast.toInt
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
