---
itemId: RISK-COVQ-001
itemType: Risk
itemIntroducesRisk: REQ-COVQ-001
itemIsRiskControlledBy: SPEC-COVQ-001, TC-COVQ-001
Initial severity: Major
Initial likelihood of occurrence (P1): Low
Residual severity: Major
Residual likelihood of occurrence (P1): Rare
Risk acceptability: Acceptable
---
# Inaccurate Coverage Metrics Leading to False Confidence in Data Sufficiency

## Hazard
Coverage analysis tools report inflated depth metrics (e.g., counting duplicate or low-quality reads) or miscalculate the fraction of callable bases, giving false confidence that a sample has sufficient coverage for reliable variant calling.

## Potential Harm
A sample with genuinely insufficient coverage at critical regions proceeds to variant calling, producing unreliable results with elevated false-negative rates. Clinical decisions are made on data that does not meet minimum quality standards.

## Risk Controls
1. **Specification-level**: SPEC-COVQ-001 mandates filtering of duplicate reads, low-quality reads (MAPQ < 20, BQ < 20), and vendor-failed reads by default, ensuring coverage metrics reflect usable data. Exclusion fractions are explicitly reported.
2. **Verification-level**: TC-COVQ-001 validates coverage metrics against expected values for characterized reference samples and cross-checks per-locus depth against an independent tool (samtools depth).
3. **Operational**: Labs should set minimum coverage thresholds (e.g., ≥ 30× mean for WGS, ≥ 50× for WES) and reject samples below threshold before variant calling.

## Residual Risk Justification
With default quality filtering and independent validation, coverage metrics accurately reflect usable sequencing depth. Explicit reporting of exclusion fractions enables transparent QC. Risk is acceptable.
