---
itemId: TC-SSNV-001
itemType: Test Case
itemTests: SPEC-SSNV-001
---
# Mutect2 Somatic Calling Accuracy on Synthetic Tumor Data

## Preconditions
- DREAM Challenge synthetic tumor/normal BAM pair (e.g., IS1 dataset).
- GRCh37 reference genome.
- DREAM Challenge truth VCF for somatic mutations.
- Panel of normals VCF constructed from ≥ 40 normal samples.

## Test Steps

1. Run Mutect2 in tumor-normal mode:
   ```
   gatk Mutect2 -R GRCh37.fa -I tumor.bam -I normal.bam \
     --tumor-sample TUMOR --normal-sample NORMAL \
     --panel-of-normals pon.vcf -O mutect2_raw.vcf
   ```
2. Evaluate raw calls against the DREAM truth set using `som.py` or equivalent.
3. Run Mutect2 in tumor-only mode (without matched normal) and evaluate.
4. Verify PoN filtering suppresses known recurrent artifact sites.

## Expected Results

- Tumor-normal mode: SNV sensitivity ≥ 95.0 % at ≥ 10 % VAF; precision ≥ 90.0 %.
- Tumor-only mode: sensitivity ≥ 90.0 % at ≥ 10 % VAF (precision may be lower).
- PoN-filtered variants do not appear in the final call set.
- Output VCF contains TLOD, NLOD, and F1R2 annotations.
