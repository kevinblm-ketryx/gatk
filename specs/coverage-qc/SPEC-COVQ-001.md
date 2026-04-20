---
itemId: SPEC-COVQ-001
itemType: Software Item Spec
itemFulfills: REQ-COVQ-001
---
# Coverage Analysis Tools Specification

## Overview

GATK provides multiple coverage analysis tools tailored for whole-genome (WGS), whole-exome (WES), and targeted sequencing data, computing depth and breadth of coverage metrics.

## Behavior

### CollectWgsMetrics
- Scans the entire genome (or specified intervals) and computes per-base depth.
- Output metrics include:
  - MEAN_COVERAGE, SD_COVERAGE, MEDIAN_COVERAGE.
  - PCT_1X through PCT_100X: fraction of bases at ≥ 1×, ≥ 5×, ≥ 10×, ..., ≥ 100× depth.
  - PCT_EXC_MAPQ, PCT_EXC_DUPE, PCT_EXC_UNPAIRED, PCT_EXC_BASEQ: fraction of bases excluded by quality filters.
- Reads are filtered by minimum mapping quality (default: 20) and minimum base quality (default: 20).
- Duplicate reads and reads failing vendor quality checks are excluded by default.

### DepthOfCoverage
- Produces per-locus depth (every base position), per-interval summary (mean and coverage thresholds), and per-sample cumulative depth distribution.
- Supports multi-sample BAMs with per-sample stratification.
- Configurable depth thresholds for summary reporting (e.g., 1, 5, 10, 15, 20, 25, 30, 40, 50).

### CollectHsMetrics
- Designed for hybrid-selection (exome capture) experiments.
- Requires bait and target interval files.
- Metrics include: ON_BAIT_BASES, ON_TARGET_BASES, PCT_SELECTED_BASES, FOLD_ENRICHMENT, MEAN_TARGET_COVERAGE, ZERO_CVG_TARGETS_PCT.
- Near-target bases (within 250 bp of targets) are reported separately.

### Output
- Picard-format metrics files (header + metrics table + histogram).
- TSV files for per-locus and per-interval data (DepthOfCoverage).
