---
itemId: REQ-FUNC-002
itemType: Requirement
---
# MAF Output for Oncology Workflows

The system shall produce Mutation Annotation Format (MAF) output compatible with downstream oncology analysis tools (e.g., cBioPortal, MutSig, MAFTools) for tumor variant characterization.

## Acceptance Criteria

1. Funcotator shall support MAF output format with all required MAF columns (Hugo_Symbol, Variant_Classification, Tumor_Seq_Allele2, etc.).
2. MAF output shall correctly populate tumor and normal sample barcode fields from the input VCF.
3. Oncotree-compatible variant classifications shall be assigned for coding and non-coding variants.
4. Multi-allelic sites shall be decomposed into separate MAF rows, one per alternate allele.
