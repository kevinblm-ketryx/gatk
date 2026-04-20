---
itemId: TC-CNVS-001
itemType: Test Case
itemTests: SPEC-CNVS-001
---
# Somatic CNV Detection Accuracy on Cell-Line Data

## Preconditions
- Tumor BAM from a cell line with known CNV profile (e.g., HCC1143 breast cancer cell line).
- Matched normal BAM (HCC1143-BL).
- Panel of normals HDF5 constructed from ≥ 30 normal WGS samples.
- GRCh38 reference genome and preprocessed intervals.

## Test Steps

1. Collect read counts for tumor and normal:
   ```
   gatk CollectReadCounts -R GRCh38.fa -I HCC1143.bam -L intervals.interval_list -O tumor_counts.hdf5
   ```
2. Denoise tumor counts against the PoN:
   ```
   gatk DenoiseReadCounts -I tumor_counts.hdf5 --count-panel-of-normals pon.hdf5 \
     --denoised-copy-ratios tumor_denoised.tsv --standardized-copy-ratios tumor_std.tsv
   ```
3. Compare denoised copy ratios to the known CNV profile for HCC1143.
4. Verify that known amplifications (e.g., chr8 MYC locus) and deletions are detected at log₂ CR thresholds.
5. Assess denoising quality: median absolute deviation (MAD) of denoised copy ratios in diploid regions.

## Expected Results

- Known focal amplifications and deletions ≥ 10 kb are detected.
- MAD of denoised copy ratios in diploid regions ≤ 0.2.
- Denoised copy-ratio profile matches published HCC1143 karyotype.
