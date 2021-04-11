# scalac-options
DSL for scalacOptions

This library contains code-generated traits for all scalac options, from 2.11.0 to 3.0.0-RC2.

There are traits and objects for concrete versions, as well as traits for version ranges, namely entire epochs (`V2` and `V3`), entire major versions (e.g. `V2_13`), and even minimum minor versions (e.g. `V2_13_2_+`).

They consist of `List[String]`-returning methods for each supported option, with the appropriate name and parameters.

There is a simple DSL for `-Wconf` strings.

There is also a helper method to supply different options depending on the version.
`ScalacOptions.all` takes a version string, and any number of `VersionOptionsFunction`s,
for which you can simply supply a function from a version trait to a `List[String]`. See the example below.


## Example usage

### `project/plugins.sbt`
```scala
libraryDependencies += "io.github.nafg.scalac-options" %% "scalac-options" % "0.1.3"
```
### `build.sbt`
```scala
import _root_.io.github.nafg.scalacoptions._

ThisBuild / scalacOptions ++=
  ScalacOptions.all(scalaVersion.value)(
    (opts: options.Common) => opts.deprecation ++ opts.unchecked ++ opts.feature,
    (_: options.V2_13).Xlint("_"),
    (_: options.V2_13_4_+).YtastyReader,
    WarningsConfig(WarningsConfig.Filter.Category(WarningsConfig.Category.`lint-byname-implicit`).silent)
  )
```
