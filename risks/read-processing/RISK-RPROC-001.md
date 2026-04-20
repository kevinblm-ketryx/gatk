---
itemId: RISK-RPROC-001
itemType: Risk
itemIntroducesRisk: REQ-RPROC-001
itemIsRiskControlledBy: SPEC-RPROC-001, TC-RPROC-001
Initial severity: Major
Initial likelihood of occurrence (P1): Low
Residual severity: Major
Residual likelihood of occurrence (P1): Rare
Risk acceptability: Acceptable
---
# Incorrect Duplicate Marking Biasing Variant Allele Frequencies

## Hazard
MarkDuplicates incorrectly flags unique reads as duplicates (or fails to flag true duplicates), distorting the allele depth at variant sites and biasing variant allele frequency estimates used by downstream callers.

## Potential Harm
Over-marking unique reads reduces apparent coverage and sensitivity for variant detection. Under-marking duplicates inflates allele counts, potentially causing false-positive variant calls or incorrect allele frequency estimates in somatic calling.

## Risk Controls
1. **Specification-level**: SPEC-RPROC-001 specifies a well-defined duplicate detection algorithm using 5' alignment position and orientation, with the highest base-quality representative retained. Optical duplicate detection separates PCR from sequencing artifacts.
2. **Verification-level**: TC-RPROC-001 validates duplication rates against independent tools and confirms read count preservation, ensuring no reads are lost or incorrectly added.
3. **Operational**: Libraries with excessive duplication (> 30 %) are flagged during QC, and labs should monitor duplicate rates as part of sequencing quality control.

## Residual Risk Justification
The duplicate detection algorithm is well-established and concordant with independent implementations. Read count preservation is verified. Residual risk of incorrect marking is rare and acceptable for standard sequencing libraries.
