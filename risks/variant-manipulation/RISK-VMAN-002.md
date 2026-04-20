---
itemId: RISK-VMAN-002
itemType: Risk
itemIntroducesRisk: REQ-VMAN-003
itemIsRiskControlledBy: SPEC-VMAN-003, TC-VMAN-003
Initial severity: Major
Initial likelihood of occurrence (P1): Low
Residual severity: Major
Residual likelihood of occurrence (P1): Rare
Risk acceptability: Acceptable
---
# Incorrect Variant Normalization Altering Biological Meaning

## Hazard
LeftAlignAndTrimVariants incorrectly normalizes a variant such that its genomic position or allele representation no longer corresponds to the original biological variant, causing mismatches in downstream database lookups or variant concordance analyses.

## Potential Harm
A normalized variant fails to match its entry in ClinVar or other clinical databases, leading to missed annotation of pathogenicity. Alternatively, two distinct variants are collapsed into the same representation, causing loss of variant resolution.

## Risk Controls
1. **Specification-level**: SPEC-VMAN-003 specifies left-alignment following the VCF specification with explicit invariant preservation guarantees. Multi-allelic splitting maintains allele-specific annotation integrity.
2. **Verification-level**: TC-VMAN-003 validates normalization correctness against an independent implementation (bcftools norm) and verifies biological identity preservation for curated variant sets.
3. **Operational**: Labs should compare normalized output against database representations and use consistent normalization across all stages of their pipeline.

## Residual Risk Justification
The normalization algorithm follows VCF specification conventions and is validated against an independent implementation. Biological meaning preservation is explicitly verified. Risk is acceptable.
