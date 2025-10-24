package io.github.nafg.scalacoptions

object VersionOrdering {
  private def parseVersion(v: String): Seq[Either[Int, String]] = {
    val cleaned = v.stripPrefix("v")
    // Split by . and - to handle versions like "2.13.0" and "3.0.0-RC1"
    cleaned.split("[.\\-]").toSeq.map { part =>
      part.toIntOption match {
        case Some(n) => Left(n)
        case None    => Right(part)
      }
    }
  }

  implicit val ordering: Ordering[String] = new Ordering[String] {
    def compare(v1: String, v2: String): Int = {
      val parts1 = parseVersion(v1)
      val parts2 = parseVersion(v2)
      
      val minLen = Math.min(parts1.length, parts2.length)
      
      val cmp = (0 until minLen).view.map { i =>
        (parts1(i), parts2(i)) match {
          case (Left(n1), Left(n2))   => n1.compare(n2)
          case (Left(_), Right(_))    => -1  // Numbers come before strings
          case (Right(_), Left(_))    => 1
          case (Right(s1), Right(s2)) => s1.compare(s2)
        }
      }.find(_ != 0).getOrElse(0)
      
      if (cmp != 0) cmp
      else parts1.length.compare(parts2.length)
    }
  }
}
