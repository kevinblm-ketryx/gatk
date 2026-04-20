---
itemId: REQ-VMAN-001
itemType: Requirement
---
# VCF Selection and Subsetting

The system shall enable selection of specific variants, samples, or genomic intervals from VCF files to support targeted downstream analysis and data management.

## Acceptance Criteria

1. SelectVariants shall accept a VCF and produce a subset VCF based on configurable criteria.
2. Selection shall support filtering by: sample name, variant type (SNP, INDEL, MIXED), genomic interval, JEXL expressions on INFO/FORMAT fields, and allele count/frequency thresholds.
3. Sample subsetting shall correctly update allele counts (AC), allele frequencies (AF), and allele number (AN) annotations.
4. The tool shall support excluding as well as including variants matching the selection criteria.
