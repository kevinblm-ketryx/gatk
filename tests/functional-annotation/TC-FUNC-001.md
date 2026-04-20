---
itemId: TC-FUNC-001
itemType: Test Case
itemTests: SPEC-FUNC-001
---
# Funcotator Annotation Accuracy on Known Variants

## Preconditions
- VCF containing a curated set of ≥ 50 variants with known functional consequences (from ClinVar pathogenic variants with established protein changes).
- Funcotator data sources for GRCh38 (GENCODE v34, gnomAD 3.1, ClinVar, COSMIC v95).
- GRCh38 reference genome.

## Test Steps

1. Run Funcotator in VCF output mode:
   ```
   gatk Funcotator -R GRCh38.fa -V known_variants.vcf \
     --data-sources-path funcotator_dataSources/ \
     --ref-version hg38 --output-file-format VCF -O annotated.vcf
   ```
2. Parse FUNCOTATION field from annotated VCF.
3. Compare variant classifications to expected values (e.g., BRAF V600E → Missense_Mutation).
4. Verify protein change annotations match HGVS notation (e.g., p.V600E).
5. Confirm splice-site variants within 2 bp of exon boundaries are classified as Splice_Site.
6. Verify gnomAD allele frequencies and ClinVar significance are populated.

## Expected Results

- Variant classification concordance ≥ 99 % with expected functional impact.
- Protein change annotations match established HGVS notation for all coding variants.
- Splice-site variants are correctly identified.
- gnomAD and ClinVar annotations are present for variants in those databases.
- All overlapping transcripts are reported, with canonical transcript listed first.
