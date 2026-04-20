---
itemId: SPEC-COVQ-002
itemType: Software Item Spec
itemFulfills: REQ-COVQ-002
---
# Sample-Level QC Metrics Specification

## Overview

GATK/Picard provides a suite of QC metrics tools that assess alignment quality, insert-size distributions, and GC bias to identify samples unsuitable for variant calling.

## Behavior

### CollectAlignmentSummaryMetrics
- Stratifies reads by category: FIRST_OF_PAIR, SECOND_OF_PAIR, PAIR, UNPAIRED.
- Per-category metrics:
  - TOTAL_READS, PF_READS_ALIGNED, PCT_PF_READS_ALIGNED.
  - PF_MISMATCH_RATE: mismatch rate for aligned bases (excluding indels).
  - PF_INDEL_RATE: indel rate per aligned base.
  - STRAND_BALANCE: fraction of reads on the positive strand (expected ~0.5).
  - PCT_CHIMERAS: fraction of read pairs with mates mapping to different chromosomes.

### CollectInsertSizeMetrics
- Computes insert-size statistics from properly paired reads.
- Metrics: MEDIAN_INSERT_SIZE, MODE_INSERT_SIZE, MEAN_INSERT_SIZE, STANDARD_DEVIATION.
- WIDTH_OF_10_PERCENT through WIDTH_OF_99_PERCENT: insert-size distribution width at percentile thresholds.
- Histogram data suitable for plotting the insert-size distribution curve.
- Separate metrics per read group if multiple libraries are present.

### CollectGcBiasMetrics
- Bins the genome into windows and computes GC content and normalized coverage per window.
- Output: GC bias curve (normalized coverage vs. GC fraction), with ideal coverage = 1.0 at all GC levels.
- Summary metrics: AT_DROPOUT (coverage loss at low GC), GC_DROPOUT (coverage loss at high GC).
- GC bias that exceeds configurable thresholds indicates library preparation issues.

### CollectMultipleMetrics
- Convenience tool that runs multiple metrics programs in a single pass over the BAM.
- Programs include: AlignmentSummary, InsertSize, GcBias, QualityScoreDistribution, MeanQualityByCycle.

### Output
- One metrics file per program, plus histogram files where applicable.
- All files follow Picard metrics format (comment lines, header row, data rows).
