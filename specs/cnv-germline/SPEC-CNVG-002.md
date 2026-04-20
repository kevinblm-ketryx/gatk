---
itemId: SPEC-CNVG-002
itemType: Software Item Spec
itemFulfills: REQ-CNVG-002
---
# Germline CNV Postprocessing and Genotyping Specification

## Overview

PostprocessGermlineCNVCalls converts the interval-level posterior probabilities from GermlineCNVCaller into segment-level CNV calls with copy-number genotypes in VCF format.

## Behavior

### Segment Merging
- Consecutive intervals with the same most-probable copy-number state are merged into segments.
- Segment boundaries are refined using the posterior probability transition points.

### Quality Score Calculation
- The quality score (QS) for each segment is the Phred-scaled probability of the called copy-number state being incorrect.
- QS = −10 × log₁₀(1 − P(called CN state)), where P is the posterior probability marginalized over the segment's intervals.

### VCF Output
- Each CNV event is represented as a structural variant record:
  - `<DEL>` for copy-number losses (CN < baseline ploidy).
  - `<DUP>` for copy-number gains (CN > baseline ploidy).
- FORMAT fields include: GT (genotype), CN (copy number), QS (quality score), NP (number of points/intervals).
- Events are reported per sample with sample-specific genotypes.

### Filtering
- Default quality threshold: QS ≥ 20 for high-confidence calls.
- Calls overlapping segmental duplications or known benign CNV regions can be annotated using external databases.
