---
itemId: RISK-GSNV-002
itemType: Risk
itemIntroducesRisk: REQ-GSNV-002
itemIsRiskControlledBy: SPEC-GSNV-002, TC-GSNV-002
Initial severity: Major
Initial likelihood of occurrence (P1): Low
Residual severity: Major
Residual likelihood of occurrence (P1): Rare
Risk acceptability: ALARP
---
# Incorrect Joint Genotyping Leading to Erroneous Population Frequency Estimates

## Hazard
GenotypeGVCFs produces incorrect genotype calls when combining GVCFs across a large cohort, leading to erroneous allele frequency estimates or wrong genotype assignments for individual samples.

## Potential Harm
Incorrect allele frequency data propagated to downstream analyses or clinical databases (e.g., gnomAD contributions) could misclassify variant pathogenicity. Incorrect individual genotypes could lead to wrong carrier-status assignments in family studies.

## Risk Controls
1. **Specification-level**: SPEC-GSNV-002 mandates correct expansion of reference-confidence blocks and propagation of allele-specific annotations for proper multi-sample genotyping.
2. **Verification-level**: TC-GSNV-002 validates joint genotyping on a trio with Mendelian consistency checks (violation rate ≤ 0.5 %), ensuring genotyping correctness.
3. **Operational**: Large cohort studies perform sample-level QC (call rate, Ti/Tv, het/hom ratio) to detect outlier samples before downstream analysis.

## Residual Risk Justification
Trio Mendelian consistency testing provides high confidence in genotyping accuracy. Residual risk is rare and acceptable under ALARP given standard cohort QC practices.
