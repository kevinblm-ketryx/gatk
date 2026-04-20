---
itemId: TC-VMAN-003
itemType: Test Case
itemTests: SPEC-VMAN-003
---
# Variant Normalization Correctness

## Preconditions
- VCF containing a curated set of variants with known non-normalized representations:
  - Right-aligned indels in homopolymer regions.
  - Variants with redundant padding bases.
  - Multi-allelic sites with 3+ alternate alleles.
- GRCh38 reference genome.

## Test Steps

1. Run LeftAlignAndTrimVariants:
   ```
   gatk LeftAlignAndTrimVariants -R GRCh38.fa -V unnormalized.vcf -O normalized.vcf
   ```
2. Verify indels are left-aligned by comparing to bcftools norm output.
3. Verify redundant padding bases are trimmed.
4. Split multi-allelic sites:
   ```
   gatk LeftAlignAndTrimVariants -R GRCh38.fa -V unnormalized.vcf \
     --split-multi-allelics -O normalized_split.vcf
   ```
5. Verify each multi-allelic site is decomposed into bi-allelic records.
6. Verify allele-specific INFO annotations are correctly partitioned.
7. Confirm the biological identity of each variant is preserved (same genomic change, canonical representation).

## Expected Results

- All indels are at their leftmost position after normalization.
- No redundant padding bases remain in REF or ALT alleles.
- Multi-allelic sites are correctly split into bi-allelic records.
- Allele-specific annotations match the corresponding allele in split records.
- Variant count: original variant count ≤ split variant count (multi-allelics increase count).
- Normalized output matches bcftools norm output for equivalent settings.
