---
itemId: REQ-PATH-001
itemType: Requirement
---
# Microbial Sequence Detection in Sequencing Data

The system shall classify sequencing reads of microbial origin to detect pathogenic organisms in clinical or environmental samples, using a k-mer based taxonomic classification approach.

## Acceptance Criteria

1. PathSeqPipelineSpark shall accept aligned or unaligned BAM files and produce taxonomic classification of non-host reads.
2. The pipeline shall subtract host (human) reads using a host reference k-mer database before classifying remaining reads against a microbial reference.
3. Classification shall support bacteria, viruses, fungi, and parasites.
4. Sensitivity shall be ≥ 90 % for organisms present at ≥ 100 reads in the input data.
5. The tool shall report per-taxon read counts and normalized abundance scores.
