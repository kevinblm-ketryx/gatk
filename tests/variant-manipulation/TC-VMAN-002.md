---
itemId: TC-VMAN-002
itemType: Test Case
itemTests: SPEC-VMAN-002
---
# VCF Merging and Concatenation Integrity

## Preconditions
- VCF files split by chromosome (chr1.vcf through chr22.vcf, chrX.vcf, chrY.vcf) from the same samples.
- Two VCF files from different samples at overlapping intervals.

## Test Steps

1. Concatenate chromosome-level VCFs using GatherVcfs:
   ```
   gatk GatherVcfs -I chr1.vcf -I chr2.vcf ... -I chrY.vcf -O gathered.vcf
   ```
2. Verify total variant count equals sum of per-chromosome variant counts.
3. Verify coordinate sort order is maintained.
4. Merge overlapping VCFs using MergeVcfs:
   ```
   gatk MergeVcfs -I sample_set_A.vcf -I sample_set_B.vcf -O merged.vcf
   ```
5. Verify header contains INFO/FORMAT definitions from both inputs.
6. Verify variant records are interleaved in coordinate order.
7. Check for duplicate variant positions and verify they are correctly represented.

## Expected Results

- GatherVcfs output contains all variants from all chromosomes in sorted order.
- No variants are lost or duplicated during concatenation.
- MergeVcfs output has combined headers and coordinate-sorted records.
- Overlapping positions are correctly interleaved.
