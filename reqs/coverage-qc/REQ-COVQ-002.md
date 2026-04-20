---
itemId: REQ-COVQ-002
itemType: Requirement
---
# Sample-Level Quality Control Metrics

The system shall compute comprehensive sample-level quality control metrics to identify samples with insufficient data quality for reliable variant calling.

## Acceptance Criteria

1. CollectAlignmentSummaryMetrics shall report alignment rate, mismatch rate, indel rate, and strand balance per read group.
2. CollectInsertSizeMetrics shall compute insert-size distribution statistics (mean, standard deviation, median, mode) for paired-end data.
3. CollectGcBiasMetrics shall report GC-bias curves showing normalized coverage as a function of GC content.
4. All QC tools shall produce both summary metrics files and histogram data suitable for plotting.
5. Samples failing configurable QC thresholds (e.g., alignment rate < 95 %, mean coverage < 20×) shall be identifiable from the metrics output.
