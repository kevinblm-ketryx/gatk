---
itemId: REQ-RPROC-002
itemType: Requirement
---
# BAM/CRAM Sorting and Indexing

The system shall sort aligned reads by genomic coordinate and produce index files to enable efficient random access for downstream tools.

## Acceptance Criteria

1. SortSam shall sort BAM/CRAM files by coordinate or query name.
2. Coordinate-sorted output shall be accompanied by a BAI or CRAI index file when requested.
3. The sorting algorithm shall handle files larger than available memory using disk-backed temporary files.
4. BuildBamIndex shall produce a BAI index for any coordinate-sorted BAM file.
