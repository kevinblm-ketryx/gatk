---
itemId: REQ-BQSR-002
itemType: Requirement
---
# Application of Recalibrated Base Quality Scores

The system shall apply the recalibration model to aligned reads, producing output with corrected base quality scores that more accurately reflect the true probability of sequencing error at each base.

## Acceptance Criteria

1. ApplyBQSR shall accept an aligned BAM/CRAM and a recalibration table from BaseRecalibrator and produce a recalibrated BAM/CRAM.
2. Recalibrated quality scores shall reduce the root-mean-square error (RMSE) between reported and empirical quality scores by ≥ 50 % compared to the original scores.
3. The recalibrated output shall preserve all read information (alignments, tags, mate pairs) except for the modified base quality scores.
4. AnalyzeCovariates shall produce diagnostic plots comparing pre- and post-recalibration quality score distributions.
