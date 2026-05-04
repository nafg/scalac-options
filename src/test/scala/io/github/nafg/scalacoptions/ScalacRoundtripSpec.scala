package io.github.nafg.scalacoptions

import io.github.nafg.scalacoptions.runner.Scalac

import java.lang.reflect.InvocationTargetException
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}


/** For each Scala 3 version, runs every generated nullary `Wunused*`/`Wshadow*` flag through
  * scalac at the matching version and asserts scalac doesn't report it as invalid. Scope is
  * intentionally narrow to the choice-bearing categories; pre-existing brokenness in other
  * arg-taking methods (`Wconf`, `bootclasspath`, ...) is out of scope.
  */
class ScalacRoundtripSpec extends munit.FunSuite {
  override val munitTimeout: Duration = 30.minutes

  // Latest of each Scala 3 minor where -Wunused/-Wshadow choices exist. 3.0 didn't have these
  // flags. 3.1/3.2 had pre-existing bare-Wunused brokenness now also fixed by this PR.
  private val testVersions = List(
    "3.1.3",
    "3.2.2",
    "3.3.7",
    "3.4.3",
    "3.5.2",
    "3.6.4",
    "3.7.4"
  )

  private val rejectionMarkers = List(
    "invalid choice",
    "bad option",
    "unrecognized option",
    "missing argument"
  )

  private def collectChoiceMethods(obj: AnyRef): List[(String, List[String])] = {
    val skip = Set("toString", "hashCode", "productPrefix", "productIterator", "getClass")
    obj.getClass.getMethods.toList.flatMap { m =>
      val name = m.getName
      val nameInScope = (name.startsWith("Wunused") || name.startsWith("Wshadow")) &&
        !skip.contains(name)
      if (nameInScope && m.getParameterCount == 0 &&
          classOf[scala.collection.immutable.List[_]].isAssignableFrom(m.getReturnType)) {
        try {
          val res = m.invoke(obj).asInstanceOf[List[String]]
          if (res.nonEmpty) Some(name -> res) else None
        } catch {
          case _: InvocationTargetException | _: IllegalAccessException => None
        }
      } else None
    }.distinct.sortBy(_._1)
  }

  testVersions.foreach { version =>
    test(s"scalac $version accepts each generated Wunused/Wshadow choice flag") {
      val obj = ScalacOptions.versionMap.getOrElse(
        version,
        fail(s"no entry for $version in ScalacOptions.versionMap")
      )
      val methods = collectChoiceMethods(obj)
      // Sanity guard: the parser fix should have produced choice methods on every Scala 3 version
      // that has -Wunused (i.e., everything we list). Zero would mean the parser silently regressed.
      assert(
        methods.nonEmpty,
        s"$version: expected at least one Wunused*/Wshadow* method on ${obj.getClass.getSimpleName}"
      )

      val rejected = methods.flatMap { case (name, flags) =>
        val res    = Scalac.run(version, flags: _*)
        val haystack = (res.stdout + "\n" + res.stderr).toLowerCase
        val markers  = rejectionMarkers.filter(haystack.contains)
        if (markers.nonEmpty) Some((name, flags, markers, res.stderr.take(400)))
        else None
      }
      assert(
        rejected.isEmpty,
        s"scalac $version rejected ${rejected.size} choice flag(s):\n" +
          rejected
            .map { case (name, flags, markers, stderr) =>
              s"  $name -> ${flags.mkString(" ")} matched $markers\n    stderr: $stderr"
            }
            .mkString("\n")
      )
    }
  }
}
