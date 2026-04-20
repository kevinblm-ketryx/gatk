---
itemId: SPEC-BQSR-001
itemType: Software Item Spec
itemFulfills: REQ-BQSR-001
---
# BaseRecalibrator Model Generation Specification

## Overview

BaseRecalibrator builds an empirical error model by comparing observed base mismatches to the reference genome, stratified by covariates, while excluding positions that overlap known variant sites.

## Behavior

### Covariate Collection
- For each base in each read, the following covariates are recorded:
  - **ReadGroupCovariate**: identifies the sequencing run, lane, and library.
  - **QualityScoreCovariate**: the machine-reported base quality score.
  - **ContextCovariate**: the dinucleotide sequence context surrounding the base.
  - **CycleCovariate**: the position (cycle) of the base within the read.

### Known-Site Masking
- Positions overlapping known variant sites (dbSNP, Mills indels, known indels) are excluded from the mismatch counting to prevent true variants from inflating the error model.

### Error Model Calculation
- For each covariate combination, the empirical error rate is calculated as: mismatches / total bases.
- A Bayesian smoothing prior (Dirichlet) is applied to avoid overfitting on low-observation bins.
- The recalibration table reports the delta between the reported and empirical quality scores for each covariate combination.

### Output
- A GATKReport-format TSV file containing recalibration tables for each covariate, with columns for estimated quality, empirical quality, and number of observations.
