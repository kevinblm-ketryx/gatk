---
itemId: SPEC-FUNC-001
itemType: Software Item Spec
itemFulfills: REQ-FUNC-001
---
# Funcotator Annotation Engine Specification

## Overview

Funcotator (Functional Annotator) annotates variant calls with gene-level and transcript-level functional impact using configurable data sources organized by reference genome build.

## Behavior

### Data Source Architecture
- Data sources are organized in a directory hierarchy: `<data_sources_dir>/<source_name>/<build>/`.
- Each data source provides a configuration file specifying the source type (locusOverlap, simpleXSV, cosmic, gencode).
- GENCODE data source provides transcript models; all other sources provide additional annotations via genomic coordinate overlap or cross-reference.

### Transcript Selection
- For each variant, all overlapping GENCODE transcripts are identified.
- The "best" transcript is selected using a priority scheme:
  1. Canonical transcript (Ensembl canonical or user-specified override list).
  2. Longest coding sequence among remaining transcripts.
  3. Appris principal isoform designation.
- All overlapping transcripts are annotated, with the best transcript reported first.

### Variant Classification
- Coding variants: Missense_Mutation, Nonsense_Mutation, Silent, Frame_Shift_Del, Frame_Shift_Ins, In_Frame_Del, In_Frame_Ins, Splice_Site, Nonstop_Mutation, Start_Codon_SNP.
- Non-coding variants: IGR (intergenic), Intron, 5'UTR, 3'UTR, 5'Flank, 3'Flank, RNA, lincRNA.
- Splice-site variants are identified within configurable distance of exon boundaries (default: 2 bp).

### Protein Change
- HGVS protein notation (e.g., p.V600E) is computed for coding variants.
- Codon change and amino acid change are reported.

### Output Formats
- **VCF**: annotations added as INFO field key-value pairs (FUNCOTATION field with pipe-delimited values).
- **MAF**: tab-delimited MAF file with one row per variant per alternate allele.
