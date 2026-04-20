---
itemId: TC-COVQ-001
itemType: Test Case
itemTests: SPEC-COVQ-001
---
# Coverage Metrics Accuracy on Reference Samples

## Preconditions
- NA12878 WGS BAM at known mean coverage (~30×).
- NA12878 WES BAM with known target regions (Agilent SureSelect v6 or equivalent).
- Bait and target interval files for the exome kit.
- GRCh38 reference genome.

## Test Steps

1. Collect WGS metrics:
   ```
   gatk CollectWgsMetrics -R GRCh38.fa -I NA12878_wgs.bam -O wgs_metrics.txt
   ```
2. Verify MEAN_COVERAGE is within ± 5 % of the expected value (~30×).
3. Verify PCT_10X, PCT_20X, PCT_30X are consistent with a ~30× Poisson distribution.
4. Collect exome metrics:
   ```
   gatk CollectHsMetrics -R GRCh38.fa -I NA12878_wes.bam \
     -BAIT_INTERVALS baits.interval_list -TARGET_INTERVALS targets.interval_list \
     -O hs_metrics.txt
   ```
5. Verify ON_TARGET_BASES > 0 and FOLD_ENRICHMENT > 10.
6. Run DepthOfCoverage on a 10 Mb interval and verify per-locus output.
7. Spot-check per-locus depth against `samtools depth` at 100 random positions.

## Expected Results

- WGS mean coverage matches expected value within ± 5 %.
- PCT_30X ≥ 80 % for a 30× WGS sample.
- Exome fold enrichment > 10× and on-target rate > 60 %.
- Per-locus depth from DepthOfCoverage matches samtools depth at spot-checked positions.
- All metrics files are valid Picard-format TSVs.
