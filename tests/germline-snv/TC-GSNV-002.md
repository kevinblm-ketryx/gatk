---
itemId: TC-GSNV-002
itemType: Test Case
itemTests: SPEC-GSNV-002
---
# Joint Genotyping Accuracy on Trio GVCF Data

## Preconditions
- Per-sample GVCFs for an NA12878/NA12891/NA12892 trio produced by HaplotypeCaller in GVCF mode.
- GRCh38 reference genome.
- GenomicsDB or combined GVCF workspace for the trio.

## Test Steps

1. Import GVCFs into GenomicsDB:
   ```
   gatk GenomicsDBImport -V NA12878.g.vcf -V NA12891.g.vcf -V NA12892.g.vcf \
     --genomicsdb-workspace-path trio_gdb -L chr20
   ```
2. Run GenotypeGVCFs:
   ```
   gatk GenotypeGVCFs -R GRCh38.fa -V gendb://trio_gdb -O trio_joint.vcf
   ```
3. Verify output VCF contains genotypes for all three samples at each variant site.
4. Check Mendelian inheritance consistency using `bcftools +mendelian`.
5. Confirm allele-specific annotations (AS_QD, AS_FS) are present in the INFO field.

## Expected Results

- All three samples have genotype calls at every variant site.
- Mendelian violation rate ≤ 0.5 % in high-confidence regions.
- Allele-specific annotations are present for all variant sites.
