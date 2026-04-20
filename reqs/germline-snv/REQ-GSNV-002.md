---
itemId: REQ-GSNV-002
itemType: Requirement
---
# Joint Genotyping of Germline Variants Across Cohorts

The system shall perform joint genotyping across multiple samples using per-sample GVCF files produced by HaplotypeCaller, enabling population-scale variant discovery via GenotypeGVCFs.

## Acceptance Criteria

1. GenotypeGVCFs shall accept one or more GVCF files (or a GenomicsDB workspace) and produce a multi-sample VCF.
2. Joint calling shall correctly represent reference-confidence blocks so that variant sites in any sample are genotyped across all samples.
3. The tool shall scale to cohorts of ≥ 1,000 samples without loss of genotyping accuracy.
4. Allele-specific annotations (AS_QD, AS_FS, AS_MQ) shall be propagated to the output VCF for downstream filtering.
