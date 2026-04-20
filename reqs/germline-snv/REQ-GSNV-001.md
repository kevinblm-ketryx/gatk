---
itemId: REQ-GSNV-001
itemType: Requirement
---
# Germline SNP and Indel Calling via HaplotypeCaller

The system shall identify germline single-nucleotide polymorphisms (SNPs) and small insertions/deletions (indels) from whole-genome or whole-exome sequencing data using a local de novo assembly–based variant caller (HaplotypeCaller).

## Acceptance Criteria

1. HaplotypeCaller shall accept aligned BAM/CRAM input files with a reference genome and produce variant calls in VCF or GVCF format.
2. The caller shall perform local de novo assembly of haplotypes in active regions where variation is detected.
3. SNP sensitivity shall be ≥ 99.0 % and indel sensitivity ≥ 95.0 % on the Genome in a Bottle NA12878 truth set (high-confidence regions).
4. Genotype concordance with the truth set shall be ≥ 99.5 % for SNPs and ≥ 98.0 % for indels.
5. The tool shall support GVCF mode (`-ERC GVCF`) for scalable joint calling across cohorts.
