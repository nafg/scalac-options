package io.github.nafg.scalacoptions

import scala.concurrent.duration.DurationInt

import io.github.nafg.scalacoptions.WarningsConfig.{Category, Filter}
import io.github.nafg.scalacoptions.launcher.Scalac

import munit.{Clue, Location}


class OptionsAcceptanceSpec extends munit.FunSuite {
  override val munitTimeout = 30.minutes

  private val rejectionMarkers = List(
    "invalid choice",
    "bad option",
    "unrecognized option",
    "missing argument"
  )

  private def clue[A](name: String, value: A) = new Clue(name, value, "")

  private def assertAccepted(makeFlags: String => List[String])(implicit location: Location): Unit =
    ScalacOptions.versionMap.keys.foreach { version =>
      val flags = makeFlags(version)
      if (flags.nonEmpty) {
        var rejected: Option[(String, String)] = None
        Scalac.run(
          version,
          os.ProcessOutput.Readlines { line =>
            if (rejected.isEmpty)
              rejectionMarkers
                .find(line.toLowerCase.contains)
                .foreach(marker => rejected = Some((line, marker)))
          },
          flags: _*
        )
        rejected.foreach { case (line, markers) =>
          fail("Invalid flag", clues(clue("version", version), clue("marker", markers), clue("line", line)))
        }
      }
    }

  private def acceptanceTest(name: String)(fs: VersionOptionsFunction*)(implicit location: Location): Unit =
    test(s"$name produces valid flags") {
      assertAccepted(version => ScalacOptions.all(version)(fs: _*))
    }

  acceptanceTest("Common.deprecation ++ feature")(
    (opts: options.Common) => opts.deprecation ++ opts.feature
  )
  acceptanceTest("V2.unchecked")((_: options.V2).unchecked)
  acceptanceTest("V3_0.unchecked")((_: options.V3_0).unchecked)
  acceptanceTest("V3_1.unchecked")((_: options.V3_1).unchecked)
  acceptanceTest("V3_2.unchecked")((_: options.V3_2).unchecked)
  acceptanceTest("V3_3.unchecked")((_: options.V3_3).unchecked)
  acceptanceTest("V3_4.unchecked")((_: options.V3_4).unchecked)
  acceptanceTest("V3_5_0.unchecked")((_: options.V3_5_0).unchecked)
  acceptanceTest("V2_13.Xlint(\"_\")")((_: options.V2_13).Xlint("_"))
  acceptanceTest("V3_3.Wunused(\"all\")")((opts: options.V3_3) => opts.Wunused("all"))
  acceptanceTest("V3_4_2_+.Wshadow(\"all\")")((opts: options.V3_4_2_+) => opts.Wshadow("all"))
  acceptanceTest("WarningsConfig lint-byname-implicit silent")(
    WarningsConfig(Filter.Category(Category.`lint-byname-implicit`).silent)
  )
}
