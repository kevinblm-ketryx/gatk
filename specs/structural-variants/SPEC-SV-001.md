---
itemId: SPEC-SV-001
itemType: Software Item Spec
itemFulfills: REQ-SV-001
---
# Structural Variant Discovery Specification

## Overview

GATK-SV uses a multi-algorithm ensemble approach to detect structural variants by combining evidence from split reads, discordant read pairs, read depth, and B-allele frequency.

## Behavior

### Evidence Collection
- **Split reads**: Reads with supplementary alignments are identified as split-read evidence for SV breakpoints. Collected by CollectSVEvidence.
- **Discordant pairs**: Read pairs with abnormal insert size or orientation provide evidence for deletions, duplications, inversions, and translocations.
- **Read depth**: Normalized read-depth profiles are analyzed for large deletions and duplications (same pipeline as somatic CNV).
- **B-allele frequency**: Allele-fraction shifts at heterozygous SNPs support CN-LOH and duplication calls.

### SV Callers (Ensemble)
- Multiple upstream SV callers (Manta, Wham, MELT for mobile elements) produce candidate SV calls.
- GATK-SV merges and clusters candidates across callers using breakpoint proximity (default: 300 bp slop for breakends).

### Breakpoint Refinement
- Split-read evidence refines breakpoint positions to single-base resolution where possible.
- Complex SVs (e.g., dispersed duplications, inversions with flanking deletions) are assembled from simple SV components.

### Output
- Raw SV VCF with SVTYPE (DEL, DUP, INV, BND, INS), SVLEN, END, and per-caller support annotations.
- Evidence annotation (SR_COUNT, PE_COUNT, RD_LOG2_RATIO) per sample.
