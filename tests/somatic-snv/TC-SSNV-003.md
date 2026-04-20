---
itemId: TC-SSNV-003
itemType: Test Case
itemTests: SPEC-SSNV-003
---
# Contamination Estimation Accuracy

## Preconditions
- In-silico BAM mixtures with known contamination levels (0 %, 2 %, 5 %, 10 %).
- gnomAD common-variant VCF (AF > 0.01) for GRCh38.

## Test Steps

1. For each contamination level, run GetPileupSummaries:
   ```
   gatk GetPileupSummaries -I mixed_sample.bam -V gnomad_common.vcf \
     -L intervals.list -O pileup_summary.table
   ```
2. Run CalculateContamination:
   ```
   gatk CalculateContamination -I pileup_summary.table -O contamination.table
   ```
3. Compare estimated contamination to the known ground truth.
4. Verify the contamination table format is compatible with FilterMutectCalls.

## Expected Results

- Estimated contamination within ± 1 % of true value for all test levels.
- Zero-contamination sample estimates ≤ 0.5 %.
- Output contamination table is a valid TSV consumable by FilterMutectCalls.
