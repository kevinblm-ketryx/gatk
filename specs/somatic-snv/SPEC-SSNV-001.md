---
itemId: SPEC-SSNV-001
itemType: Software Item Spec
itemFulfills: REQ-SSNV-001
---
# Mutect2 Somatic Variant Calling Specification

## Overview

Mutect2 is a somatic variant caller that identifies SNVs and indels by comparing tumor reads to a reference (and optionally a matched normal) using local assembly and a somatic genotyping model.

## Behavior

### Active Region Detection
- Similar to HaplotypeCaller, Mutect2 detects active regions by scanning for evidence of variation. The activity profile uses a somatic-tuned sensitivity threshold.

### Local Assembly
- Within active regions, reads from tumor (and normal, if provided) are assembled into candidate haplotypes using a De Bruijn graph assembler.

### Somatic Genotyping Model
- Unlike HaplotypeCaller, Mutect2 does not assume Hardy-Weinberg priors. It models somatic allele fractions as continuous values.
- Log-odds (TLOD) scores are computed for each candidate somatic allele against the null hypothesis of no somatic variant.
- Normal log-odds (NLOD) scores quantify the evidence that the variant is absent in the matched normal.

### Panel of Normals
- A PoN VCF, created by running Mutect2 on a set of normal samples and merging with CreateSomaticPanelOfNormals, is used to flag recurrent artifacts.

### Output
- VCF with somatic calls, TLOD/NLOD annotations, F1R2 counts, and per-sample allele depth (AD) fields.
- Mutect2 stats file (`.stats`) for downstream filtering.

## Key Parameters
| Parameter | Default | Description |
|---|---|---|
| `--tumor-sample` | (required) | Tumor sample name |
| `--normal-sample` | (optional) | Matched normal sample name |
| `--panel-of-normals` | none | PoN VCF for artifact suppression |
| `--af-of-alleles-not-in-resource` | 5.0 × 10⁻⁶ | Prior allele frequency for unlisted alleles |
