---
itemId: SPEC-FUNC-002
itemType: Software Item Spec
itemFulfills: REQ-FUNC-002
---
# Funcotator MAF Output Specification

## Overview

Funcotator produces MAF (Mutation Annotation Format) output for compatibility with oncology analysis pipelines, ensuring proper column mapping and variant decomposition.

## Behavior

### MAF Column Mapping
- Required MAF columns are populated from VCF fields and Funcotator annotations:
  - `Hugo_Symbol`: from GENCODE gene name.
  - `Variant_Classification`: from Funcotator variant classification.
  - `Variant_Type`: SNP, DNP, TNP, ONP, DEL, INS based on allele structure.
  - `Tumor_Sample_Barcode`: from the tumor sample name in the VCF header.
  - `Matched_Norm_Sample_Barcode`: from the normal sample name, if present.
  - `HGVSp_Short`: short protein change notation (e.g., p.V600E).

### Multi-Allelic Decomposition
- Multi-allelic VCF records are split into separate MAF rows, one per alternate allele.
- Each row receives the annotations specific to that alternate allele.
- Allele-specific FORMAT fields (AD, AF) are correctly partitioned.

### Reference and Alternate Allele Representation
- Alleles are left-aligned and trimmed to minimal representation.
- Insertions and deletions are represented with a preceding reference base as per MAF convention.

### Additional Annotation Columns
- gnomAD allele frequency columns (AF, AF_popmax) when gnomAD data source is configured.
- ClinVar clinical significance and review status.
- COSMIC occurrence count and tissue distribution.

### Output
- Tab-delimited MAF file with header row specifying column names.
- Comment lines at the top with tool version and data source versions.
