---
itemId: RISK-FUNC-001
itemType: Risk
itemIntroducesRisk: REQ-FUNC-001
itemIsRiskControlledBy: SPEC-FUNC-001, TC-FUNC-001
Initial severity: Major
Initial likelihood of occurrence (P1): Low
Residual severity: Major
Residual likelihood of occurrence (P1): Rare
Risk acceptability: Acceptable
---
# Incorrect Functional Annotation Leading to Variant Misinterpretation

## Hazard
Funcotator assigns an incorrect variant classification (e.g., classifying a missense variant as silent, or missing a splice-site effect) due to outdated transcript models, incorrect transcript selection, or annotation logic errors.

## Potential Harm
Clinicians or variant curation teams misinterpret the functional impact of a variant, potentially leading to incorrect pathogenicity classification and inappropriate clinical action (e.g., missing a loss-of-function variant).

## Risk Controls
1. **Specification-level**: SPEC-FUNC-001 defines a rigorous transcript selection hierarchy (canonical → longest CDS → Appris) and explicit variant classification rules with established splice-site distance thresholds.
2. **Verification-level**: TC-FUNC-001 validates annotation accuracy against ≥ 50 curated variants with known functional consequences, achieving ≥ 99 % concordance.
3. **Operational**: Clinical labs should use current GENCODE data sources, verify critical annotations against independent databases (e.g., VEP, SnpEff), and apply ACMG/AMP guidelines that consider multiple evidence sources beyond tool annotations.

## Residual Risk Justification
With current transcript models and validated classification logic, annotation errors are rare. The clinical workflow requirement for multi-source evidence review provides an additional safety net. Risk is acceptable.
