package io.github.nafg.scalacoptions

import io.github.nafg.scalacoptions.WarningsConfig.{Category, Filter}


object CompilationTests {
  ScalacOptions.all("")(
    (opts: options.Common) =>
      opts.deprecation ++ opts.unchecked ++ opts.feature,
    (_: options.V2_13).Xlint("_"),
    WarningsConfig(Filter.Category(Category.`lint-byname-implicit`).silent)
  )
}
