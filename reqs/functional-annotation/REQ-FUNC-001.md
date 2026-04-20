---
itemId: REQ-FUNC-001
itemType: Requirement
---
# Functional Annotation of Genomic Variants

The system shall annotate variant calls with functional impact information, including gene context, transcript consequence, and protein change predictions, using reference gene models and pre-computed data sources.

## Acceptance Criteria

1. Funcotator shall accept a VCF of variant calls and produce an annotated VCF or MAF file with functional annotations.
2. Annotations shall include: gene name, transcript ID, variant classification (missense, nonsense, silent, splice site, etc.), protein change (HGVS notation), and genome change.
3. The tool shall support both GRCh37 and GRCh38 reference builds with corresponding data sources.
4. Annotation data sources shall include GENCODE transcript models, gnomAD allele frequencies, ClinVar clinical significance, and COSMIC somatic mutation frequencies.
