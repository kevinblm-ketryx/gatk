---
itemId: TC-SV-002
itemType: Test Case
itemTests: SPEC-SV-002
---
# Structural Variant Genotyping and Filtering on Cohort

## Preconditions
- Discovered SVs from TC-SV-001 plus additional cohort samples (≥ 100 WGS samples).
- Known SV truth sets for training (gnomAD-SV, 1000 Genomes SV).
- HG002 GIAB truth set for evaluation.

## Test Steps

1. Genotype discovered SVs across the cohort.
2. Train the random-forest filter:
   ```
   gatk TrainSVFilter --training-vcf cohort_genotyped.vcf \
     --positive-training-sites gnomad_sv.vcf \
     --model-output sv_rf_model.pkl
   ```
3. Apply the filter:
   ```
   gatk FilterSVCalls --input cohort_genotyped.vcf \
     --model sv_rf_model.pkl --output cohort_filtered.vcf
   ```
4. Evaluate HG002 calls (PASS only) against the GIAB truth set.
5. Inspect GQ distribution for correctly and incorrectly genotyped SVs.

## Expected Results

- FDR ≤ 10 % for PASS SVs ≥ 300 bp.
- Genotype concordance ≥ 90 % for HG002 at GQ ≥ 20.
- Batch-effect artifacts are correctly filtered.
- Output VCF contains proper SVTYPE, SVLEN, END, and per-sample GT/GQ fields.
