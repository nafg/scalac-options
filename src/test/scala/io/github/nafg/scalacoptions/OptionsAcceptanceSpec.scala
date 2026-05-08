package io.github.nafg.scalacoptions

import scala.concurrent.duration.DurationInt

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

  test("-Wunused produces valid flags") {
    assertAccepted { version =>
      ScalacOptions.all(version)((opts: options.V3_3) => opts.Wunused("all"))
    }
  }

  test("-Wshadow produces valid flags") {
    assertAccepted { version =>
      ScalacOptions.all(version)((opts: options.V3_4_2_+) => opts.Wshadow("all"))
    }
  }
}
