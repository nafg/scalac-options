import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object VersionUpdaterTest {

  def runTests(): Unit = {
    println("Running VersionUpdater tests...")

    testVersionParsing()
    testVersionOrdering()
    testGroupIntoPatchRanges()
    testMergeVersionRanges()
    testYamlGeneration()

    println("\nAll tests passed!")
  }

  def testVersionParsing(): Unit = {
    println("\n[TEST] Version parsing...")

    val tests = Seq(
      ("2.13.12", Some(VersionUpdater.ScalaVersion(2, 13, 12, None))),
      ("3.3.0", Some(VersionUpdater.ScalaVersion(3, 3, 0, None))),
      ("2.13.0-RC1", Some(VersionUpdater.ScalaVersion(2, 13, 0, Some(1)))),
      ("invalid", None),
      ("2.13", None)
    )

    tests.foreach { case (input, expected) =>
      val result = VersionUpdater.ScalaVersion.parse(input)
      assert(result == expected, s"Failed: parse('$input') = $result, expected $expected")
      println(s"  ✓ parse('$input') = $result")
    }
  }

  def testVersionOrdering(): Unit = {
    println("\n[TEST] Version ordering...")

    val versions = Seq(
      VersionUpdater.ScalaVersion(2, 13, 0, None),
      VersionUpdater.ScalaVersion(2, 13, 1, None),
      VersionUpdater.ScalaVersion(2, 13, 0, Some(1)),
      VersionUpdater.ScalaVersion(3, 0, 0, None),
      VersionUpdater.ScalaVersion(2, 12, 18, None)
    )

    val sorted = versions.sorted
    assert(sorted(0) == VersionUpdater.ScalaVersion(2, 12, 18, None))
    assert(sorted(1) == VersionUpdater.ScalaVersion(2, 13, 0, Some(1)))
    assert(sorted(2) == VersionUpdater.ScalaVersion(2, 13, 0, None))
    assert(sorted(3) == VersionUpdater.ScalaVersion(2, 13, 1, None))
    assert(sorted(4) == VersionUpdater.ScalaVersion(3, 0, 0, None))

    println(s"  ✓ Sorted versions: ${sorted.map(_.versionString).mkString(", ")}")
  }

  def testGroupIntoPatchRanges(): Unit = {
    println("\n[TEST] Grouping into patch ranges...")

    val versions = Seq(
      VersionUpdater.ScalaVersion(2, 13, 0, None),
      VersionUpdater.ScalaVersion(2, 13, 1, None),
      VersionUpdater.ScalaVersion(2, 13, 2, None),
      VersionUpdater.ScalaVersion(2, 13, 5, None),
      VersionUpdater.ScalaVersion(2, 13, 6, None),
      VersionUpdater.ScalaVersion(3, 3, 0, None),
      VersionUpdater.ScalaVersion(3, 3, 1, None)
    )

    val ranges = VersionUpdater.groupIntoPatchRanges(versions)

    val scala213Ranges = ranges((2, 13))
    assert(scala213Ranges.size == 2, s"Expected 2 ranges for 2.13, got ${scala213Ranges.size}")
    assert(scala213Ranges.exists(r => r.start == 0 && r.end == 2),
           s"Expected range 0..2 in $scala213Ranges")
    assert(scala213Ranges.exists(r => r.start == 5 && r.end == 6),
           s"Expected range 5..6 in $scala213Ranges")

    val scala33Ranges = ranges((3, 3))
    assert(scala33Ranges.size == 1, s"Expected 1 range for 3.3, got ${scala33Ranges.size}")
    assert(scala33Ranges.head.start == 0 && scala33Ranges.head.end == 1)

    println(s"  ✓ 2.13: ${scala213Ranges.mkString(", ")}")
    println(s"  ✓ 3.3: ${scala33Ranges.mkString(", ")}")
  }

  def testMergeVersionRanges(): Unit = {
    println("\n[TEST] Merging version ranges...")

    val current = Map(
      (2, 13) -> Seq(VersionUpdater.VersionRange(0, 10))
    )

    val available = Map(
      (2, 13) -> Seq(VersionUpdater.VersionRange(0, 12)),
      (3, 3) -> Seq(VersionUpdater.VersionRange(0, 5))
    )

    val merged = VersionUpdater.mergeVersionRanges(current, available)

    assert(merged.contains((2, 13)))
    assert(merged.contains((3, 3)))

    val scala213 = merged((2, 13))
    assert(scala213.head.end == 12, s"Expected 2.13 to extend to 12, got ${scala213.head}")

    val scala33 = merged((3, 3))
    assert(scala33 == available((3, 3)), s"Expected 3.3 ranges to be added")

    println(s"  ✓ 2.13 extended: ${scala213.mkString(", ")}")
    println(s"  ✓ 3.3 added: ${scala33.mkString(", ")}")
  }

  def testYamlGeneration(): Unit = {
    println("\n[TEST] YAML generation...")

    val ranges = Map(
      (2, 13) -> Seq(VersionUpdater.VersionRange(0, 5)),
      (3, 3) -> Seq(VersionUpdater.VersionRange(0, 2))
    )

    val yaml = VersionUpdater.generateYamlContent(ranges)

    assert(yaml.contains("2:"), "Missing epoch 2")
    assert(yaml.contains("3:"), "Missing epoch 3")
    assert(yaml.contains("  13:"), "Missing major 13")
    assert(yaml.contains("  3:"), "Missing major 3")
    assert(yaml.contains("    0..5:"), "Missing range 0..5")
    assert(yaml.contains("    0..2:"), "Missing range 0..2")
    assert(yaml.contains("helpFlags:"), "Missing helpFlags")
    assert(yaml.contains("-W"), "Missing -W flag for 2.13")

    println("  ✓ Generated YAML contains expected structure")
    println("\n  Sample output:")
    yaml.split("\n").take(10).foreach(line => println(s"    $line"))
  }

  def testDryRun(): Unit = {
    println("\n[TEST] Dry run mode (fetching live data)...")

    val result = Await.result(VersionUpdater.dryRun(), 30.seconds)

    println(s"  Available versions: ${result.availableVersions.size}")
    println(s"  Current ranges: ${result.currentRanges.size}")
    println(s"  Merged ranges: ${result.mergedRanges.size}")
    println(s"  Has changes: ${result.hasChanges}")

    if (result.hasChanges) {
      println("\n  Changes that would be made:")
      result.changes.foreach(println)
    }
  }

  private def assert(condition: Boolean, message: String = ""): Unit = {
    if (!condition) {
      throw new AssertionError(s"Assertion failed${if (message.nonEmpty) s": $message" else ""}")
    }
  }
}
