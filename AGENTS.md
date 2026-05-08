# Repository Guidelines

## Project Structure & Module Organization
The primary build definition lives in `build.sbt`, with release automation in `ci.sbt` and Scala release metadata in `versions.yaml`. Build-definition Scala code, including the generator invoked by sbt tasks, resides under `project/` (e.g., `project/Generator.scala`, `project/Versions.scala`, `project/VersionUpdater.scala`); `project/*.sbt` configures that build definition's own build. Hand-written runtime library code lives under `src/main/scala`, while generated option traits are emitted under `target/scala-*/src_managed`.

## Build, Test, and Development Commands
- `sbt compile` – compile the library; option traits are produced as a sourceGenerator step.
- `sbt test` – run the test suite (currently `src/test/scala/io/github/nafg/scalacoptions/`); use `+test` to run across the cross-build (Scala 2.12 / 2.13 / 3.3).

`updateVersions`, `launcher/regenerateScalacHelp`, and `generate` run automatically (via GitHub Actions or sourceGenerator) and shouldn't be invoked by hand.

## Coding Style & Naming Conventions
Follow the `.scalafmt.conf` preset (IntelliJ style, 120-column limit, two-space indents). Format changes with a scalafmt-enabled editor or CLI before committing. Stick to CamelCase for Scala classes/traits (`Container`, `FlagSegment`) and lowerCamelCase for vals/defs. When adding options, mirror the existing naming in `project/Setting.scala` so generated identifiers remain predictable.

## Testing Guidelines
When introducing logic changes, add focused unit tests under `src/test/scala` using MUnit, which is the test framework currently configured for this repository. Name test files after the type under test, e.g., `GeneratorSpec.scala`. If you touch version parsing or flag handling, recompile and review the diff in `target/scala-*/src_managed/` to ensure no unintended option churn.

## Commit & Pull Request Guidelines
Commit messages should be concise and imperative (“Add Scala 3.3.2 descriptors”). PRs should describe the motivation and list key changes. Link related issues and include before/after snippets when updating generated sources. For dependency bumps or version additions, mention the impacted Scala releases and any follow-up tasks so maintainers can coordinate downstream publishing.
