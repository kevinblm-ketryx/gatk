---
itemId: REQ-SV-001
itemType: Requirement
---
# Structural Variant Discovery via Split-Read and Discordant-Pair Analysis

The system shall detect structural variants (deletions, duplications, inversions, translocations) from whole-genome sequencing data using split-read and discordant read-pair evidence.

## Acceptance Criteria

1. The SV pipeline (GATK-SV) shall accept aligned BAM/CRAM files and produce structural variant calls in VCF format.
2. The pipeline shall detect SVs ≥ 50 bp including deletions, duplications, inversions, and inter-chromosomal translocations.
3. SV sensitivity shall be ≥ 85 % for events ≥ 300 bp on the GIAB Tier 1 SV truth set (HG002).
4. The pipeline shall integrate multiple evidence types: split reads, discordant pairs, read depth, and B-allele frequency.
