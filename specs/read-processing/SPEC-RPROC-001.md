---
itemId: SPEC-RPROC-001
itemType: Software Item Spec
itemFulfills: REQ-RPROC-001
---
# MarkDuplicates Specification

## Overview

MarkDuplicates identifies duplicate reads from aligned BAM/CRAM files by comparing read alignment signatures and flags duplicates in the output, retaining the highest-quality representative from each duplicate set.

## Behavior

### Duplicate Detection Algorithm
- For each read (or read pair), a signature is computed from:
  - **Single-end reads**: 5' unclipped alignment position and orientation.
  - **Paired-end reads**: 5' unclipped alignment positions of both mates, plus their orientations.
- Reads sharing the same signature are grouped as duplicates.
- The representative read (not flagged as duplicate) is the one with the highest total base quality sum.

### Optical Duplicate Detection
- Optical duplicates are a subset of duplicates originating from the same cluster on the flow cell.
- Identified using tile, x-coordinate, and y-coordinate from the read name (Illumina format).
- Configurable pixel distance threshold (`--OPTICAL_DUPLICATE_PIXEL_DISTANCE`): 100 for non-patterned, 2500 for patterned flow cells.
- Optical duplicates are reported separately in metrics.

### Spark Implementation
- MarkDuplicatesSpark provides a distributed implementation for large-scale processing.
- Uses the same duplicate-detection algorithm but partitions reads by genomic position for parallel processing.

### Output
- BAM/CRAM with duplicate flag (0x400) set on duplicate reads.
- Metrics file: LIBRARY, UNPAIRED_READS_EXAMINED, PAIRED_READS_EXAMINED, UNMAPPED_READS, UNPAIRED_READ_DUPLICATES, PAIRED_READ_DUPLICATES, READ_PAIR_OPTICAL_DUPLICATES, PERCENT_DUPLICATION, ESTIMATED_LIBRARY_SIZE.

## Parameters
| Parameter | Default | Description |
|---|---|---|
| `--REMOVE_DUPLICATES` | false | Remove duplicates instead of flagging |
| `--OPTICAL_DUPLICATE_PIXEL_DISTANCE` | 100 | Pixel distance for optical duplicate detection |
| `--TAGGING_POLICY` | DontTag | Tag duplicates with the duplicate type (DT tag) |
