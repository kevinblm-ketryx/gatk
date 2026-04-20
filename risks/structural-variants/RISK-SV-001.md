---
itemId: RISK-SV-001
itemType: Risk
itemIntroducesRisk: REQ-SV-001
itemIsRiskControlledBy: SPEC-SV-001, TC-SV-001
Initial severity: Major
Initial likelihood of occurrence (P1): Medium
Residual severity: Major
Residual likelihood of occurrence (P1): Low
Risk acceptability: ALARP
---
# Missed Structural Variants in Repetitive Genomic Regions

## Hazard
The SV discovery pipeline fails to detect structural variants in repetitive regions (segmental duplications, centromeric regions, mobile element insertions) where short-read mapping is ambiguous, leading to incomplete SV ascertainment.

## Potential Harm
Clinically relevant SVs (e.g., gene fusions, large deletions encompassing tumor suppressors) in repeat-rich regions are missed, potentially affecting cancer diagnosis, pharmacogenomics, or rare-disease diagnosis.

## Risk Controls
1. **Specification-level**: SPEC-SV-001 mandates a multi-evidence ensemble approach (split reads + discordant pairs + read depth + BAF), providing redundant detection signals even when one evidence type is weakened by repeats.
2. **Verification-level**: TC-SV-001 evaluates sensitivity on the GIAB HG002 truth set which includes SVs in moderately repetitive regions, confirming ≥ 85 % sensitivity for events ≥ 300 bp.
3. **Operational**: For clinical applications in highly repetitive regions, long-read sequencing or targeted assays may supplement short-read SV calling.

## Residual Risk Justification
The multi-evidence approach recovers most SVs in moderately repetitive regions. Truly unmappable regions (e.g., centromeres) remain challenging with short reads, but these regions are excluded from clinical reporting panels. Risk is acceptable under ALARP for standard short-read WGS applications.
