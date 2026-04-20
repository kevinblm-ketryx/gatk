---
itemId: SPEC-GSNV-002
itemType: Software Item Spec
itemFulfills: REQ-GSNV-002
---
# GenotypeGVCFs Joint Genotyping Specification

## Overview

GenotypeGVCFs performs joint genotyping on per-sample GVCFs, combining evidence across samples to produce a multi-sample VCF with accurate genotypes at every variant site.

## Behavior

### Input Handling
- Accepts one or more GVCF files directly, or a GenomicsDB workspace created by GenomicsDBImport.
- Reference-confidence blocks are expanded to per-base records internally for genotyping.

### Allele Discovery
- All alternate alleles across input GVCFs at each position are collected; the `<NON_REF>` symbolic allele is resolved to concrete alleles.
- Multiallelic sites retain all observed alternate alleles up to `--max-alternate-alleles` (default: 6).

### Genotype Calculation
- Per-sample genotype likelihoods from GVCFs are combined with the allele set to assign diploid genotypes.
- Allele-specific annotations (AS_QD, AS_FS, AS_MQRankSum, AS_ReadPosRankSum, AS_SOR) are propagated from per-sample data.

### Output
- Multi-sample VCF with genotype fields (GT, AD, DP, GQ, PL) and allele-specific INFO annotations.
- Sites where no sample has a non-reference genotype are omitted.

## Scalability
- GenomicsDB provides column-major storage for efficient random access across thousands of samples.
- Recommended workflow: GenomicsDBImport (batched per interval) → GenotypeGVCFs per interval → GatherVcfs.
