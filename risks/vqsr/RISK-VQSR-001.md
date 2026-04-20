---
itemId: RISK-VQSR-001
itemType: Risk
itemIntroducesRisk: REQ-VQSR-001
itemIsRiskControlledBy: SPEC-VQSR-001, TC-VQSR-001
Initial severity: Major
Initial likelihood of occurrence (P1): Medium
Residual severity: Major
Residual likelihood of occurrence (P1): Low
Risk acceptability: ALARP
---
# VQSR Model Mis-Training on Small or Biased Cohorts

## Hazard
VariantRecalibrator produces a poorly calibrated model when trained on a cohort that is too small (< 30 WGS samples) or biased toward a specific population, leading to incorrect VQSLOD scores and inappropriate filtering.

## Potential Harm
Over-filtering removes true variants (false negatives), potentially missing clinically relevant mutations. Under-filtering retains artifacts (false positives), leading to incorrect diagnostic conclusions or wasted follow-up testing.

## Risk Controls
1. **Specification-level**: SPEC-VQSR-001 specifies the use of well-characterized truth/training resources (HapMap, Omni) with calibrated prior probabilities, constraining the model even with limited cohort data. The GMM uses configurable Gaussian counts to prevent overfitting.
2. **Verification-level**: TC-VQSR-001 validates model convergence and tranche monotonicity, detecting mis-training before scores are applied.
3. **Operational**: GATK Best Practices recommend VQSR only for cohorts ≥ 30 WGS or ≥ 50 WES samples, with CNN-based filtering (CNNScoreVariants) as the alternative for small cohorts.

## Residual Risk Justification
When cohort size requirements are met and training resources are correctly configured, VQSR produces well-calibrated models. For small cohorts, the documented fallback to CNN filtering mitigates the risk. Residual risk is acceptable under ALARP.
