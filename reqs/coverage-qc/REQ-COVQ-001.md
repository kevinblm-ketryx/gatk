---
itemId: REQ-COVQ-001
itemType: Requirement
---
# Sequencing Coverage and Depth Analysis

The system shall compute per-base and per-interval coverage depth metrics from aligned sequencing data to assess data quality and sufficiency for downstream variant calling.

## Acceptance Criteria

1. CollectWgsMetrics shall compute genome-wide coverage statistics including mean coverage, median coverage, and the fraction of bases at various depth thresholds (≥ 10×, ≥ 20×, ≥ 30×).
2. DepthOfCoverage shall produce per-locus, per-interval, and per-sample coverage summaries with configurable depth thresholds.
3. CollectHsMetrics shall compute hybrid-selection (exome capture) metrics including on-target rate, fold enrichment, and per-target mean coverage.
4. Coverage tools shall support BAM and CRAM input formats with configurable minimum base quality and mapping quality thresholds.
