---
itemId: REQ-BQSR-001
itemType: Requirement
---
# Base Quality Score Recalibration Model Generation

The system shall analyze patterns of systematic error in base quality scores assigned by the sequencing instrument and generate a recalibration model that corrects these scores, improving the accuracy of downstream variant calling.

## Acceptance Criteria

1. BaseRecalibrator shall accept an aligned BAM/CRAM, a reference genome, and one or more known-sites VCFs (e.g., dbSNP, Mills indels).
2. The tool shall build a recalibration model based on covariates: read group, reported quality score, machine cycle, and sequence context.
3. Known variant sites shall be excluded from the error model to avoid confusing true variants with sequencing errors.
4. The output recalibration table shall be a human-readable TSV suitable for inspection and archival.
