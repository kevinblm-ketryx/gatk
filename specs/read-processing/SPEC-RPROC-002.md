---
itemId: SPEC-RPROC-002
itemType: Software Item Spec
itemFulfills: REQ-RPROC-002
---
# BAM/CRAM Sorting and Indexing Specification

## Overview

SortSam and BuildBamIndex provide coordinate and query-name sorting of aligned reads and BAI/CRAI index generation for efficient random access.

## Behavior

### SortSam
- Accepts BAM/CRAM input and sorts reads by:
  - **Coordinate**: reference index, then alignment start position. Unmapped reads are placed after all mapped reads.
  - **Queryname**: lexicographic ordering of read names.
- Uses an external merge-sort algorithm: reads are sorted in memory up to `MAX_RECORDS_IN_RAM` (default: 500,000), then spilled to temporary disk files.
- Temporary files are merged in a multi-way merge to produce the final sorted output.
- For coordinate-sorted output, the `SO:coordinate` header tag is set.

### BuildBamIndex
- Reads a coordinate-sorted BAM file and produces a BAI index.
- The index enables efficient retrieval of reads overlapping any genomic interval.
- CRAM files use the CRAI index format, generated automatically during CRAM writing.

### Output
- Sorted BAM/CRAM file with appropriate header sort-order tag.
- BAI index (for BAM) or CRAI index (for CRAM) file co-located with the output.
