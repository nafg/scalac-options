# scalac-options
DSL for scalacOptions

This library contains code-generated traits for all scalac options, from 2.11.0 to 3.0.0-RC2. They are generated by running the compiler and parsing its output.

There are traits and objects for concrete versions, as well as traits for version ranges: entire epochs (`V2` and `V3`), entire major versions (e.g. `V2_13`), and even minimum minor versions (e.g. `V2_13_2_+`).

They consist of `List[String]`-returning methods for each supported option, with the appropriate name and parameters.

There is a simple DSL for `-Wconf` strings.

There is also a helper method to supply different options depending on the version.
`ScalacOptions.all` takes a version string, and any number of `VersionOptionsFunction`s,
for which you can simply supply a function from a version trait to a `List[String]`. See the example below.

This library can be used in SBT, Mill, or anywhere else. It does not depend on anything from either.


## Example usage

### `project/plugins.sbt`
```scala
libraryDependencies += "io.github.nafg.scalac-options" %% "scalac-options" % "0.1.3"
```
### `build.sbt`
```scala
// In build.sbt, _root_ is necessary because `sbt.io` is imported into the namespace already
import _root_.io.github.nafg.scalacoptions._

ThisBuild / scalacOptions ++=
  // Use all of the options we supply that are applicable to the current version
  ScalacOptions.all(scalaVersion.value)(
    // These are available in all scala versions (hence "Common")
    (opts: options.Common) => opts.deprecation ++ opts.unchecked ++ opts.feature,
    // For 2.13.x, pass -Xlint:_
    (_: options.V2_13).Xlint("_"),
    // For 2.13.4+, pass -YtastyReader
    (_: options.V2_13_4_+).YtastyReader,
    // Where applicable, silence warnings in the lint-byname-implicit category
    // This will add -Wconf:cat=lint-byname-implicit:silent on 2.12.13 and 2.13.2+
    WarningsConfig(WarningsConfig.Filter.Category(WarningsConfig.Category.`lint-byname-implicit`).silent)
  )
```
