---
itemId: TC-CNVG-001
itemType: Test Case
itemTests: SPEC-CNVG-001
---
# Germline CNV Detection on a Characterized Cohort

## Preconditions
- WGS BAMs for ≥ 30 samples from the 1000 Genomes Project with published CNV truth sets.
- GRCh38 reference genome and preprocessed/annotated/filtered interval list.
- Known germline CNVs from the 1000 Genomes structural variant call set.

## Test Steps

1. Determine contig ploidy:
   ```
   gatk DetermineGermlineContigPloidy -I sample1_counts.hdf5 -I sample2_counts.hdf5 ... \
     --contig-ploidy-priors contig_ploidy_priors.tsv -O ploidy_model/
   ```
2. Run GermlineCNVCaller in COHORT mode:
   ```
   gatk GermlineCNVCaller --run-mode COHORT -I sample1_counts.hdf5 -I sample2_counts.hdf5 ... \
     --contig-ploidy-calls ploidy_model/calls/ --interval-list filtered_intervals.interval_list \
     -O cohort_cnv_model/
   ```
3. Verify model convergence (ELBO converges, no divergence warnings).
4. Compare detected CNV intervals to the 1000 Genomes truth set.
5. Check sex chromosome ploidy assignments for correctness.

## Expected Results

- Contig ploidy correctly identifies XX vs. XY samples.
- CNV events ≥ 10 kb spanning ≥ 3 intervals are detected with sensitivity ≥ 90 %.
- Model ELBO converges within the configured number of iterations.
- False discovery rate ≤ 10 % at the default quality threshold.
