---
itemId: TC-GSNV-003
itemType: Test Case
itemTests: SPEC-GSNV-003
---
# CNN Variant Filtering Effectiveness

## Preconditions
- HaplotypeCaller VCF for NA12878.
- Aligned BAM for NA12878 (required for 2D model).
- GATK resource bundle: hapmap, 1000G omni VCFs for truth set.

## Test Steps

1. Run CNNScoreVariants with the 1D model:
   ```
   gatk CNNScoreVariants -R GRCh38.fa -V NA12878_hc.vcf -O NA12878_cnn1d.vcf
   ```
2. Run CNNScoreVariants with the 2D model:
   ```
   gatk CNNScoreVariants -R GRCh38.fa -V NA12878_hc.vcf -I NA12878.bam \
     -O NA12878_cnn2d.vcf --tensor-type read_tensor
   ```
3. Apply FilterVariantTranches at the 99.5 sensitivity tranche:
   ```
   gatk FilterVariantTranches -V NA12878_cnn1d.vcf \
     --resource hapmap.vcf --resource omni.vcf \
     --snp-tranche 99.5 --indel-tranche 99.0 -O NA12878_filtered.vcf
   ```
4. Evaluate filtered call set against GIAB truth using `hap.py`.

## Expected Results

- CNN_1D and CNN_2D annotations are present in scored VCFs.
- Filtered call set achieves SNP sensitivity ≥ 99.0 % at FDR ≤ 1.5 %.
- Filtered call set achieves indel sensitivity ≥ 95.0 % at FDR ≤ 5.0 %.
- Variants failing the tranche carry the appropriate FILTER tag.
