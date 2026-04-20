---
itemId: TC-COVQ-002
itemType: Test Case
itemTests: SPEC-COVQ-002
---
# Sample QC Metrics Validation

## Preconditions
- NA12878 WGS BAM (high-quality library, expected alignment rate > 99 %).
- A deliberately degraded BAM (low mapping quality, high duplicate rate) for negative control.
- GRCh38 reference genome.

## Test Steps

1. Collect alignment summary metrics:
   ```
   gatk CollectAlignmentSummaryMetrics -R GRCh38.fa -I NA12878.bam -O alignment_metrics.txt
   ```
2. Verify PCT_PF_READS_ALIGNED > 99 % for the high-quality sample.
3. Verify PF_MISMATCH_RATE < 1 % and PCT_CHIMERAS < 2 %.
4. Collect insert-size metrics:
   ```
   gatk CollectInsertSizeMetrics -I NA12878.bam -O insert_metrics.txt -H insert_hist.pdf
   ```
5. Verify MEDIAN_INSERT_SIZE is within the expected range (300–500 bp for standard WGS libraries).
6. Verify insert-size distribution is unimodal (no secondary peaks).
7. Collect GC-bias metrics:
   ```
   gatk CollectGcBiasMetrics -R GRCh38.fa -I NA12878.bam -O gc_metrics.txt -CHART gc_chart.pdf -S gc_summary.txt
   ```
8. Verify AT_DROPOUT and GC_DROPOUT are < 5 % for the high-quality sample.
9. Run all metrics on the degraded BAM and verify lower quality scores.

## Expected Results

- High-quality sample: alignment rate > 99 %, mismatch rate < 1 %, chimera rate < 2 %.
- Insert-size distribution is unimodal with SD < 100 bp.
- GC bias is minimal: AT_DROPOUT < 5 %, GC_DROPOUT < 5 %.
- Degraded sample shows measurably worse metrics across all categories.
- All metrics files parse correctly and contain expected column headers.
