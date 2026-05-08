# Repository Guidelines

## Project Structure & Module Organization
The primary build definition lives in `build.sbt`, with release automation in `ci.sbt` and Scala release metadata in `versions.yaml`. Build-definition Scala code, including the generator invoked by sbt tasks, resides under `project/` (e.g., `project/Generator.scala`, `project/Versions.scala`, `project/VersionUpdater.scala`); `project/*.sbt` configures that build definition's own build. Hand-written runtime library code lives under `src/main/scala`, while generated option traits are emitted under `target/scala-*/src_managed`.

## Build, Test, and Development Commands
- `sbt compile` – compile the generator and any generated sources.
- `sbt updateVersions` / `sbt updateVersionsDryRun` – query Maven Central and extend the top-most ranges in `versions.yaml` for new patch releases (dry-run mode reports what would change without writing).
- `sbt generate` – regenerate option traits under `target/scala-*/src_managed/io/github/nafg/scalacoptions`. Compiler JARs are fetched lazily and `scalac -help` outputs are cached per `(version, flag)` inside `runScalacFn`, so subsequent runs only re-execute pairs that haven't been seen.
- `sbt test` – run the test suite (currently `src/test/scala/io/github/nafg/scalacoptions/CompilationTests.scala`); use `+test` to run across the cross-build (Scala 2.12 / 2.13 / 3.3).

## Coding Style & Naming Conventions
Follow the `.scalafmt.conf` preset (IntelliJ style, 120-column limit, two-space indents). Format changes with a scalafmt-enabled editor or CLI before committing. Stick to CamelCase for Scala classes/traits (`Container`, `FlagSegment`) and lowerCamelCase for vals/defs. When adding options, mirror the existing naming in `project/Setting.scala` so generated identifiers remain predictable.

## Testing Guidelines
When introducing logic changes, add focused unit tests under `src/test/scala` using ScalaTest or MUnit (your choice, but keep the dependency lightweight). Name test files after the type under test, e.g., `GeneratorSpec.scala`. If you touch version parsing or flag handling, run `sbt generate` and review the diff to ensure no unintended option churn — the per-`(version, flag)` cache will reuse prior `scalac -help` outputs automatically.

## Commit & Pull Request Guidelines
Commit messages should be concise and imperative (“Add Scala 3.3.2 descriptors”). PRs should describe the motivation, list key changes, and note any manual steps (such as running `sbt generate`). Link related issues and include before/after snippets when updating generated sources. For dependency bumps or version additions, mention the impacted Scala releases and any follow-up tasks so maintainers can coordinate downstream publishing.
