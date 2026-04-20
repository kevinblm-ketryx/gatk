---
itemId: TC-VQSR-002
itemType: Test Case
itemTests: SPEC-VQSR-002
---
# ApplyVQSR Filtering Accuracy on Cohort Data

## Preconditions
- Recalibration tables and tranches files from TC-VQSR-001 (SNP and indel models).
- Joint-called cohort VCF.
- GIAB truth set for NA12878 (included in the cohort).

## Test Steps

1. Apply SNP recalibration at the 99.5 sensitivity tranche:
   ```
   gatk ApplyVQSR -R GRCh38.fa -V cohort.vcf \
     --recal-file snp_recal.table --tranches-file snp_tranches \
     --truth-sensitivity-filter-level 99.5 -mode SNP -O cohort_snp_recal.vcf
   ```
2. Apply indel recalibration at the 99.0 sensitivity tranche:
   ```
   gatk ApplyVQSR -R GRCh38.fa -V cohort_snp_recal.vcf \
     --recal-file indel_recal.table --tranches-file indel_tranches \
     --truth-sensitivity-filter-level 99.0 -mode INDEL -O cohort_recal.vcf
   ```
3. Extract NA12878 from the cohort and evaluate against GIAB truth set.
4. Verify FILTER tags are correctly assigned based on tranche thresholds.

## Expected Results

- SNP sensitivity ≥ 99.0 % at FDR ≤ 1.0 % for NA12878 in GIAB high-confidence regions.
- Indel sensitivity ≥ 95.0 % at FDR ≤ 3.0 %.
- Variants carry appropriate FILTER tags (PASS, VQSRTrancheSNP..., VQSRTrancheINDEL...).
- VQSLOD scores are present in the INFO field for all variants.
