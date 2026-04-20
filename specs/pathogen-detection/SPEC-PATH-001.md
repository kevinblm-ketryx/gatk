---
itemId: SPEC-PATH-001
itemType: Software Item Spec
itemFulfills: REQ-PATH-001
---
# PathSeq Microbial Detection Pipeline Specification

## Overview

PathSeq is a GATK Spark-based pipeline that identifies microbial sequences in human sequencing data through a subtract-then-classify approach: host reads are removed, and remaining reads are classified against a microbial reference database.

## Behavior

### Host Read Subtraction (PathSeqFilterSpark)
- Input BAM reads are first quality-filtered (minimum quality, minimum length, complexity filter to remove low-complexity sequences).
- Remaining reads are aligned against a host (human) BWA index and a host k-mer database.
- Reads matching the host reference (by alignment or k-mer match) are removed.
- Non-host reads are passed to the classification stage.
- The host k-mer database is a Bloom-filter–based structure for rapid k-mer membership testing.

### Microbial Classification (PathSeqBwaSpark)
- Non-host reads are aligned against a comprehensive microbial BWA index (containing bacterial, viral, fungal, and parasitic reference genomes).
- Reads are assigned to taxonomic nodes based on alignment hits.
- Ambiguous reads (mapping to multiple taxa) are assigned to the lowest common ancestor (LCA) in the taxonomy tree.

### Integrated Pipeline (PathSeqPipelineSpark)
- Combines filtering and classification in a single Spark job for efficiency.
- Supports distributed execution on Spark clusters for large-scale processing.
- Intermediate results (filtered reads) can optionally be written for QC inspection.

### Output
- BAM file of classified microbial reads with taxonomic assignments.
- Per-read taxonomic classification tags in BAM (YP:Z tag for taxon ID).
