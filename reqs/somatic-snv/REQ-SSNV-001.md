---
itemId: REQ-SSNV-001
itemType: Requirement
---
# Somatic SNV and Indel Calling via Mutect2

The system shall detect somatic single-nucleotide variants (SNVs) and small insertions/deletions in tumor samples, with or without a matched normal, using the Mutect2 somatic variant caller.

## Acceptance Criteria

1. Mutect2 shall accept tumor BAM/CRAM input (and optionally a matched normal BAM/CRAM) with a reference genome and produce somatic calls in VCF format.
2. The caller shall perform local assembly of haplotypes in active regions and use a somatic genotyping model that does not assume diploid germline priors.
3. Somatic SNV sensitivity shall be ≥ 95.0 % at ≥ 10 % variant allele frequency (VAF) on the DREAM Challenge synthetic tumor truth set.
4. The tool shall support panel-of-normals (PoN) filtering to suppress recurrent technical artifacts.
5. Tumor-only mode shall be supported for samples without a matched normal.
