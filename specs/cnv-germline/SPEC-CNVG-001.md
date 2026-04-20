---
itemId: SPEC-CNVG-001
itemType: Software Item Spec
itemFulfills: REQ-CNVG-001
---
# Germline CNV Detection Pipeline Specification

## Overview

The germline CNV pipeline uses a probabilistic model combining read-depth denoising and cohort-level analysis to detect inherited copy-number variants.

## Behavior

### DetermineGermlineContigPloidy
- Estimates per-sample, per-contig ploidy using a Bayesian model over total read counts per contig.
- Handles sex chromosome ploidy (XX vs. XY) automatically.
- Produces contig-ploidy priors consumed by the CNV caller.

### GermlineCNVCaller
- Operates in two modes:
  - **COHORT mode**: jointly models CNV events across multiple samples to improve detection power.
  - **CASE mode**: calls CNVs for individual samples using a pre-trained cohort model.
- Uses a hierarchical Hidden Markov Model (HMM) with:
  - Interval-level emission model based on denoised read counts (negative binomial).
  - Transition model with configurable mean event length and copy-number state priors.
- Variational Bayes inference produces posterior distributions over copy-number states per interval per sample.

### Interval Preparation
- PreprocessIntervals defines the interval set (1000 bp bins for WGS, padded exons for WES).
- AnnotateIntervals adds GC content and mappability annotations for bias correction.
- FilterIntervals removes low-mappability and extreme-GC intervals.

### Output
- Per-sample posterior copy-number state probabilities per interval.
- Model parameter files for case-mode reuse.
