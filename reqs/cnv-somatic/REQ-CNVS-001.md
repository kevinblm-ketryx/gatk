---
itemId: REQ-CNVS-001
itemType: Requirement
---
# Somatic Copy Number Variation Detection

The system shall detect somatic copy number alterations (amplifications and deletions) in tumor samples relative to a matched normal or panel of normals, using read-depth coverage analysis.

## Acceptance Criteria

1. The CNV pipeline shall accept tumor BAM/CRAM and a panel of normals (PoN) or matched normal, and produce copy-ratio segments in SEG format.
2. CollectReadCounts shall bin reads into configurable genomic intervals and produce HDF5 read-count files.
3. DenoiseReadCounts shall normalize tumor read counts against the PoN to remove systematic biases (GC content, mappability).
4. The pipeline shall detect focal amplifications and deletions ≥ 10 kb at copy-ratio log₂ thresholds of ≥ 0.2 (gain) and ≤ −0.2 (loss).
