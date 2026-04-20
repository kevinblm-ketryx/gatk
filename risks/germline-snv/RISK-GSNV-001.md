---
itemId: RISK-GSNV-001
itemType: Risk
itemIntroducesRisk: REQ-GSNV-001
itemIsRiskControlledBy: SPEC-GSNV-001, TC-GSNV-001
Initial severity: Critical
Initial likelihood of occurrence (P1): Low
Residual severity: Critical
Residual likelihood of occurrence (P1): Rare
Risk acceptability: ALARP
---
# False-Negative Germline Variant Calls Leading to Missed Diagnoses

## Hazard
HaplotypeCaller fails to call a clinically significant germline variant (e.g., pathogenic SNP in BRCA1/2) due to low coverage, mapping artifacts, or active-region detection failure.

## Potential Harm
Patient receives a false-negative genetic test result, delaying diagnosis or resulting in absence of indicated clinical intervention (e.g., prophylactic surgery, targeted therapy).

## Risk Controls
1. **Specification-level**: HaplotypeCaller uses local de novo assembly and PairHMM to recover variants even in regions with mapping complexity. Configurable minimum-confidence and base-quality thresholds allow sensitivity tuning.
2. **Verification-level**: TC-GSNV-001 validates sensitivity ≥ 99.0 % for SNPs and ≥ 95.0 % for indels on the GIAB truth set, ensuring population-level sensitivity targets are met.
3. **Operational**: Clinical labs apply minimum coverage requirements (≥ 30×) and use established truth sets for periodic revalidation.

## Residual Risk Justification
After risk controls, the residual probability of a clinically significant false negative in a well-covered region is rare. Residual risk is acceptable under ALARP because further mitigation (e.g., redundant callers) adds operational complexity without proportionate risk reduction for high-coverage data.
