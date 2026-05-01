# Repository Guidelines

## Project Structure & Module Organization
The primary build definition lives in `build.sbt`, with release automation in `ci.sbt` and Scala release metadata in `versions.yaml`. Build-definition Scala code, including the generator invoked by sbt tasks, resides under `project/` (e.g., `project/Generator.scala`, `project/Versions.scala`); `project/*.sbt` configures that build definition's own build. Hand-written runtime library code lives under `src/main/scala`. Cached `scalac -help` output is committed under `help-text/<version>/<flag>.txt`, and generated option traits are committed under `src/main/scala-generated/io/github/nafg/scalacoptions/`.

## Build, Test, and Development Commands
- `sbt compile` – compile the generator and any generated sources.
- `sbt downloadScalaCompilerJars` – prefetch all compiler artifacts defined in `versions.yaml`.
- `sbt getOutputs` – read committed help-text from `help-text/`; fetches only entries that are missing on disk (e.g., after adding a new version to `versions.yaml`).
- `sbt refreshOutputs` – force re-fetch all `scalac -help` outputs from network, overwriting `help-text/`.
- `sbt generate` – regenerate trait files under `src/main/scala-generated/io/github/nafg/scalacoptions`.
- `sbt test` – run the test suite (`src/test/scala/io/github/nafg/scalacoptions/CompilationTests.scala`); use `+test` to run across the cross-build (Scala 2.12 / 2.13 / 3.3).

## Coding Style & Naming Conventions
Follow the `.scalafmt.conf` preset (IntelliJ style, 120-column limit, two-space indents). Format changes with a scalafmt-enabled editor or CLI before committing. Stick to CamelCase for Scala classes/traits (`Container`, `FlagSegment`) and lowerCamelCase for vals/defs. When adding options, mirror the existing naming in `project/Setting.scala` so generated identifiers remain predictable.

## Testing Guidelines
When introducing logic changes, add focused unit tests under `src/test/scala` using ScalaTest or MUnit (your choice, but keep the dependency lightweight). Name test files after the type under test, e.g., `GeneratorSpec.scala`. If you touch version parsing or flag handling, run `sbt generate` and review the diff under `src/main/scala-generated/` to ensure no unintended option churn. Use `sbt refreshOutputs` only when the upstream `scalac -help` output itself needs to be re-pulled.

## Commit & Pull Request Guidelines
Commit messages should be concise and imperative (“Add Scala 3.3.2 descriptors”). PRs should describe the motivation, list key changes, and note any manual steps (such as running `sbt generate`). Link related issues and include before/after snippets when updating generated sources. For dependency bumps or version additions, mention the impacted Scala releases and any follow-up tasks so maintainers can coordinate downstream publishing.
