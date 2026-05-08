# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

scalac-options is a build-tool-agnostic Scala library that provides a type-safe DSL for managing scalac compiler options across different Scala versions (2.11+, 3.x). The library automatically generates version-specific option traits by running Scala compilers and parsing their help output.

The library itself cross-builds on Scala 2.12 / 2.13 / 3.3 (see `build.sbt` for exact patch versions). `AGENTS.md` exists with overlapping guidance for other AI tools — when updating workflow info, keep both in sync rather than duplicating new content here.

## Build Commands

- `sbt compile` - Compile the library (generated sources are produced as a sourceGenerator step)
- `sbt test` - Run tests; `sbt +test` to cross-build against all Scala versions

`generate` runs automatically as a sourceGenerator step when you run `sbt compile`.
`updateVersions` extends `versions.yaml` from Maven Central and is run by the `update-versions.yml` workflow, or manually via `sbt updateVersions`.

## Architecture

### Code Generation Pipeline

The library generates version-specific option traits from sbt tasks defined in the build:

1. **versions.yaml** - Defines Scala versions and their help flags (e.g., `-help`, `-X`, `-Y`)
2. **Generator** (project/Generator.scala) - Orchestrates the generation pipeline:
   - Fetches compiler JARs via Coursier
   - Runs each compiler with help flags to extract available options
   - Parses outputs using FastParseParser
   - Builds inheritance hierarchy of option traits
3. **Output** - Generated traits in `target/scala-*/src_managed/io/github/nafg/scalacoptions/options/`

### Key Abstractions

- **Setting** (project/Setting.scala) - Represents a single compiler option with name, flag segments, description, and deprecation status
- **FlagSegment** - Either a literal string or a parameter (e.g., `-language:` + parameter)
- **Container** (project/Container.scala) - Represents a version trait with inherited settings. Generates trait code with parent relationships
- **Version Hierarchy** - Options are organized as:
  - `Common` - Available in all Scala versions
  - `V2`, `V3` - Entire epochs
  - `V2_13`, `V3_0` - Major versions
  - `V2_13_4_+` - Minimum minor versions (range traits)
  - `V2_13_4` - Concrete versions (objects extending their trait)

### Generator Organization

Build-definition Scala code lives under `project/` so sbt can use it from build tasks:

- **project/Generator.scala** - Main orchestration logic
- **project/Versions.scala** - Parses versions.yaml and models version hierarchy
- **project/VersionUpdater.scala** - Queries Maven Central and extends top-most ranges in versions.yaml when new patch releases appear
- **launcher/** subproject (`launcher/src/main/scala/.../launcher/Scalac.scala`) - Standalone main that fetches a compiler via Coursier and runs it in a forked JVM, capturing combined stdout+stderr. Wired into the build via the `runScalacFn` task.
- **project/FastParseParser.scala** - Parses scalac help text into Setting objects
- **project/CodeGen.scala** - Generates trait method definitions
- **project/Container.scala**, **project/Setting.scala**, **project/FlagSegment.scala** - Core data models

### Library Code

Hand-written runtime library code in `src/main/scala/`:

- **ScalacOptions.scala** - Main API: `ScalacOptions.all(scalaVersion.value)(...)` selects appropriate version trait
- **WarningsConfig.scala** - DSL for building `-Wconf` strings with filters and actions
- **VersionOptionsFunction.scala** - Function wrapper for version-specific option selection

Generated traits go to `target/.../src_managed/` and are included as managed sources.

## Development Workflow

### Modifying Option Parsing

When changing parser logic in `project/FastParseParser.scala`, the next `sbt compile` will re-run the sourceGenerator. The `(version, flag)` cache reuses prior `scalac -help` outputs, so you only re-run scalac for entries that haven't been seen. Review the regenerated traits under `target/scala-*/src_managed/` for correctness.

### Adding Custom Option Overrides

Some options need manual specification in versions.yaml under `settings:` when the parser can't extract them correctly (e.g., `-language:features`). These override the parsed flag segments.

## Code Style

Follow `.scalafmt.conf` (IntelliJ style, 120-column limit). Format changes with a scalafmt-enabled editor or CLI. Generated identifiers mirror compiler option names, converting hyphens to method names (e.g., `-Xlint` becomes `Xlint`).
