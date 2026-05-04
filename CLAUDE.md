# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

scalac-options is a build-tool-agnostic Scala library that provides a type-safe DSL for managing scalac compiler options across different Scala versions (2.11+, 3.x). The library automatically generates version-specific option traits by running Scala compilers and parsing their help output.

The library itself cross-builds on Scala 2.12 / 2.13 / 3.3 (see `build.sbt` for exact patch versions). `AGENTS.md` exists with overlapping guidance for other AI tools — when updating workflow info, keep both in sync rather than duplicating new content here.

## Build Commands

- `sbt compile` - Compile the generator and generated sources
- `sbt downloadScalaCompilerJars` - Prefetch all compiler artifacts from versions.yaml
- `sbt getOutputs` - Read committed help-text from `help-text/`; fetches only entries missing on disk
- `sbt refreshOutputs` - Force re-fetch all help-text from network and overwrite `help-text/`
- `sbt generate` - Write generated trait files to `src/main/scala-generated/io/github/nafg/scalacoptions/`
- `sbt test` - Run tests

## Architecture

### Code Generation Pipeline

The library generates version-specific option traits from sbt tasks defined in the build:

1. **versions.yaml** - Defines Scala versions and their help flags (e.g., `-help`, `-X`, `-Y`)
2. **Generator** (project/Generator.scala) - Orchestrates the generation pipeline:
   - Fetches compiler JARs via Coursier
   - Runs each compiler with help flags to extract available options
   - Parses outputs using FastParseParser
   - Builds inheritance hierarchy of option traits
3. **Output** - Generated traits in `src/main/scala-generated/io/github/nafg/scalacoptions/options/` (committed)

Both intermediates are committed:
- `help-text/<version>/<flag>.txt` - cached `scalac -help` output (Layer 1)
- `src/main/scala-generated/...` - generated trait sources (Layer 2)

`sbt generate` runs on every compile via `sourceGenerators`; CI fails on uncommitted regeneration via `git diff --exit-code`.

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
- **project/GetHelpString.scala** - Downloads compiler JARs and extracts help output
- **project/FastParseParser.scala** - Parses scalac help text into Setting objects
- **project/CodeGen.scala** - Generates trait method definitions
- **project/Container.scala**, **project/Setting.scala**, **project/FlagSegment.scala** - Core data models

### Library Code

Hand-written runtime library code in `src/main/scala/`:

- **ScalacOptions.scala** - Main API: `ScalacOptions.all(scalaVersion.value)(...)` selects appropriate version trait
- **WarningsConfig.scala** - DSL for building `-Wconf` strings with filters and actions
- **VersionOptionsFunction.scala** - Function wrapper for version-specific option selection

Generated traits live at `src/main/scala-generated/io/github/nafg/scalacoptions/` (committed). They are wired into the build via `Compile / sourceGenerators` and not via `unmanagedSourceDirectories`, so each compile re-derives them from `help-text/`.

## Development Workflow

### Adding a New Scala Version

1. Update `versions.yaml` with the new version range and help flags
2. Run `sbt getOutputs` — automatically fetches the now-missing entries and writes them to `help-text/`
3. Run `sbt generate` to regenerate option traits under `src/main/scala-generated/`
4. Review the git diff to verify new options were added correctly, commit both `help-text/` and `src/main/scala-generated/` changes

### Refreshing Help Text Against Existing Versions

To force re-fetch (e.g., after a generator/parser change that should re-pull all upstream help text):

1. Run `sbt refreshOutputs` — overwrites all `help-text/<version>/<flag>.txt`
2. Run `sbt generate`
3. Review and commit the diffs

### Modifying Option Parsing

When changing parser logic in `project/FastParseParser.scala`:
1. Run `sbt generate` (no help-text refresh needed — parsing happens from cached `.txt` files)
2. Review generated output for correctness

### Adding Custom Option Overrides

Some options need manual specification in versions.yaml under `settings:` when the parser can't extract them correctly (e.g., `-language:features`). These override the parsed flag segments.

## Code Style

Follow `.scalafmt.conf` (IntelliJ style, 120-column limit). Format changes with a scalafmt-enabled editor or CLI. Generated identifiers mirror compiler option names, converting hyphens to method names (e.g., `-Xlint` becomes `Xlint`).
