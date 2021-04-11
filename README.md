# scalac-options
DSL for scalacOptions

## Example usage in build.sbt

```scala
import _root_.io.github.nafg.scalacoptions._

ThisBuild / scalacOptions ++=
  ScalacOptions.all(scalaVersion.value)(
    (opts: options.Common) => opts.deprecation ++ opts.unchecked ++ opts.feature,
    (_: options.V2_13).Xlint("_"),
    WarningsConfig(WarningsConfig.Filter.Category(WarningsConfig.Category.`lint-byname-implicit`).silent)
  )
```
