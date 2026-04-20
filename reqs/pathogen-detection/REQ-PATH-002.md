---
itemId: REQ-PATH-002
itemType: Requirement
---
# Microbial Abundance Scoring and Reporting

The system shall compute normalized abundance scores for detected microorganisms to enable comparison across samples and distinguish true infections from background contamination.

## Acceptance Criteria

1. PathSeqScoreSpark shall compute per-taxon abundance scores normalized by genome size and total classified reads.
2. Scores shall be reported at multiple taxonomic levels (species, genus, family) in a hierarchical format.
3. The tool shall distinguish between uniquely mapped reads and ambiguously mapped reads in the abundance calculation.
4. Output shall include a TSV report suitable for clinical review with columns for taxon name, taxonomic ID, read count, unique read count, and normalized score.
