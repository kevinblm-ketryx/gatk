---
itemId: REQ-SV-002
itemType: Requirement
---
# Structural Variant Genotyping and Filtering

The system shall genotype discovered structural variants across a cohort and apply quality-based filtering to produce a high-confidence SV call set.

## Acceptance Criteria

1. SVs discovered across the cohort shall be genotyped per sample with allele-specific read evidence.
2. Each SV call shall include a genotype quality (GQ) score reflecting confidence in the assigned genotype.
3. After filtering, the false discovery rate shall be ≤ 10 % for SVs ≥ 300 bp.
4. The output VCF shall conform to the VCF 4.2 structural variant specification with proper SVTYPE, SVLEN, and END fields.
