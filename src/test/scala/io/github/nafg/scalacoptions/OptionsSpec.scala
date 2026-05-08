package io.github.nafg.scalacoptions

import scala.collection.immutable.SortedSet

import coursier.core.Version
import munit.FunSuite


class OptionsSpec extends FunSuite {
  private def matchingVersions(toFlags: String => List[String]): SortedSet[String] =
    SortedSet(ScalacOptions.versionMap.keys.filter(toFlags(_).nonEmpty).toSeq: _*)

  private def range(from: String, to: String): SortedSet[String] =
    SortedSet(ScalacOptions.sortedVersionMap.keys.toSeq: _*)
      .from(new Version(from))
      .until(new Version(to))
      .map(_.repr)

  test("V3_2_+ matches Scala 3.2 and later") {
    assertEquals(
      matchingVersions { v =>
        ScalacOptions.all(v)((opts: options.V3_2_+) => opts.javaOutputVersion("8"))
      },
      range("3.2.0", "4.0.0")
    )
  }

  test("V3_3 matches only Scala 3.3.x") {
    assertEquals(
      matchingVersions { v =>
        ScalacOptions.all(v)((opts: options.V3_3) => opts.javaOutputVersion("8"))
      },
      range("3.3.0", "3.4.0")
    )
  }

  test("V3_4_2_+ matches Scala 3.4.2 through end of 3.4.x") {
    assertEquals(
      matchingVersions { v =>
        ScalacOptions.all(v)((opts: options.V3_4_2_+) => opts.Wshadow("all"))
      },
      range("3.4.2", "3.5.0")
    )
  }

  test("javaOutputVersion produces -java-output-version:VALUE") {
    for (version <- Seq("3.2.0", "3.5.0", "3.8.3"))
      assertEquals(
        ScalacOptions.all(version)((opts: options.V3_2_+) => opts.javaOutputVersion("8")),
        List("-java-output-version:8")
      )
  }
}
