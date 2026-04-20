---
itemId: TC-VQSR-001
itemType: Test Case
itemTests: SPEC-VQSR-001
---
# VQSR Model Training Convergence and Quality

## Preconditions
- Joint-called VCF from a cohort of ≥ 30 whole-genome samples (e.g., 1000 Genomes subset).
- Truth/training resources: HapMap 3.3, Omni 2.5, 1000G Phase 1 high-confidence SNPs, dbSNP.
- Mills and 1000G gold-standard indels for indel model.
- GRCh38 reference genome.

## Test Steps

1. Train the SNP model:
   ```
   gatk VariantRecalibrator -R GRCh38.fa -V cohort.vcf \
     --resource:hapmap,known=false,training=true,truth=true,prior=15.0 hapmap.vcf \
     --resource:omni,known=false,training=true,truth=true,prior=12.0 omni.vcf \
     --resource:1000G,known=false,training=true,truth=false,prior=10.0 1000G.vcf \
     --resource:dbsnp,known=true,training=false,truth=false,prior=7.0 dbsnp.vcf \
     -an QD -an FS -an SOR -an MQ -an MQRankSum -an ReadPosRankSum \
     -mode SNP -O snp_recal.table --tranches-file snp_tranches
   ```
2. Train the indel model with appropriate resources and annotations.
3. Verify model convergence (no EM convergence warnings in log).
4. Inspect tranches file for monotonic sensitivity increase.
5. Verify VQSLOD scores are present and normally distributed for truth-set variants.

## Expected Results

- GMM converges without warnings for both SNP and indel models.
- Tranches file shows truth sensitivity levels from 90.0 to 99.9.
- VQSLOD distribution for truth-positive variants is right-shifted relative to false positives.
- Recalibration table contains entries for all input variants.
