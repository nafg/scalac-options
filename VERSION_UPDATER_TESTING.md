# Testing the Version Updater

This document describes how to test the automated version updater functionality.

## Available Test Commands

### 1. Unit Tests (No Network Required)
```bash
sbt testVersionUpdater
```

This runs offline unit tests that validate:
- Version string parsing (e.g., "2.13.12", "3.3.0-RC1")
- Version ordering and comparison
- Grouping patch versions into ranges
- Merging version ranges
- YAML generation

These tests use mock data and don't require network access or Maven Central.

### 2. Dry Run (Requires Network)
```bash
sbt updateVersionsDryRun
```

This performs a full check against Maven Central but **does not modify** `versions.yaml`. Use this to:
- Verify the updater can connect to Maven Central
- See what changes would be made without actually making them
- Test after modifying the updater logic

Example output:
```
Found 150 available Scala versions
Current version ranges: 8 major versions
Available version ranges: 8 major versions

[DRY RUN] Would make the following changes:
  2.13: 0..18 (was: 0..17)
  3.6: 0..4 (was: 0..3)
```

### 3. Live Update (Modifies versions.yaml)
```bash
sbt updateVersions
```

This queries Maven Central and updates `versions.yaml` if new versions are found.

**Always review the changes** before committing:
```bash
git diff versions.yaml
```

## Testing Workflow

### For Development/Pull Requests

1. **Run unit tests** to verify core logic:
   ```bash
   sbt testVersionUpdater
   ```

2. **Run dry-run** to verify Maven Central integration:
   ```bash
   sbt updateVersionsDryRun
   ```

3. If changes are shown, **test the full update**:
   ```bash
   # Backup current versions
   cp versions.yaml versions.yaml.backup

   # Run the update
   sbt updateVersions

   # Review the diff
   git diff versions.yaml

   # If something looks wrong, restore
   mv versions.yaml.backup versions.yaml
   ```

4. **Verify the updated versions work**:
   ```bash
   sbt downloadScalaCompilerJars
   sbt generate
   sbt test
   ```

### Manual Testing Scenarios

#### Test 1: Verify Parsing Logic
Edit `project/VersionUpdaterTest.scala` to add new test cases:
```scala
val tests = Seq(
  ("2.13.20", Some(VersionUpdater.ScalaVersion(2, 13, 20, None))),
  ("3.7.0-RC2", Some(VersionUpdater.ScalaVersion(3, 7, 0, Some(2))))
)
```

#### Test 2: Test Range Grouping
```scala
val versions = Seq(
  VersionUpdater.ScalaVersion(2, 13, 0, None),
  VersionUpdater.ScalaVersion(2, 13, 1, None),
  // Gap here
  VersionUpdater.ScalaVersion(2, 13, 5, None),
  VersionUpdater.ScalaVersion(2, 13, 6, None)
)

val ranges = VersionUpdater.groupIntoPatchRanges(versions)
// Should produce: Seq(0..1, 5..6)
```

#### Test 3: Test Default Flags
Verify that default helpFlags are correct for each version:
```scala
assert(VersionUpdater.defaultHelpFlags(2, 13).contains("-W"))
assert(VersionUpdater.defaultHelpFlags(3, 3).contains("-W"))
assert(!VersionUpdater.defaultHelpFlags(2, 12).contains("-W"))
```

## CI Integration

The unit tests can be added to CI since they don't require network access:

```yaml
# Add to .github/workflows/ci.yml
- name: Test version updater
  run: sbt testVersionUpdater
```

The dry-run and live update commands are better suited for:
- Scheduled workflows (e.g., weekly cron job)
- Manual workflow dispatch
- Dependabot-style automation

## Troubleshooting

### Test Failures

If `testVersionUpdater` fails:
1. Check the assertion that failed
2. Verify the logic in `project/VersionUpdater.scala`
3. Add debugging output if needed

### Network Issues

If `updateVersionsDryRun` fails with network errors:
1. Check your internet connection
2. Verify Maven Central is accessible: https://repo1.maven.org/maven2/
3. Check if there are proxy/firewall issues

### Parsing Errors

If versions.yaml becomes malformed:
1. Restore from backup: `git checkout versions.yaml`
2. Check the YAML generation logic in `generateYamlContent`
3. Verify indentation is correct (YAML is whitespace-sensitive)

## What Gets Tested

### Unit Tests Cover
- ✓ Version string parsing
- ✓ Version comparison and ordering
- ✓ Patch range grouping logic
- ✓ Range merging logic
- ✓ YAML generation format

### Dry Run Covers
- ✓ Maven Central API access
- ✓ Coursier version fetching
- ✓ Current versions.yaml parsing
- ✓ End-to-end workflow (without file writes)

### Integration Testing (Manual)
- ✓ Full workflow with file modification
- ✓ Compatibility with `downloadScalaCompilerJars`
- ✓ Compatibility with `generate` task
- ✓ Generated code compiles and tests pass
