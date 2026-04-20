---
itemId: RISK-VMAN-001
itemType: Risk
itemIntroducesRisk: REQ-VMAN-001
itemIsRiskControlledBy: SPEC-VMAN-001, TC-VMAN-001
Initial severity: Major
Initial likelihood of occurrence (P1): Low
Residual severity: Major
Residual likelihood of occurrence (P1): Rare
Risk acceptability: Acceptable
---
# Unintended Variant Loss During VCF Selection

## Hazard
SelectVariants inadvertently removes clinically relevant variants due to incorrect JEXL expression syntax, unintended interaction between multiple selection criteria, or incorrect allele count recalculation after sample subsetting.

## Potential Harm
Clinically actionable variants are silently dropped from the analysis, potentially causing missed diagnoses or incorrect population frequency estimates in research databases.

## Risk Controls
1. **Specification-level**: SPEC-VMAN-001 defines explicit selection semantics and annotation recalculation behavior, ensuring predictable subsetting outcomes. Unused allele removal is opt-in (`--remove-unused-alternates`).
2. **Verification-level**: TC-VMAN-001 validates variant counts and annotation consistency across multiple selection modes (type, sample, JEXL, interval), confirming no unintended variant loss.
3. **Operational**: Users should compare variant counts before and after selection and validate critical variants are retained using independent lookups.

## Residual Risk Justification
With well-defined selection semantics and count validation, unintended variant loss is rare. The opt-in nature of allele removal provides additional safety. Risk is acceptable.
