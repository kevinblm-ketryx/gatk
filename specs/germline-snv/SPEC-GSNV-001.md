---
itemId: SPEC-GSNV-001
itemType: Software Item Spec
itemFulfills: REQ-GSNV-001
---
# HaplotypeCaller Variant Calling Specification

## Overview

HaplotypeCaller implements a local de novo assembly–based variant calling algorithm. It operates on aligned reads (BAM/CRAM) against a reference genome to produce germline variant calls.

## Behavior

### Active Region Detection
- The engine scans the genome for regions showing evidence of variation (mismatches, insertions, deletions, soft clips) relative to the reference.
- Regions exceeding the activity threshold are marked as active regions and padded by a configurable extension window (default: 100 bp).

### Local De Novo Assembly
- Within each active region, reads are assembled into candidate haplotypes using a De Bruijn graph assembler.
- Graph construction uses multiple kmer sizes (10, 25) to resolve complex variants; dangling head and tail recovery merges partial paths.

### Pair-HMM Likelihood Calculation
- Each read is aligned against each candidate haplotype using a Pair Hidden Markov Model (PairHMM).
- Hardware-accelerated PairHMM (AVX, FPGA via Intel GKL) is used when available.

### Genotyping
- Per-read likelihoods are marginalized over haplotypes to derive per-sample genotype likelihoods.
- Genotypes are assigned using Bayesian inference with configurable heterozygosity priors (default: 0.001 for SNPs, 1.25 × 10⁻⁴ for indels).

### Output Modes
- **VCF mode**: emits only variant sites.
- **GVCF mode** (`-ERC GVCF`): emits variant sites and reference-confidence blocks with `<NON_REF>` alleles.

## Configurable Parameters
| Parameter | Default | Description |
|---|---|---|
| `--min-base-quality-score` | 10 | Minimum base quality for active-region evidence |
| `--standard-min-confidence-threshold-for-calling` | 30.0 | Phred-scaled confidence threshold |
| `--max-alternate-alleles` | 6 | Maximum alt alleles per site |
