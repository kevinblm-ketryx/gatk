---
itemId: REQ-CNVG-002
itemType: Requirement
---
# Germline CNV Genotyping and Quality Scoring

The system shall assign copy-number genotypes (CN0, CN1, CN2, CN3, etc.) to detected germline CNV events and provide calibrated quality scores for filtering.

## Acceptance Criteria

1. PostprocessGermlineCNVCalls shall convert GermlineCNVCaller model output into per-sample VCF files with copy-number genotypes.
2. Each CNV call shall include a quality score (QS) reflecting the posterior probability of the assigned copy-number state.
3. Calls with QS ≥ 20 shall have a genotype concordance ≥ 95 % with orthogonal validation (array CGH or MLPA).
4. The output VCF shall conform to the VCF 4.2 structural variant specification with proper `<DEL>` and `<DUP>` alleles.
