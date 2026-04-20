---
itemId: REQ-VMAN-003
itemType: Requirement
---
# Variant Normalization and Left-Alignment

The system shall normalize variant representations by left-aligning indels and trimming redundant allele padding to ensure consistent variant representation across call sets.

## Acceptance Criteria

1. LeftAlignAndTrimVariants shall left-align indels with respect to the reference genome and trim redundant bases from alleles.
2. After normalization, the same biological variant shall have an identical VCF representation regardless of the calling tool that produced it.
3. Multi-allelic sites shall be optionally split into bi-allelic records for compatibility with tools that do not support multi-allelic VCFs.
4. Normalization shall not alter the biological meaning of any variant call.
