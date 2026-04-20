---
itemId: SPEC-BQSR-002
itemType: Software Item Spec
itemFulfills: REQ-BQSR-002
---
# ApplyBQSR Score Application Specification

## Overview

ApplyBQSR reads the recalibration model produced by BaseRecalibrator and adjusts each base quality score in the input BAM/CRAM, writing out a recalibrated alignment file.

## Behavior

### Quality Score Adjustment
- For each base, the recalibration delta is looked up from the recalibration table using the base's covariate values (read group, original quality, context, cycle).
- The adjusted quality score is: original quality + delta(ReadGroup) + delta(Quality) + delta(Context) + delta(Cycle).
- Adjusted scores are capped at the Phred maximum (93) and floored at the minimum usable quality (2).

### Static Quantization (Optional)
- When `--static-quantized-quals` is specified, recalibrated scores are binned into discrete levels (e.g., 10, 20, 30, 40) to improve compression ratios in CRAM output.

### Read Preservation
- All SAM fields other than QUAL are preserved exactly.
- Original quality scores are optionally retained in the OQ tag (`--emit-original-quals`).

### Output
- BAM or CRAM file with recalibrated base qualities.
- AnalyzeCovariates consumes before/after recalibration tables to produce diagnostic PDF/CSV reports.
