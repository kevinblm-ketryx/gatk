---
itemId: REQ-GSNV-003
itemType: Requirement
---
# Germline Variant Filtering via CNN-Based Quality Score

The system shall provide a Convolutional Neural Network (CNN) based variant filtering mechanism that assigns quality scores to germline variant calls, enabling separation of true variants from artifacts without a separate training truth set at the project level.

## Acceptance Criteria

1. CNNScoreVariants shall accept a VCF of germline calls and output per-variant CNN quality scores.
2. The 1D model shall use only variant-level annotations; the 2D model shall additionally use read tensor data.
3. FilterVariantTranches shall accept CNN-scored VCFs and apply sensitivity tranches to partition variants into PASS/filter categories.
4. On Genome in a Bottle NA12878, filtered call sets shall achieve ≥ 99.0 % SNP sensitivity at ≤ 1.5 % FDR and ≥ 95.0 % indel sensitivity at ≤ 5.0 % FDR.
