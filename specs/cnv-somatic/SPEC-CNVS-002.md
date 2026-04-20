---
itemId: SPEC-CNVS-002
itemType: Software Item Spec
itemFulfills: REQ-CNVS-002
---
# Somatic CNV Segmentation and Allelic Analysis Specification

## Overview

ModelSegments integrates denoised copy-ratio data with heterozygous SNP allele fractions to produce copy-number segments with allelic imbalance information.

## Behavior

### Allele Count Collection
- CollectAllelicCounts tallies reference and alternate allele depths at heterozygous SNP sites from a common-variant VCF (e.g., gnomAD AF > 0.05).
- Output: allelic counts TSV with ref_count, alt_count, and minor allele fraction per site.

### Segmentation
- ModelSegments performs kernel-based segmentation on both copy-ratio and allele-fraction tracks simultaneously.
- Change-point detection uses a Markov Chain Monte Carlo (MCMC) approach to estimate segment boundaries.
- Each segment is characterized by a posterior distribution of copy ratio and minor allele fraction.

### CN-LOH Detection
- Segments with copy ratio near 0 (neutral) but minor allele fraction significantly below 0.5 are flagged as CN-LOH.
- These regions have lost one parental allele but duplicated the other, maintaining total copy number.

### CallCopyRatioSegments
- Segments are classified: amplified (log₂ CR > +0.2), deleted (log₂ CR < −0.2), or neutral.
- Allelic imbalance is reported independently of copy-ratio calls.

### Output
- Segment file (.seg) with columns: contig, start, end, num_points, log2_copy_ratio, minor_allele_fraction, call.
- MCMC parameter files for model diagnostics.
