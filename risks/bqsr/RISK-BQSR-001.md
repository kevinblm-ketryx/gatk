---
itemId: RISK-BQSR-001
itemType: Risk
itemIntroducesRisk: REQ-BQSR-001
itemIsRiskControlledBy: SPEC-BQSR-001, TC-BQSR-001
Initial severity: Major
Initial likelihood of occurrence (P1): Low
Residual severity: Major
Residual likelihood of occurrence (P1): Rare
Risk acceptability: Acceptable
---
# Incorrect Recalibration Model Leading to Biased Quality Scores

## Hazard
BaseRecalibrator produces an inaccurate error model due to incomplete known-sites masking or insufficient data in covariate bins, resulting in over- or under-corrected base quality scores.

## Potential Harm
Biased quality scores propagate to variant callers, causing either inflated confidence in false-positive calls (over-correction) or reduced sensitivity for true variants (under-correction), ultimately affecting diagnostic accuracy.

## Risk Controls
1. **Specification-level**: SPEC-BQSR-001 mandates Bayesian smoothing priors on covariate bins and known-site masking using established databases (dbSNP, Mills), preventing model overfitting and variant contamination of the error model.
2. **Verification-level**: TC-BQSR-001 validates the model by running a second-pass recalibration to confirm residuals are ≤ 1 Phred point, demonstrating model accuracy.
3. **Operational**: Standard BQSR practice uses comprehensive known-sites databases and ≥ 30× coverage, providing sufficient data for accurate covariate estimation.

## Residual Risk Justification
With established known-sites databases and adequate coverage, the recalibration model converges reliably. The two-pass validation confirms accuracy. Residual risk is acceptable for standard WGS/WES applications.
