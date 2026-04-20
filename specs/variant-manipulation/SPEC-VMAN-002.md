---
itemId: SPEC-VMAN-002
itemType: Software Item Spec
itemFulfills: REQ-VMAN-002
---
# VCF Merging and Concatenation Specification

## Overview

MergeVcfs and GatherVcfs combine multiple VCF files into a single output, with MergeVcfs performing a full merge-sort and GatherVcfs providing fast concatenation for pre-sorted interval shards.

## Behavior

### MergeVcfs
- Reads all input VCFs, merges headers (combining INFO, FORMAT, FILTER, and contig lines), and writes variants in coordinate-sorted order.
- Input VCFs may overlap genomically; overlapping records are interleaved by position.
- Uses a priority-queue merge for efficient multi-way sorting.
- The output is coordinate-sorted and optionally indexed.

### GatherVcfs
- Designed for VCFs that represent non-overlapping, sorted intervals (e.g., output of parallel HaplotypeCaller runs on different chromosomes).
- Performs simple concatenation without re-sorting: headers are merged and variant records are written sequentially.
- Much faster than MergeVcfs when input ordering guarantees are met.
- Validates that input intervals do not overlap (when `--ENFORCE_ORDERING` is true).

### Header Merging
- INFO, FORMAT, and FILTER definitions from all inputs are combined.
- Conflicting definitions (same ID, different type/description) emit a warning; the first definition is retained.
- Sample columns are merged or validated for consistency.

### Output
- Single coordinate-sorted VCF with combined header and all variant records.
- Optional tabix index (.tbi) when writing compressed VCF (VCF.GZ).
