---
itemId: TC-GSNV-001
itemType: Test Case
itemTests: SPEC-GSNV-001
---
# HaplotypeCaller Accuracy on NA12878

## Preconditions
- Aligned BAM for NA12878 (whole-genome, ≥ 30× mean coverage).
- GRCh38 reference genome with associated index and dictionary.
- Genome in a Bottle (GIAB) v4.2 high-confidence VCF and BED for NA12878.

## Test Steps

1. Run HaplotypeCaller in VCF mode on the NA12878 BAM:
   ```
   gatk HaplotypeCaller -R GRCh38.fa -I NA12878.bam -O NA12878_hc.vcf
   ```
2. Restrict evaluation to GIAB high-confidence regions using `hap.py`:
   ```
   hap.py GIAB_NA12878.vcf NA12878_hc.vcf -r GRCh38.fa \
     -f GIAB_highconf.bed -o eval_result
   ```
3. Extract sensitivity and precision metrics from `eval_result.summary.csv`.
4. Run HaplotypeCaller in GVCF mode and verify output contains `<NON_REF>` alleles and reference blocks.

## Expected Results

- SNP sensitivity ≥ 99.0 %, SNP precision ≥ 99.5 %.
- Indel sensitivity ≥ 95.0 %, indel precision ≥ 98.0 %.
- GVCF output parses correctly and contains MIN_DP annotations in reference blocks.
