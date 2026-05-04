libraryDependencies += "com.lihaoyi"                   %% "fastparse"      % "3.1.1"
libraryDependencies += "org.scalameta"                 %% "scalameta"      % "4.14.7"
libraryDependencies += "com.lihaoyi"                   %% "os-lib"         % "0.11.8"
libraryDependencies += "com.lihaoyi"                   %% "pprint"         % "0.9.6"
libraryDependencies += "io.get-coursier"               %% "coursier"       % "2.1.24"
libraryDependencies += "dev.zio"                       %% "zio-json-yaml"  % "0.9.2"
libraryDependencies += "io.github.nafg.scalac-options" %% "scalac-options" % "0.4.0"

scalacOptions += "-deprecation"

// Compile the runner subproject's sources (io.github.nafg.scalacoptions.runner.Scalac) into the
// meta-build classpath so build.sbt and project/*.scala can call Scalac.run directly. The runner
// subproject also compiles them on its own for the library's tests; this is intentional source
// sharing — the alternative would be re-implementing the same Coursier+os.proc fork in project/.
unmanagedSourceDirectories in Compile +=
  baseDirectory.value / ".." / "runner" / "src" / "main" / "scala"
