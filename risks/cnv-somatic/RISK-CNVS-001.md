---
itemId: RISK-CNVS-001
itemType: Risk
itemIntroducesRisk: REQ-CNVS-001
itemIsRiskControlledBy: SPEC-CNVS-001, TC-CNVS-001
Initial severity: Major
Initial likelihood of occurrence (P1): Medium
Residual severity: Major
Residual likelihood of occurrence (P1): Low
Risk acceptability: ALARP
---
# Undetected Somatic Copy Number Alterations Affecting Treatment Decisions

## Hazard
The somatic CNV pipeline fails to detect clinically relevant copy number alterations (e.g., ERBB2 amplification in breast cancer) due to insufficient denoising, low tumor purity, or inadequate panel of normals.

## Potential Harm
Patient with an actionable CNV (e.g., HER2 amplification eligible for trastuzumab) is not identified, resulting in missed targeted therapy and potentially worse clinical outcome.

## Risk Controls
1. **Specification-level**: SPEC-CNVS-001 specifies SVD-based denoising against a PoN of ≥ 30 matched-protocol normals, effectively removing systematic biases that could mask true CNVs. GC-bias correction is implicitly handled.
2. **Verification-level**: TC-CNVS-001 validates detection of known amplifications and deletions on a characterized cell line, confirming pipeline sensitivity for focal events ≥ 10 kb.
3. **Operational**: Clinical labs should validate CNV calling at the minimum tumor purity for their assay and use orthogonal methods (FISH, MLPA) for confirmation of clinically actionable findings.

## Residual Risk Justification
With adequate PoN quality and tumor purity ≥ 20 %, the pipeline reliably detects clinically significant CNVs. Residual risk for low-purity samples is mitigated by orthogonal confirmation. Risk is acceptable under ALARP.
