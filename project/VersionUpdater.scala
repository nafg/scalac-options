import coursier.core.{Module, ModuleName, Organization}
import coursier.{Dependency, Versions}
import io.circe.yaml.parser
import io.circe.{Decoder, Json}
import sbt.io.IO
import sbt.io.syntax.file

import scala.collection.immutable.SortedMap
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.matching.Regex

object VersionUpdater {

  case class ScalaVersion(epoch: Int, major: Int, minor: Int, rc: Option[Int] = None) extends Ordered[ScalaVersion] {
    def versionString: String = rc match {
      case Some(rcNum) => s"$epoch.$major.$minor-RC$rcNum"
      case None => s"$epoch.$major.$minor"
    }

    def majorMinor: (Int, Int) = (epoch, major)

    override def compare(that: ScalaVersion): Int = {
      val cmp1 = this.epoch.compare(that.epoch)
      if (cmp1 != 0) return cmp1
      val cmp2 = this.major.compare(that.major)
      if (cmp2 != 0) return cmp2
      val cmp3 = this.minor.compare(that.minor)
      if (cmp3 != 0) return cmp3
      (this.rc, that.rc) match {
        case (None, None) => 0
        case (None, Some(_)) => 1  // Release versions come after RCs
        case (Some(_), None) => -1
        case (Some(a), Some(b)) => a.compare(b)
      }
    }
  }

  object ScalaVersion {
    private val versionRegex: Regex = """(\d+)\.(\d+)\.(\d+)(?:-RC(\d+))?""".r

    def parse(versionStr: String): Option[ScalaVersion] = versionStr match {
      case versionRegex(epoch, major, minor, null) =>
        Some(ScalaVersion(epoch.toInt, major.toInt, minor.toInt, None))
      case versionRegex(epoch, major, minor, rc) =>
        Some(ScalaVersion(epoch.toInt, major.toInt, minor.toInt, Some(rc.toInt)))
      case _ => None
    }
  }

  def fetchAvailableVersions(implicit ec: ExecutionContext): Future[Seq[ScalaVersion]] = {
    val scala2Compiler = Module(Organization("org.scala-lang"), ModuleName("scala-compiler"), Map.empty)
    val scala3Compiler = Module(Organization("org.scala-lang"), ModuleName("scala3-compiler_3"), Map.empty)

    for {
      scala2VersionsResult <- Versions()
        .withModule(scala2Compiler)
        .result()
        .future()
      scala3VersionsResult <- Versions()
        .withModule(scala3Compiler)
        .result()
        .future()
    } yield {
      val scala2Versions = scala2VersionsResult.versions.available
      val scala3Versions = scala3VersionsResult.versions.available
      val allVersionStrings = (scala2Versions ++ scala3Versions).distinct
      allVersionStrings.flatMap(ScalaVersion.parse).sorted
    }
  }

  case class VersionRange(start: Int, end: Int) {
    def contains(patch: Int): Boolean = patch >= start && patch <= end
    def extend(patch: Int): VersionRange =
      VersionRange(Math.min(start, patch), Math.max(end, patch))
    override def toString: String = s"$start..$end"
  }

  def groupIntoPatchRanges(versions: Seq[ScalaVersion]): Map[(Int, Int), Seq[VersionRange]] = {
    versions
      .filter(_.rc.isEmpty)  // Only include release versions in ranges
      .groupBy(v => (v.epoch, v.major))
      .map { case (key, vers) =>
        // Group consecutive patch versions into ranges
        val patches = vers.map(_.minor).sorted.distinct
        val ranges = patches.foldLeft(List.empty[VersionRange]) {
          case (Nil, patch) => List(VersionRange(patch, patch))
          case (current :: rest, patch) =>
            if (patch == current.end + 1) {
              // Extend current range
              current.extend(patch) :: rest
            } else {
              // Start new range
              VersionRange(patch, patch) :: current :: rest
            }
        }.reverse
        key -> ranges
      }
  }

  def defaultHelpFlags(epoch: Int, major: Int): Seq[String] = {
    (epoch, major) match {
      case (2, 11) => Seq("-help", "-X", "-Y")
      case (2, 12) => Seq("-help", "-X", "-Y")
      case (2, 13) => Seq("-help", "-V", "-W", "-X", "-Y")
      case (3, _)  => Seq("-help", "-W", "-X", "-Y")
      case _       => Seq("-help", "-X", "-Y")
    }
  }

  def defaultSettings(epoch: Int, major: Int): Map[String, Seq[Map[String, String]]] = {
    val languageSetting = Map(
      "language" -> Seq(
        Map("lit" -> "-language:"),
        Map("param" -> "features")
      )
    )

    (epoch, major) match {
      case (2, _) if major <= 12 => languageSetting
      case (3, _) => Map(
        "encoding" -> Seq(
          Map("lit" -> "-encoding "),
          Map("param" -> "encoding")
        )
      ) ++ languageSetting
      case _ => languageSetting
    }
  }

  def readCurrentVersions(): Map[(Int, Int), Seq[VersionRange]] = {
    val yamlContent = IO.read(file("versions.yaml"))
    val parsed = parser.parse(yamlContent).toTry.get

    val rangeRegex = """(\d+)\.\.(\d+)""".r

    val result = scala.collection.mutable.Map[(Int, Int), List[VersionRange]]()

    parsed.asObject.foreach { root =>
      root.toMap.foreach { case (epochKey, epochValue) =>
        val epoch = epochKey.toInt
        epochValue.asObject.foreach { majorObj =>
          majorObj.toMap.foreach { case (majorKey, minorObj) =>
            val major = majorKey.toInt
            minorObj.asObject.foreach { patchObj =>
              patchObj.toMap.foreach { case (patchKey, _) =>
                patchKey match {
                  case rangeRegex(start, end) =>
                    val key = (epoch, major)
                    val range = VersionRange(start.toInt, end.toInt)
                    result(key) = range :: result.getOrElse(key, Nil)
                  case _ => // Ignore invalid formats
                }
              }
            }
          }
        }
      }
    }

    result.map { case (k, v) => k -> v.sorted(Ordering.by[(VersionRange, Int), (Int, Int)](r => (r.start, r.end))).reverse }.toMap
  }

  def mergeVersionRanges(
    current: Map[(Int, Int), Seq[VersionRange]],
    available: Map[(Int, Int), Seq[VersionRange]]
  ): Map[(Int, Int), Seq[VersionRange]] = {
    val allKeys = (current.keys ++ available.keys).toSet
    allKeys.map { key =>
      val currentRanges = current.getOrElse(key, Seq.empty)
      val availableRanges = available.getOrElse(key, Seq.empty)

      // Find the maximum patch version we need to cover
      val maxPatch = availableRanges.map(_.end).maxOption.getOrElse(0)

      // Extend existing ranges or add new ones
      val merged = if (currentRanges.isEmpty) {
        availableRanges
      } else {
        // Extend the last (highest) range if possible
        val lastRange = currentRanges.head
        if (maxPatch > lastRange.end) {
          // Check if we need to add a new range or extend the existing one
          if (maxPatch == lastRange.end + 1 || availableRanges.exists(r => r.start <= lastRange.end + 1)) {
            // Extend the range
            lastRange.copy(end = maxPatch) +: currentRanges.tail
          } else {
            // Add a new range
            VersionRange(lastRange.end + 1, maxPatch) +: currentRanges
          }
        } else {
          currentRanges
        }
      }

      key -> merged
    }.toMap
  }

  def generateYamlContent(ranges: Map[(Int, Int), Seq[VersionRange]]): String = {
    val grouped = ranges.groupBy(_._1._1).toSeq.sortBy(_._1)

    val lines = scala.collection.mutable.ArrayBuffer[String]()

    grouped.foreach { case (epoch, majorRanges) =>
      lines += s"$epoch:"

      val sortedMajors = majorRanges.toSeq.sortBy(_._1._2)
      sortedMajors.foreach { case ((_, major), ranges) =>
        lines += s"  $major:"

        ranges.foreach { range =>
          lines += s"    $range:"
          lines += s"      helpFlags: [ ${defaultHelpFlags(epoch, major).mkString(", ")} ]"

          val settings = defaultSettings(epoch, major)
          if (settings.nonEmpty) {
            lines += "      settings:"
            settings.foreach { case (name, flagSegments) =>
              lines += s"        $name:"
              flagSegments.foreach { segment =>
                segment.foreach { case (segType, value) =>
                  lines += s"          - $segType: '$value'"
                }
              }
            }
          }
        }
      }
    }

    lines.mkString("\n") + "\n"
  }

  case class UpdateResult(
    availableVersions: Seq[ScalaVersion],
    currentRanges: Map[(Int, Int), Seq[VersionRange]],
    availableRanges: Map[(Int, Int), Seq[VersionRange]],
    mergedRanges: Map[(Int, Int), Seq[VersionRange]],
    hasChanges: Boolean,
    changes: Seq[String]
  )

  def dryRun()(implicit ec: ExecutionContext): Future[UpdateResult] = {
    for {
      available <- fetchAvailableVersions
    } yield {
      val currentRanges = readCurrentVersions()
      val availableRanges = groupIntoPatchRanges(available)
      val merged = mergeVersionRanges(currentRanges, availableRanges)
      val hasChanges = merged != currentRanges

      val changes = scala.collection.mutable.ArrayBuffer[String]()

      if (hasChanges) {
        val newKeys = merged.keySet.diff(currentRanges.keySet)
        if (newKeys.nonEmpty) {
          changes += s"New major versions: ${newKeys.map { case (e, m) => s"$e.$m" }.mkString(", ")}"
        }

        merged.foreach { case (key @ (epoch, major), ranges) =>
          val oldRanges = currentRanges.getOrElse(key, Seq.empty)
          if (ranges != oldRanges) {
            changes += s"  $epoch.$major: ${ranges.mkString(", ")} (was: ${oldRanges.mkString(", ")})"
          }
        }
      }

      UpdateResult(
        availableVersions = available,
        currentRanges = currentRanges,
        availableRanges = availableRanges,
        mergedRanges = merged,
        hasChanges = hasChanges,
        changes = changes.toSeq
      )
    }
  }

  def updateVersionsFile(dryRunMode: Boolean = false)(implicit ec: ExecutionContext): Future[Unit] = {
    for {
      result <- dryRun()
    } yield {
      println(s"Found ${result.availableVersions.size} available Scala versions")
      println(s"Current version ranges: ${result.currentRanges.size} major versions")
      println(s"Available version ranges: ${result.availableRanges.size} major versions")

      if (result.hasChanges) {
        if (dryRunMode) {
          println("\n[DRY RUN] Would make the following changes:")
        } else {
          val yamlContent = generateYamlContent(result.mergedRanges)
          IO.write(file("versions.yaml"), yamlContent)
          println("\nUpdated versions.yaml with new Scala versions")
        }

        result.changes.foreach(println)
      } else {
        println("\nNo new versions found - versions.yaml is up to date")
      }
    }
  }
}
