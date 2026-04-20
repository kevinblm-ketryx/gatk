---
itemId: SPEC-VQSR-001
itemType: Software Item Spec
itemFulfills: REQ-VQSR-001
---
# VariantRecalibrator Model Training Specification

## Overview

VariantRecalibrator uses a Gaussian Mixture Model (GMM) to learn the distribution of annotation values for true variants (from truth/training resources) versus artifacts, producing a VQSLOD score for each variant.

## Behavior

### Resource Configuration
- Each input resource VCF is tagged with `known`, `training`, and/or `truth` labels and a prior probability (e.g., hapmap: known=false, training=true, truth=true, prior=15.0).
- Training resources define the positive training set; truth resources define the set used for tranche calculation.

### Annotation-Based Feature Space
- Variant-level annotations used as features:
  - **QD** (Quality by Depth), **FS** (Fisher Strand), **SOR** (Strand Odds Ratio), **MQ** (Mapping Quality).
  - **MQRankSum** and **ReadPosRankSum** for rank-sum tests of mapping quality and read position bias.
  - For allele-specific mode: AS_ prefixed annotations are used per allele.

### Gaussian Mixture Model
- The GMM is fit using Expectation-Maximization (EM) with configurable maximum Gaussians (default: 8 for SNPs, 4 for indels).
- The model produces a VQSLOD (Variant Quality Score Log-Odds) for each variant, representing the log-odds of the variant being a true positive relative to a false positive.

### Output
- Recalibration table with VQSLOD scores per variant.
- Tranches file defining sensitivity thresholds based on truth set overlap.
- Tranches plot (R script or PDF) visualizing the sensitivity-specificity tradeoff.
