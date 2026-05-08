package io.github.nafg.scalacoptions

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Future, blocking}

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

  private def assertAccepted(makeArgs: String => List[String])(implicit location: Location) =
    Future.traverse(ScalacOptions.versionMap.keys.toList) { version =>
      val args = makeArgs(version)
      if (args.isEmpty)
        Future.unit
      else {
        Future {
          blocking {
            println(s"Testing scalac $version with: ${args.mkString(" ")}")
            var rejected: Option[(String, String)] = None
            Scalac.run(
              version,
              os.ProcessOutput.Readlines { line =>
                if (rejected.isEmpty)
                  rejectionMarkers
                    .find(line.toLowerCase.contains)
                    .foreach(marker => rejected = Some((line, marker)))
              },
              args: _*
            )
            rejected.foreach { case (line, marker) =>
              fail("Invalid flag", clues(clue("version", version), clue("marker", marker), clue("line", line)))
            }
          }
        }
      }
    }

  test("-deprecation, -feature") {
    assertAccepted { version =>
      ScalacOptions.all(version)((opts: options.Common) => opts.deprecation ++ opts.feature)
    }
  }

  test("-unchecked") {
    assertAccepted { version =>
      ScalacOptions.all(version)(
        (_: options.V2).unchecked,
        (_: options.V3_0).unchecked,
        (_: options.V3_1).unchecked,
        (_: options.V3_2).unchecked,
        (_: options.V3_3).unchecked,
        (_: options.V3_4).unchecked,
        (_: options.V3_5_0).unchecked
      )
    }
  }

  test("-Xlint") {
    assertAccepted { version =>
      ScalacOptions.all(version)((_: options.V2_13).Xlint("_"))
    }
  }

  test("-Wunused") {
    assertAccepted { version =>
      ScalacOptions.all(version)((opts: options.V3_3) => opts.Wunused("all"))
    }
  }

  test("-Wshadow") {
    assertAccepted { version =>
      ScalacOptions.all(version)((opts: options.V3_4_2_+) => opts.Wshadow("all"))
    }
  }

  test("-java-output-version") {
    assertAccepted { version =>
      ScalacOptions.all(version)(
        (opts: options.V3_1_2_+) => opts.javaOutputVersion("8"),
        (opts: options.V3_2) => opts.javaOutputVersion("8"),
        (opts: options.V3_3) => opts.javaOutputVersion("8"),
        (opts: options.V3_4) => opts.javaOutputVersion("8"),
        (opts: options.V3_5) => opts.javaOutputVersion("8"),
        (opts: options.V3_6) => opts.javaOutputVersion("8"),
        (opts: options.V3_7) => opts.javaOutputVersion("8"),
        (opts: options.V3_8) => opts.javaOutputVersion("8")
      )
    }
  }

  test("-Wconf") {
    assertAccepted { version =>
      ScalacOptions.all(version)(WarningsConfig(Filter.Category(Category.`lint-byname-implicit`).silent))
    }
  }
}
