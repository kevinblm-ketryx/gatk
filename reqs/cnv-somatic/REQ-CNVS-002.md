---
itemId: REQ-CNVS-002
itemType: Requirement
---
# Somatic CNV Segmentation and Allelic Analysis

The system shall segment denoised copy-ratio data and integrate allele-fraction information from heterozygous SNP sites to distinguish copy-neutral loss of heterozygosity (CN-LOH) from true deletions.

## Acceptance Criteria

1. ModelSegments shall perform circular binary segmentation (CBS) on denoised copy ratios and allelic counts.
2. The tool shall output segment-level copy ratio, minor allele fraction, and segment quality metrics.
3. CN-LOH regions (normal copy ratio but skewed allele fractions) shall be identified and reported.
4. CallCopyRatioSegments shall classify segments as amplified (+), deleted (−), or neutral (0) with configurable thresholds.
