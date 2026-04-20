---
itemId: REQ-VQSR-002
itemType: Requirement
---
# Application of VQSR Sensitivity Tranches

The system shall apply the trained VQSR model to variant calls, assigning calibrated quality scores (VQSLOD) and partitioning variants into sensitivity tranches to enable flexible filtering.

## Acceptance Criteria

1. ApplyVQSR shall accept a VCF and the recalibration model from VariantRecalibrator and produce a VCF with VQSLOD scores and tranche-based FILTER tags.
2. At the 99.5 % sensitivity tranche for SNPs, the false discovery rate shall be ≤ 1.0 %.
3. At the 99.0 % sensitivity tranche for indels, the false discovery rate shall be ≤ 3.0 %.
4. Variants above the selected tranche threshold shall be marked PASS; those below shall carry a descriptive FILTER tag.
