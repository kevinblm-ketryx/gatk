---
itemId: TC-FUNC-002
itemType: Test Case
itemTests: SPEC-FUNC-002
---
# Funcotator MAF Output Compatibility

## Preconditions
- Somatic VCF from Mutect2 (tumor-normal pair) containing SNVs, indels, and multi-allelic sites.
- Funcotator data sources for GRCh38.
- Reference MAF specification from NCI GDC.

## Test Steps

1. Run Funcotator in MAF output mode:
   ```
   gatk Funcotator -R GRCh38.fa -V somatic.vcf \
     --data-sources-path funcotator_dataSources/ \
     --ref-version hg38 --output-file-format MAF -O somatic.maf
   ```
2. Validate MAF file structure: verify all required columns are present.
3. Verify Tumor_Sample_Barcode and Matched_Norm_Sample_Barcode match VCF sample names.
4. Check multi-allelic site decomposition: confirm separate rows per alternate allele.
5. Import MAF into MAFTools (R package) and verify successful parsing.
6. Spot-check Hugo_Symbol, Variant_Classification, and HGVSp_Short for 10 known variants.

## Expected Results

- MAF file contains all required GDC MAF columns.
- Sample barcodes correctly match VCF sample names.
- Multi-allelic sites are decomposed into individual rows.
- MAFTools successfully imports and summarizes the MAF without errors.
- Variant classifications and protein changes match expected values.
