---
itemId: SPEC-SSNV-002
itemType: Software Item Spec
itemFulfills: REQ-SSNV-002
---
# FilterMutectCalls Somatic Filtering Specification

## Overview

FilterMutectCalls applies a series of statistical filters to Mutect2 raw calls to remove likely artifacts and produce a high-confidence somatic call set.

## Behavior

### Filtering Models
- **Contamination filter**: uses cross-sample contamination estimate to down-weight variants consistent with contamination.
- **Orientation bias filter**: applies learned artifact priors from LearnReadOrientationModel to suppress OxoG (G→T) and FFPE (C→T) artifacts.
- **Normal artifact filter**: removes variants that appear at low frequency in the matched normal, consistent with normal sample artifacts rather than germline leakage.
- **Strand bias filter**: applies a symmetric odds ratio test for strand bias.
- **Mapping quality filter**: filters variants where supporting reads have abnormally low mapping quality.

### FILTER Tags
Each filter applies a named FILTER tag (e.g., `contamination`, `orientation_bias`, `normal_artifact`, `strand_bias`, `weak_evidence`). Variants passing all filters receive `PASS`.

### Threshold Calibration
- Default thresholds are calibrated for ≥ 90 % sensitivity at ≤ 5 % FDR for variants at ≥ 10 % VAF.
- Users may adjust `--max-contamination`, `--min-allele-fraction`, and other thresholds for specific use cases.

### FFPE Mode
- `--ffpe-mode` enables a stricter calibration tailored to formalin-fixed paraffin-embedded (FFPE) samples, where cytosine deamination produces a strand-biased C→T (and reverse-complement G→A) artifact signature that inflates the somatic false-positive rate under default thresholds.
- When enabled, the read-orientation artifact posterior is multiplied by `--ffpe-deamination-prior-boost` (default 3.0) **only** for C>T and G>A substitutions; all other substitution classes retain their stock calibration.
- The contamination estimate consumed by the contamination filter is multiplied by `--ffpe-contamination-boost` (default 1.5), biasing low-AF calls toward the contamination/artifact hypothesis.
- Both boosts are gated on `--ffpe-mode` and clamped to ≥ 1.0; the default invocation of `FilterMutectCalls` is unchanged.
- For best performance, supply `--orientation-bias-artifact-priors` learned from an FFPE-matched reference panel via `LearnReadOrientationModel`.
