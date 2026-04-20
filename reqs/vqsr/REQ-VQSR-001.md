---
itemId: REQ-VQSR-001
itemType: Requirement
---
# Variant Quality Score Recalibration Model Training

The system shall build an adaptive variant quality recalibration model using machine learning (Gaussian Mixture Model) trained on known truth and training variant resources, enabling site-level filtering that outperforms hard-threshold filters.

## Acceptance Criteria

1. VariantRecalibrator shall accept a VCF of called variants and one or more truth/training resource VCFs (e.g., HapMap, Omni, 1000G, dbSNP).
2. The model shall be trained separately for SNPs and indels using variant-level annotations (QD, FS, SOR, MQ, MQRankSum, ReadPosRankSum).
3. The Gaussian Mixture Model shall converge and produce a recalibration table and tranches plot.
4. The tool shall support allele-specific mode (AS_) for improved multi-allelic site handling.
