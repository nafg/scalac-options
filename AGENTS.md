# Repository Guidelines

## Project Structure & Module Organization
The primary build definition lives in `build.sbt`, with release automation in `ci.sbt` and Scala release metadata in `versions.yaml`. Generator source code resides under `project/` (e.g., `project/Generator.scala`, `project/Versions.scala`), because the sbt build itself performs code generation. Running the generator writes managed sources into `src/main/scala`, so hand-written library code should remain under `project/` while generated artifacts stay in `target/scala-*/src_managed`. There are currently no persistent sources under `src/test`, so add new test suites there when they become necessary.

## Build, Test, and Development Commands
- `sbt compile` – compile the generator and any generated sources.
- `sbt downloadScalaCompilerJars` – prefetch all compiler artifacts defined in `versions.yaml`; run this once whenever you update the version list.
- `sbt getOutputs` – fetch and cache `scalac -help` outputs for the configured versions; use to refresh local caches.
- `sbt generate` – regenerate option case classes under `src/main/scala/io/github/nafg/scalacoptions`.
- `sbt test` – executes the (currently empty) test suite; keep it green when adding tests.

## Coding Style & Naming Conventions
Follow the `.scalafmt.conf` preset (IntelliJ style, 120-column limit, two-space indents). Format changes with a scalafmt-enabled editor or by running the scalafmt CLI before committing. Stick to CamelCase for Scala classes/traits (`Container`, `FlagSegment`) and lowerCamelCase for vals/defs. When adding options, mirror the existing naming in `project/Setting.scala` so generated identifiers remain predictable.

## Testing Guidelines
When introducing logic changes, add focused unit tests under `src/test/scala` using ScalaTest or MUnit (your choice, but keep the dependency lightweight). Name test files after the type under test, e.g., `GeneratorSpec.scala`. If you touch version parsing or flag handling, run `sbt getOutputs` followed by a regeneration and review the diff to ensure no unintended option churn.

## Commit & Pull Request Guidelines
Commit messages should be concise and imperative (“Add Scala 3.3.2 descriptors”). PRs should describe the motivation, list key changes, and note any manual steps (such as running `sbt generate`). Link related issues and include before/after snippets when updating generated sources. For dependency bumps or version additions, mention the impacted Scala releases and any follow-up tasks so maintainers can coordinate downstream publishing.
