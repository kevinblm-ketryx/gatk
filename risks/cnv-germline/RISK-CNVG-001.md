---
itemId: RISK-CNVG-001
itemType: Risk
itemIntroducesRisk: REQ-CNVG-001
itemIsRiskControlledBy: SPEC-CNVG-001, TC-CNVG-001
Initial severity: Major
Initial likelihood of occurrence (P1): Medium
Residual severity: Major
Residual likelihood of occurrence (P1): Low
Risk acceptability: ALARP
---
# Missed Germline CNVs Affecting Genetic Diagnosis

## Hazard
The germline CNV pipeline fails to detect pathogenic deletions or duplications (e.g., exon-level deletions in DMD, PMP22 duplications in CMT1A) due to insufficient interval resolution, inadequate cohort size, or poor denoising.

## Potential Harm
Patient with a clinically significant germline CNV receives a negative genetic test result, missing a diagnosis that could inform treatment, prognosis, or family counseling.

## Risk Controls
1. **Specification-level**: SPEC-CNVG-001 specifies a hierarchical HMM with negative binomial emission model and GC/mappability bias correction via annotated intervals, enabling detection of CNVs spanning ≥ 3 intervals. Cohort mode leverages cross-sample information for improved power.
2. **Verification-level**: TC-CNVG-001 validates sensitivity ≥ 90 % for events ≥ 10 kb on characterized 1000 Genomes samples, confirming detection of clinically relevant event sizes.
3. **Operational**: For clinical applications, labs should validate at exon-level resolution for their target gene panels and use orthogonal confirmation (MLPA, qPCR) for reportable findings.

## Residual Risk Justification
With cohort mode and adequate sample counts (≥ 30), the pipeline achieves strong sensitivity for multi-exon events. Single-exon deletions remain challenging and are mitigated by orthogonal confirmation. Risk is acceptable under ALARP.
