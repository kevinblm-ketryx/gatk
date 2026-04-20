---
itemId: SPEC-VMAN-003
itemType: Software Item Spec
itemFulfills: REQ-VMAN-003
---
# Variant Normalization and Left-Alignment Specification

## Overview

LeftAlignAndTrimVariants normalizes indel representation by left-aligning insertions and deletions against the reference genome and removing redundant padding bases.

## Behavior

### Left-Alignment
- Indels are shifted left until the leftmost possible position is reached (the position where the indel can no longer be shifted further left without changing the reference context).
- This follows the VCF specification recommendation for indel normalization.
- Example: `chr1:100 AT>A` might become `chr1:95 CA>C` after left-alignment if the deleted base is within a homopolymer run.

### Allele Trimming
- Redundant prefix and suffix bases shared between REF and ALT alleles are removed.
- The POS field is adjusted to reflect the trimmed representation.
- Example: `chr1:100 ACGT>ATGT` becomes `chr1:101 C>T` after trimming.

### Multi-Allelic Splitting
- When `--split-multi-allelics` is enabled, multi-allelic records are decomposed into bi-allelic records.
- Allele-specific INFO annotations (e.g., AC, AF) are partitioned to the appropriate bi-allelic record.
- Genotype fields (GT, AD, PL) are recalculated for the bi-allelic context.

### Invariants
- The biological meaning of every variant is preserved: the same set of alternate alleles at the same genomic position is represented, just in canonical form.
- Reference alleles are never modified in a way that changes the implied sequence change.

### Output
- Normalized VCF with left-aligned, trimmed variants.
- Optionally split multi-allelic sites into separate records.
