---
itemId: RISK-SSNV-002
itemType: Risk
itemIntroducesRisk: REQ-SSNV-002
itemIsRiskControlledBy: SPEC-SSNV-002, TC-SSNV-002
Initial severity: Major
Initial likelihood of occurrence (P1): Medium
Residual severity: Major
Residual likelihood of occurrence (P1): Low
Risk acceptability: ALARP
---
# False-Positive Somatic Calls from Sequencing Artifacts

## Hazard
Sequencing artifacts (OxoG damage, FFPE deamination, strand bias) pass filtering and are reported as somatic mutations, leading to incorrect variant calls.

## Potential Harm
Patient undergoes unnecessary targeted therapy or is enrolled in an inappropriate clinical trial based on an artifactual variant call, exposing them to treatment side effects without therapeutic benefit.

## Risk Controls
1. **Specification-level**: FilterMutectCalls applies orientation bias, strand bias, and contamination filters specifically designed to remove these artifact classes.
2. **Verification-level**: TC-SSNV-002 validates that filtering achieves FDR ≤ 5 % on synthetic tumor data, confirming artifact suppression effectiveness.
3. **Operational**: Clinical labs should include OxoG and FFPE artifact metrics in their QC pipeline and reject samples exceeding artifact thresholds.

## Residual Risk Justification
The multi-layer filtering approach (orientation bias + strand bias + contamination) effectively suppresses the most common artifact classes. Residual FDR ≤ 5 % is clinically acceptable and consistent with industry standards for somatic variant calling.
