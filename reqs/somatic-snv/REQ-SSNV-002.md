---
itemId: REQ-SSNV-002
itemType: Requirement
---
# Somatic Variant Filtering via FilterMutectCalls

The system shall provide a post-calling filtering step that applies statistical models to distinguish true somatic mutations from sequencing and sample-preparation artifacts.

## Acceptance Criteria

1. FilterMutectCalls shall accept a Mutect2 VCF and associated statistics file and produce a filtered VCF with FILTER annotations.
2. Filtering shall apply contamination estimates, orientation bias priors, and normal artifact detection.
3. After filtering, the false discovery rate shall be ≤ 5.0 % while retaining ≥ 90.0 % sensitivity for variants at ≥ 10 % VAF.
4. LearnReadOrientationModel shall produce an artifact prior table from F1R2 counts for orientation bias filtering.
5. When `--ffpe-mode` is enabled, FilterMutectCalls shall additionally suppress C→T / G→A deamination artifacts characteristic of formalin-fixed samples without degrading sensitivity for non-deamination substitution classes; default-mode behavior shall be unchanged when `--ffpe-mode` is not supplied.
