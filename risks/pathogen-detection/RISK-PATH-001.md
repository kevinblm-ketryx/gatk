---
itemId: RISK-PATH-001
itemType: Risk
itemIntroducesRisk: REQ-PATH-001
itemIsRiskControlledBy: SPEC-PATH-001, TC-PATH-001
Initial severity: Critical
Initial likelihood of occurrence (P1): Medium
Residual severity: Critical
Residual likelihood of occurrence (P1): Low
Risk acceptability: ALARP
---
# Missed Pathogen Detection Due to Incomplete Reference Database or Host Subtraction Errors

## Hazard
PathSeq fails to detect a clinically significant pathogen because: (a) the organism is absent from the microbial reference database, (b) microbial reads are incorrectly classified as host and removed during subtraction, or (c) novel or highly divergent strains do not align to reference sequences.

## Potential Harm
Patient with an active infection receives a false-negative pathogen detection result, potentially delaying appropriate antimicrobial therapy and leading to disease progression or transmission.

## Risk Controls
1. **Specification-level**: SPEC-PATH-001 uses a comprehensive microbial reference database covering bacteria, viruses, fungi, and parasites. The two-stage host subtraction (BWA alignment + k-mer filtering) is tuned to minimize false removal of microbial reads. LCA assignment handles reads from novel strains by classifying at higher taxonomic levels.
2. **Verification-level**: TC-PATH-001 validates detection of 5 diverse organisms (gram-positive, gram-negative, mycobacterial, viral, fungal) at multiple abundance levels, confirming sensitivity ≥ 90 % at ≥ 100 reads and host read contamination < 1 %.
3. **Operational**: Clinical metagenomics labs should maintain up-to-date reference databases, validate against clinically relevant organisms for their patient population, and use orthogonal testing (culture, PCR) for critical diagnoses.

## Residual Risk Justification
With comprehensive reference databases and validated sensitivity, the pipeline detects most clinically relevant pathogens. Novel or highly divergent organisms remain a residual risk inherent to reference-based classification, mitigated by LCA assignment and orthogonal testing. Risk is acceptable under ALARP for a sequencing-based adjunct diagnostic tool.
