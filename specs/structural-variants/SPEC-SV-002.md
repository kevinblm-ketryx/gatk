---
itemId: SPEC-SV-002
itemType: Software Item Spec
itemFulfills: REQ-SV-002
---
# Structural Variant Genotyping and Filtering Specification

## Overview

After discovery, SVs are genotyped across all cohort samples and filtered using a random-forest classifier trained on quality annotations and known SV truth sets.

## Behavior

### Genotyping
- Each discovered SV is genotyped per sample by re-examining split-read and discordant-pair evidence at the breakpoint loci.
- Genotype likelihoods are computed for REF/REF, REF/ALT, and ALT/ALT states.
- The genotype quality (GQ) is the Phred-scaled difference between the best and second-best genotype likelihoods.

### Cohort-Level Filtering
- A random-forest (RF) classifier is trained on:
  - Positive training set: SVs overlapping known polymorphic SV databases (e.g., gnomAD-SV, 1000 Genomes SV).
  - Negative training set: SVs with low evidence or conflicting signals.
- Features include: SR_COUNT, PE_COUNT, RD evidence, allele frequency, size, and call-set overlap.
- The RF score is used to partition SVs into PASS and filtered categories.

### Batch Effect Correction
- Allele frequencies are compared across sequencing batches to detect and remove batch-specific artifacts.
- SVs present only in a single batch at elevated frequency are flagged as potential artifacts.

### Output
- Filtered cohort VCF with per-sample genotypes (GT, GQ, RD_CN, PE_GQ, SR_GQ).
- FILTER tags: PASS, LOW_QUALITY, BATCH_EFFECT, UNRESOLVED.
