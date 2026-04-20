---
itemId: SPEC-VMAN-001
itemType: Software Item Spec
itemFulfills: REQ-VMAN-001
---
# SelectVariants Specification

## Overview

SelectVariants extracts a subset of variants and/or samples from a VCF based on user-specified criteria, producing a reduced VCF for targeted analysis.

## Behavior

### Selection Criteria
- **Sample selection**: `--sample-name` or `--sample-file` to include specific samples; `--exclude-sample-name` to exclude.
- **Variant type**: `--select-type-to-include` (SNP, INDEL, MIXED, MNP, SYMBOLIC) or `--select-type-to-exclude`.
- **Interval selection**: `-L` for genomic intervals (BED, interval list, or region string).
- **JEXL expressions**: `--select` accepts Java Expression Language filters on INFO/FORMAT fields (e.g., `vc.getGenotype("sample1").getGQ() > 20`).
- **Allele criteria**: `--min-allele-count`, `--max-allele-count`, `--min-allele-frequency`, `--max-allele-frequency`.
- **Mendelian violations**: `--mendelian-violation` with a pedigree file to select de novo candidates.

### Annotation Update
- After sample subsetting, AC, AF, and AN are recalculated from the remaining samples.
- Genotype-level annotations (DP, GQ, AD) are preserved for retained samples.
- Alleles no longer present in any retained sample are removed (`--remove-unused-alternates`).

### Output
- VCF containing only variants and samples matching the selection criteria.
- Header is updated to reflect retained samples and modified annotations.
