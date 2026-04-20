---
itemId: RISK-SSNV-001
itemType: Risk
itemIntroducesRisk: REQ-SSNV-001
itemIsRiskControlledBy: SPEC-SSNV-001, TC-SSNV-001
Initial severity: Critical
Initial likelihood of occurrence (P1): Medium
Residual severity: Critical
Residual likelihood of occurrence (P1): Low
Risk acceptability: ALARP
---
# Missed Somatic Mutations in Low-VAF Tumors

## Hazard
Mutect2 fails to detect clinically actionable somatic mutations present at low variant allele frequency (< 10 %), particularly in low-purity tumor samples or liquid biopsy specimens.

## Potential Harm
Patient misses targeted therapy eligibility (e.g., EGFR inhibitors for EGFR-mutant NSCLC) due to undetected actionable mutation, potentially resulting in suboptimal treatment and disease progression.

## Risk Controls
1. **Specification-level**: Mutect2 uses a somatic allele-fraction model (not diploid priors) that can detect variants at VAFs well below 50 %. Panel-of-normals filtering reduces noise without sacrificing low-VAF sensitivity.
2. **Verification-level**: TC-SSNV-001 validates sensitivity at ≥ 10 % VAF. Supplementary validation at lower VAFs (5 %, 2 %) is recommended for liquid biopsy applications.
3. **Operational**: Laboratories using Mutect2 for clinical oncology should validate at the lowest clinically relevant VAF for their assay and may employ UMI-based error correction for ultra-low-frequency detection.

## Residual Risk Justification
At standard sequencing depths (≥ 100× tumor), Mutect2 achieves strong sensitivity for variants ≥ 5 % VAF. Below 5 % VAF, residual risk is mitigated by assay-specific validation. Risk is acceptable under ALARP for the intended clinical sequencing use case.
