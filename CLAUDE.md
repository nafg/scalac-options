# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

scalac-options is a build-tool-agnostic Scala library that provides a type-safe DSL for managing scalac compiler options across different Scala versions (2.11+, 3.x). The library automatically generates version-specific option traits by running Scala compilers and parsing their help output.

## Build Commands

- `sbt compile` - Compile the generator and generated sources
- `sbt updateVersions` - Query Maven Central and update versions.yaml with latest Scala releases
- `sbt updateVersionsDryRun` - Check for new versions without modifying versions.yaml
- `sbt testVersionUpdater` - Run unit tests for the version updater
- `sbt downloadScalaCompilerJars` - Prefetch all compiler artifacts from versions.yaml (run once after updating version list)
- `sbt getOutputs` - Fetch and cache `scalac -help` outputs for all configured versions
- `sbt generate` - Regenerate option case classes under `src/main/scala/io/github/nafg/scalacoptions`
- `sbt test` - Run tests

See [VERSION_UPDATER_TESTING.md](VERSION_UPDATER_TESTING.md) for detailed testing instructions.

## Architecture

### Code Generation Pipeline

The library uses a meta-build approach where generation happens inside the sbt build itself:

1. **versions.yaml** - Defines Scala versions and their help flags (e.g., `-help`, `-X`, `-Y`)
2. **Generator** (project/Generator.scala) - Orchestrates the generation pipeline:
   - Fetches compiler JARs via Coursier
   - Runs each compiler with help flags to extract available options
   - Parses outputs using FastParseParser
   - Builds inheritance hierarchy of option traits
3. **Output** - Generated traits in `src/main/scala/io/github/nafg/scalacoptions/options/`

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

All generator source code lives under `project/` because sbt's meta-build performs the generation:

- **project/Generator.scala** - Main orchestration logic
- **project/Versions.scala** - Parses versions.yaml and models version hierarchy
- **project/VersionUpdater.scala** - Fetches latest Scala releases from Maven Central and updates versions.yaml
- **project/GetHelpString.scala** - Downloads compiler JARs and extracts help output
- **project/FastParseParser.scala** - Parses scalac help text into Setting objects
- **project/CodeGen.scala** - Generates trait method definitions
- **project/Container.scala**, **project/Setting.scala**, **project/FlagSegment.scala** - Core data models

### Library Code

Hand-written runtime library code in `src/main/scala/`:

- **ScalacOptions.scala** - Main API: `ScalacOptions.all(scalaVersion.value)(...)` selects appropriate version trait
- **WarningsConfig.scala** - DSL for building `-Wconf` strings with filters and actions
- **VersionOptionsFunction.scala** - Function wrapper for version-specific option selection

Generated traits go to `target/.../src_managed/` but are also written to `src/main/scala/` for publishing.

## Development Workflow

### Updating to Latest Scala Versions (Automated)

The project includes an automated version updater that fetches the latest Scala releases from Maven Central:

1. Run `sbt updateVersions` to automatically update `versions.yaml` with new Scala releases
2. Run `sbt downloadScalaCompilerJars` to fetch the new compiler JARs
3. Run `sbt generate` to regenerate option traits with the new versions
4. Review the git diff to verify new options were added correctly

The updater automatically:
- Queries Maven Central for all scala-compiler (2.x) and scala3-compiler_3 (3.x) releases
- Groups patch versions into ranges (e.g., "0..17" for patches 0 through 17)
- Applies appropriate default helpFlags based on the version (e.g., Scala 2.13+ includes `-W`)
- Extends existing version ranges when new patches are released
- Preserves custom settings defined in versions.yaml

### Adding a New Scala Version (Manual)

If you need to manually add a version or customize settings:

1. Update `versions.yaml` with the new version range and help flags
2. Run `sbt downloadScalaCompilerJars` to fetch the new compiler
3. Run `sbt generate` to regenerate option traits
4. Review the git diff to verify new options were added correctly

### Modifying Option Parsing

When changing parser logic in `project/FastParseParser.scala`:
1. Run `sbt getOutputs` to refresh cached outputs (if needed)
2. Run `sbt generate` to regenerate with new parsing logic
3. Review generated output for correctness

### Adding Custom Option Overrides

Some options need manual specification in versions.yaml under `settings:` when the parser can't extract them correctly (e.g., `-language:features`). These override the parsed flag segments.

## Code Style

Follow `.scalafmt.conf` (IntelliJ style, 120-column limit). Generated identifiers mirror compiler option names, converting hyphens to method names (e.g., `-Xlint` becomes `Xlint`).
