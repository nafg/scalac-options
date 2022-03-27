import io.circe.yaml.parser
import sbt.io.IO
import sbt.io.syntax.file
import sjsonnew.BasicJsonProtocol._
import sjsonnew.{:*:, IsoLList, LList, LNil}


object Versions {
  case class Minor(epoch: Int, major: Int, minor: Int, prerelease: Option[(String, Int)], helpFlags: Seq[String]) {
    lazy val prereleaseString = prerelease.map { case (let, num) => let + num }
    lazy val versionString =
      s"$epoch.$major.$minor" + prereleaseString.fold("")("-" + _)

    override def toString = versionString
  }

  object Minor {
    implicit val minorLListIso: IsoLList.Aux[
      Minor,
      (Int, Int, Int) :*: Option[(String, Int)] :*: Seq[String] :*: LNil
    ] =
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

  val versions = {
    val json =
      parser.parse(IO.read(file("versions.yaml")))
        .toTry.get
    val regex = """(\d+)\.\.(\d+)""".r
    for ((epoch, json) <- json.asObject.get.toList.sortBy(_._1.toInt)) yield Epoch(
      epoch = epoch.toInt,
      majors =
        for ((major, json) <- json.asObject.get.toList.sortBy(_._1.toInt))
          yield Major(
            epoch = epoch.toInt,
            major = major.toInt,
            minors =
              for {
                (regex(minorFirst, minorLast), json) <- json.asObject.get.toList.sortBy(_._1.toInt)
                minor <- minorFirst.toInt to minorLast.toInt
              } yield Minor(
                epoch = epoch.toInt,
                major = major.toInt,
                minor = minor,
                prerelease = None,
                helpFlags = json.asObject.get("helpFlags").get.as[Seq[String]].toTry.get
              )
          )
    )
  }
}
