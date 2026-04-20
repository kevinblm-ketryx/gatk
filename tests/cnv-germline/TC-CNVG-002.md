---
itemId: TC-CNVG-002
itemType: Test Case
itemTests: SPEC-CNVG-002
---
# Germline CNV Genotyping Accuracy

## Preconditions
- GermlineCNVCaller output from TC-CNVG-001.
- Orthogonal validation data (array CGH or SNP array CNV calls) for a subset of samples.
- Contig ploidy calls from DetermineGermlineContigPloidy.

## Test Steps

1. Postprocess CNV calls for each sample:
   ```
   gatk PostprocessGermlineCNVCalls --calls-shard-path cohort_cnv_model/calls/ \
     --model-shard-path cohort_cnv_model/model/ \
     --contig-ploidy-calls ploidy_model/calls/ \
     --sample-index 0 -O sample0_cnv.vcf
   ```
2. Verify VCF contains proper `<DEL>` and `<DUP>` alleles with GT, CN, QS, NP fields.
3. Filter calls at QS ≥ 20 and compare genotypes to orthogonal validation.
4. Assess genotype concordance (CN state match) for high-confidence calls.
5. Verify segment boundaries are consistent with the underlying interval-level posterior data.

## Expected Results

- Output VCF conforms to VCF 4.2 SV specification.
- Genotype concordance ≥ 95 % for calls with QS ≥ 20 against orthogonal validation.
- DEL and DUP alleles are correctly assigned based on copy-number state relative to baseline ploidy.
- Quality scores are well-calibrated (observed error rate matches Phred-scaled QS).
