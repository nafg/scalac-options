package io.github.nafg.scalacoptions

import coursier.core.Version

object VersionOrdering {
  implicit val ordering: Ordering[String] = Ordering.by(new Version(_))
}
