package io.github.nafg.scalacoptions

import io.github.nafg.scalacoptions.WarningsConfig.{Category, Filter}


object CompilationTests {
  ScalacOptions.all("")(
    (opts: options.Common) =>
      opts.deprecation ++ opts.feature,
    (_: options.V2).unchecked,
    (_: options.V3_0).unchecked,
    (_: options.V3_1).unchecked,
    (_: options.V3_2).unchecked,
    (_: options.V3_3).unchecked,
    (_: options.V3_4).unchecked,
    (_: options.V3_5_0).unchecked,
    (_: options.V2_13).Xlint("_"),
    WarningsConfig(Filter.Category(Category.`lint-byname-implicit`).silent)
  )
}
