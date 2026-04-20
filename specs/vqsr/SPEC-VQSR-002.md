---
itemId: SPEC-VQSR-002
itemType: Software Item Spec
itemFulfills: REQ-VQSR-002
---
# ApplyVQSR Tranche Application Specification

## Overview

ApplyVQSR reads the VQSLOD scores and tranche definitions produced by VariantRecalibrator and applies FILTER tags to each variant in the input VCF based on the selected sensitivity tranche.

## Behavior

### VQSLOD Assignment
- Each variant receives a VQSLOD score from the recalibration table, stored in the INFO field.
- VQSLOD represents the log-odds ratio of the variant being true vs. false, calibrated by the GMM.

### Tranche-Based Filtering
- The user selects a truth sensitivity threshold (e.g., 99.5 for SNPs, 99.0 for indels).
- Variants with VQSLOD above the tranche cutoff are marked `PASS`.
- Variants below the cutoff but above the next tranche are marked with the tranche range (e.g., `VQSRTrancheSNP99.50to99.90`).
- Variants below all tranches are marked `VQSRTrancheSNP99.90+`.

### Allele-Specific Mode
- In AS mode, each allele at a multiallelic site is independently scored and filtered.
- The site-level FILTER is the least restrictive of the per-allele filters.

### Sequential Application
- VQSR is applied in two passes: first for SNPs (`-mode SNP`), then for indels (`-mode INDEL`), preserving the filters from the first pass.
