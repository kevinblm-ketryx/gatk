---
itemId: TC-SSNV-002
itemType: Test Case
itemTests: SPEC-SSNV-002
---
# FilterMutectCalls Filtering Performance

## Preconditions
- Mutect2 raw VCF and stats file from TC-SSNV-001.
- Orientation bias model from LearnReadOrientationModel.
- Contamination estimate from CalculateContamination.
- DREAM Challenge truth VCF.

## Test Steps

1. Learn orientation bias model:
   ```
   gatk LearnReadOrientationModel -I mutect2_f1r2.tar.gz -O orientation_model.tar.gz
   ```
2. Run FilterMutectCalls:
   ```
   gatk FilterMutectCalls -R GRCh37.fa -V mutect2_raw.vcf \
     --ob-priors orientation_model.tar.gz \
     --contamination-table contamination.table \
     -O mutect2_filtered.vcf
   ```
3. Evaluate filtered VCF against the truth set.
4. Inspect FILTER tags for correctly identified artifacts.

## Expected Results

- Filtered call set achieves FDR ≤ 5.0 % while retaining sensitivity ≥ 90.0 % at ≥ 10 % VAF.
- Orientation bias artifacts (OxoG, FFPE) are correctly tagged.
- Contamination-related false positives are removed.
- All passing variants carry the `PASS` filter tag.
