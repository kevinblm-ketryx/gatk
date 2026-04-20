---
itemId: TC-CNVS-002
itemType: Test Case
itemTests: SPEC-CNVS-002
---
# Somatic CNV Segmentation and CN-LOH Detection

## Preconditions
- Denoised copy ratios from TC-CNVS-001.
- Allelic counts collected at heterozygous SNP sites for HCC1143 tumor and matched normal.
- Known CN-LOH regions for HCC1143.

## Test Steps

1. Collect allelic counts:
   ```
   gatk CollectAllelicCounts -R GRCh38.fa -I HCC1143.bam -L common_snps.interval_list \
     -O tumor_allelic.tsv
   ```
2. Run ModelSegments:
   ```
   gatk ModelSegments --denoised-copy-ratios tumor_denoised.tsv \
     --allelic-counts tumor_allelic.tsv -O segments/
   ```
3. Call segments:
   ```
   gatk CallCopyRatioSegments -I segments/tumor.cr.seg -O tumor_called.seg
   ```
4. Verify CN-LOH regions are identified (neutral copy ratio + skewed allele fraction).
5. Compare called segments to known HCC1143 cytogenetic profile.

## Expected Results

- Segmentation produces contiguous segments with consistent copy ratio and allele fraction.
- CN-LOH regions are correctly identified with minor allele fraction < 0.3 and neutral copy ratio.
- Called segments match known amplifications, deletions, and CN-LOH events.
- MCMC diagnostics show convergence (no divergence warnings).
