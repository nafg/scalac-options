package io.github.nafg.scalacoptions.generator

import sjsonnew.BasicJsonProtocol._
import sjsonnew.{:*:, IsoLList, LList, LNil}


object Versions {
  val commonHelpFlags = Seq("-help", "-X", "-Y")

  case class Minor(epoch: Int,
                   major: Int,
                   minor: Int,
                   prerelease: Option[(String, Int)],
                   helpFlags: Seq[String]) {
    lazy val prereleaseString = prerelease.map { case (let, num) => let + num }
    lazy val versionString = s"$epoch.$major.$minor" + prereleaseString.fold("")("-" + _)

    override def toString = versionString
  }

  object Minor {
    implicit val minorLListIso: IsoLList.Aux[Minor, (Int, Int, Int) :*: Option[(String, Int)] :*: Seq[String] :*: LNil] =
      LList.iso(
        { case Minor(epoch, major, minor, prerelease, helpFlags) =>
          "version" -> (epoch, major, minor) :*:
            "prerelease" -> prerelease :*:
            "helpFlags" -> helpFlags :*:
            LNil
        },
        { case (_, (epoch, major, minor)) :*: (_, prerelease) :*: (_, helpFlags) :*: LNil =>
          Minor(epoch, major, minor, prerelease, helpFlags)
        }
      )

    implicit val ordering: Ordering[Minor] =
      Ordering.by { case Minor(epoch, major, minor, prerelease, _) =>
        (epoch, major, minor, prerelease.getOrElse("" -> 0))
      }
  }

  case class Major(epoch: Int, major: Int, minors: Seq[Minor])

  case class Epoch(epoch: Int, majors: Seq[Major]) {
    def allMinors = majors.flatMap(_.minors)
  }

  val versions =
    List(
      Epoch(
        epoch = 2,
        majors = List(
          Major(2, 11, (0 to 12).map(Minor(2, 11, _, None, commonHelpFlags))),
          Major(2, 12, (0 to 14).map(Minor(2, 12, _, None, commonHelpFlags))),
          Major(2, 13, (0 to 6).map(Minor(2, 13, _, None, commonHelpFlags :+ "-V" :+ "-W")))
        )
      ),
      Epoch(
        epoch = 3,
        majors = List(
          Major(
            3,
            0,
            (1 to 3).map(rc => Minor(3, 0, 0, Some("RC" -> rc), commonHelpFlags)) ++
              (0 to 2).map(Minor(3, 0, _, None, commonHelpFlags))
          ),
          Major(3, 1, Seq(Minor(3, 1, 0, None, commonHelpFlags :+ "-W")))
        )
      )
    )
}
