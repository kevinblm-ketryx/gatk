---
itemId: SPEC-GSNV-003
itemType: Software Item Spec
itemFulfills: REQ-GSNV-003
---
# CNN Variant Scoring and Filtering Specification

## Overview

CNNScoreVariants and FilterVariantTranches provide a machine-learning–based quality scoring and filtering pipeline for germline short variants.

## Behavior

### CNNScoreVariants
- Reads a VCF and the corresponding BAM/CRAM.
- **1D model**: uses site-level annotations (QD, FS, SOR, MQ, MQRankSum, ReadPosRankSum) as input features.
- **2D model**: additionally constructs a read-tensor image from the reads at the variant site for convolutional feature extraction.
- Outputs a VCF with an `INFO/CNN_1D` or `INFO/CNN_2D` annotation containing the quality score.

### FilterVariantTranches
- Accepts a CNN-scored VCF and known-sites resource VCFs (e.g., hapmap, 1000G omni).
- Partitions variants into sensitivity tranches (e.g., 99.0, 99.5, 99.9) based on the CNN score relative to the truth resource overlap.
- Applies FILTER tags (e.g., `CNN_1D_SNP_Tranche_99.00_99.50`) to variants not meeting the selected tranche threshold.

## Dependencies
- PyTorch runtime (bundled via GATK conda environment) for CNN inference.
- Pre-trained model weights shipped with the GATK resource bundle.
