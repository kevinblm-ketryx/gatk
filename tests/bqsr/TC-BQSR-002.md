---
itemId: TC-BQSR-002
itemType: Test Case
itemTests: SPEC-BQSR-002
---
# ApplyBQSR Score Accuracy and Read Integrity

## Preconditions
- Recalibration table from TC-BQSR-001.
- Original aligned BAM for NA12878.
- GRCh38 reference genome.

## Test Steps

1. Apply recalibration:
   ```
   gatk ApplyBQSR -R GRCh38.fa -I NA12878.bam \
     --bqsr-recal-file recal_data.table -O NA12878_recal.bam
   ```
2. Verify all reads are preserved: compare read counts between original and recalibrated BAMs using `samtools flagstat`.
3. Verify base quality scores have changed: compare QUAL fields between original and recalibrated BAMs.
4. Run AnalyzeCovariates to generate before/after diagnostic plots:
   ```
   gatk AnalyzeCovariates -before recal_data.table -after post_recal.table -plots recal_plots.pdf
   ```
5. Measure RMSE of quality scores before and after recalibration.
6. Run HaplotypeCaller on both original and recalibrated BAMs and compare variant calling metrics.

## Expected Results

- Read count is identical between original and recalibrated BAMs.
- RMSE of quality scores is reduced by ≥ 50 % after recalibration.
- AnalyzeCovariates plots show convergence of reported and empirical quality scores.
- Variant calling on recalibrated BAM shows improved genotype quality (GQ) scores.
