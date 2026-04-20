---
itemId: SPEC-PATH-002
itemType: Software Item Spec
itemFulfills: REQ-PATH-002
---
# PathSeq Scoring and Reporting Specification

## Overview

PathSeqScoreSpark aggregates per-read taxonomic classifications into per-taxon abundance scores, normalized by genome size to enable meaningful cross-taxon and cross-sample comparisons.

## Behavior

### Read Aggregation
- Classified reads are grouped by their assigned taxon (species-level or LCA assignment).
- For each taxon, the following counts are computed:
  - **Total reads**: all reads assigned to this taxon or its descendants.
  - **Unique reads**: reads mapping unambiguously to this taxon only.
  - **Ambiguous reads**: reads assigned via LCA (shared with sibling taxa).

### Abundance Normalization
- Raw read counts are normalized by the reference genome size for each taxon to compute a score proportional to organism abundance.
- Score = (read count / genome size in Mb) × (1,000,000 / total classified reads).
- This double normalization accounts for both genome size bias and sequencing depth differences.

### Taxonomic Rollup
- Scores are computed at species level and rolled up through the taxonomic hierarchy (genus, family, order, class, phylum, kingdom).
- Parent-level scores are the sum of child-level scores.

### Report Generation
- Output TSV with columns: tax_id, taxonomy, type (species/genus/family), kingdom, score, score_normalized, reads, unambiguous_reads.
- Rows are sorted by descending score within each kingdom.
- A header line includes the total number of classified and unclassified reads.

### Output
- Per-taxon abundance report (TSV).
- Summary statistics: total reads processed, host reads removed, classified reads, unclassified reads.
