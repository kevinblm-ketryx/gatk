---
itemId: TC-BQSR-001
itemType: Test Case
itemTests: SPEC-BQSR-001
---
# BaseRecalibrator Model Quality Validation

## Preconditions
- Aligned BAM for NA12878 (WGS, ≥ 30× coverage).
- GRCh38 reference genome.
- Known-sites VCFs: dbSNP, Mills and 1000G gold-standard indels.

## Test Steps

1. Run BaseRecalibrator:
   ```
   gatk BaseRecalibrator -R GRCh38.fa -I NA12878.bam \
     --known-sites dbsnp.vcf --known-sites mills.vcf \
     -O recal_data.table
   ```
2. Verify the recalibration table is a valid GATKReport-format TSV.
3. Inspect the ReadGroup, QualityScore, Context, and Cycle covariate tables for completeness.
4. Verify known-site positions are excluded by comparing mismatch counts at known vs. novel sites.
5. Run a second pass of BaseRecalibrator on the recalibrated BAM to measure post-recalibration residuals.

## Expected Results

- Recalibration table contains entries for all four covariates.
- Reported quality scores deviate from empirical quality by > 2 Phred points on average before recalibration.
- Second-pass residuals are ≤ 1 Phred point on average, confirming effective recalibration.
