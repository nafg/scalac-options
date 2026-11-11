import scala.collection.immutable.SortedMap
import scala.concurrent.{ExecutionContext, Future}
import scala.math.Ordering.Implicits.infixOrderingOps

import coursier.Versions as CoursierVersions
import coursier.core.{Module, ModuleName, Organization}
import coursier.version.Version
import org.yaml.snakeyaml.DumperOptions.FlowStyle
import sbt.io.IO
import sbt.io.syntax.file
import zio.json.ast.Json
import zio.json.yaml.*


class VersionUpdater(implicit ec: ExecutionContext) {
  private def getVersions(module: Module) = CoursierVersions().withModule(module).result().future()

  private def fetchPublishedPatches: Future[SortedMap[Int, Map[Int, Set[Int]]]] =
    Future
      .traverse(Seq("scala-compiler", "scala3-compiler_3")) { name =>
        getVersions(Module(Organization("org.scala-lang"), ModuleName(name), Map.empty))
      }
      .map { results =>
        val releaseVersions =
          results
            .flatMap(_.versions.available0.map(_.items))
            .collect { case Vector(x: Version.Number, y: Version.Number, z: Version.Number) =>
              (x.value, y.value, z.value)
            }
            .filter(_ >= (2, 11, 0))
        SortedMap.from(
          releaseVersions.groupBy(_._1).map { case (epoch, byEpoch) =>
            epoch -> SortedMap.from(byEpoch.groupMapReduce(_._2)(v => Set(v._3))(_ ++ _))
          }
        )
      }

  private def render(vf: Versions.VersionFile): String =
    vf.toYaml(
      YamlOptions.default.copy(
        flowStyle = {
          case Json.Arr(elems) if elems.forall(_.asString.isDefined) => FlowStyle.FLOW
          case _                                                     => FlowStyle.BLOCK
        }
      )
    )
      .fold(t => sys.error(s"YAML render failed: $t"), s => s)

  def run(dryRun: Boolean): Future[String] = {
    val yamlPath = file("versions.yaml")
    fetchPublishedPatches.map { published =>
      val parsed  =
        if (yamlPath.exists())
          Versions.parseFile(IO.read(yamlPath))
        else
          Versions.VersionFile(SortedMap())
      val updated = parsed.addVersions(published)

      if (updated == parsed)
        "versions.yaml is up to date"
      else if (dryRun)
        "[DRY RUN] versions.yaml would be modified"
      else {
        IO.write(yamlPath, render(updated))
        "Updated versions.yaml"
      }
    }
  }
}
