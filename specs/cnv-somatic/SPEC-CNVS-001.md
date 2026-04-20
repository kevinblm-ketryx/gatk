---
itemId: SPEC-CNVS-001
itemType: Software Item Spec
itemFulfills: REQ-CNVS-001
---
# Somatic CNV Detection Pipeline Specification

## Overview

The somatic CNV pipeline uses read-depth analysis with panel-of-normals denoising to detect copy number alterations in tumor samples.

## Behavior

### CollectReadCounts
- Reads are counted in predefined genomic intervals (bins), typically 1000 bp for WGS or exon-level for WES.
- Output is an HDF5 file containing per-interval read counts.
- Intervals are preprocessed using PreprocessIntervals to handle padding and bin merging.

### CreateReadCountPanelOfNormals
- Normal sample read counts are combined into a panel of normals using singular value decomposition (SVD).
- The PoN captures systematic coverage biases (GC content, mappability, batch effects) as principal components.
- Recommended PoN size: ≥ 30 samples from the same sequencing protocol.

### DenoiseReadCounts
- Tumor read counts are projected onto the PoN principal components, and the systematic bias is subtracted.
- Output: denoised copy ratios (log₂ scale) and standardized copy ratios per interval.
- GC-bias correction is implicitly handled by the PoN subtraction.

### Output
- Denoised copy ratios in TSV format with columns: contig, start, end, log2_copy_ratio.
- Standardized copy ratios for quality assessment.
