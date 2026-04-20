---
itemId: SPEC-SSNV-003
itemType: Software Item Spec
itemFulfills: REQ-SSNV-003
---
# Contamination Estimation Specification

## Overview

GetPileupSummaries and CalculateContamination work together to estimate cross-sample DNA contamination, which is critical for accurate somatic variant filtering.

## Behavior

### GetPileupSummaries
- Accepts a BAM/CRAM and a VCF of common germline variant sites (e.g., gnomAD AF > 0.01).
- At each variant site, counts reference and alternate allele depths.
- Outputs a pileup summary table (contig, position, ref count, alt count, allele frequency).

### CalculateContamination
- Reads pileup summaries for the tumor sample (and optionally the matched normal).
- Fits a contamination model by comparing observed allele fractions at homozygous-alt sites to the expected fraction under contamination.
- Outputs a contamination estimate (fraction) and a segmentation table for FilterMutectCalls.

### Integration
- FilterMutectCalls consumes the contamination table to adjust the posterior probability of somatic variants, reducing false positives from contaminating germline alleles.

## Accuracy
- On in-silico mixtures with known contamination (0 %–10 %), the estimate shall be within ± 1 % absolute of the true value.
