---
itemId: REQ-CNVG-001
itemType: Requirement
---
# Germline Copy Number Variation Detection

The system shall detect germline copy number variants (deletions and duplications) from whole-genome or whole-exome sequencing data using a read-depth and allele-fraction based approach with a cohort or matched-interval panel of normals.

## Acceptance Criteria

1. The germline CNV pipeline shall accept sample BAM/CRAM files and a panel of normals to produce per-sample germline CNV calls.
2. DetermineGermlineContigPloidy shall estimate per-sample, per-contig baseline ploidy and produce ploidy prior models.
3. GermlineCNVCaller shall detect germline CNV events at interval-level resolution.
4. The pipeline shall detect deletions and duplications spanning ≥ 3 consecutive intervals with sensitivity ≥ 90 % for events ≥ 10 kb on validated truth sets.
