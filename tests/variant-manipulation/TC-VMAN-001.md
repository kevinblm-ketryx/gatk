---
itemId: TC-VMAN-001
itemType: Test Case
itemTests: SPEC-VMAN-001
---
# SelectVariants Subsetting Accuracy

## Preconditions
- Multi-sample VCF (≥ 10 samples) from joint genotyping with SNPs, indels, and mixed variant types.
- A pedigree file for trio samples within the cohort.

## Test Steps

1. Select only SNPs:
   ```
   gatk SelectVariants -V cohort.vcf --select-type-to-include SNP -O snps_only.vcf
   ```
2. Verify output contains only SNP records (no indels or mixed).
3. Select a subset of 3 samples:
   ```
   gatk SelectVariants -V cohort.vcf --sample-name sample1 --sample-name sample2 \
     --sample-name sample3 --remove-unused-alternates -O subset.vcf
   ```
4. Verify AC, AF, AN are recalculated for the 3 retained samples.
5. Apply a JEXL expression filter:
   ```
   gatk SelectVariants -V cohort.vcf --select "QD > 10.0 && FS < 60.0" -O filtered.vcf
   ```
6. Verify all passing variants satisfy QD > 10.0 and FS < 60.0.
7. Select Mendelian violations using the pedigree file and verify flagged de novo candidates.

## Expected Results

- SNP-only VCF contains zero indel or mixed records.
- Sample-subsetted VCF has updated AC/AF/AN and only 3 sample columns.
- Unused alternate alleles are removed from sample-subsetted output.
- JEXL-filtered VCF contains only variants meeting the expression criteria.
- Variant count is consistent between input and output (no records lost outside selection criteria).
