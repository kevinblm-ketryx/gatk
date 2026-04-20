---
itemId: SPEC-RPROC-003
itemType: Software Item Spec
itemFulfills: REQ-RPROC-003
---
# Read Group Management and BAM Merging Specification

## Overview

AddOrReplaceReadGroups, MergeSamFiles, and ValidateSamFile manage read group metadata and file integrity for multi-sample and multi-lane sequencing workflows.

## Behavior

### AddOrReplaceReadGroups
- Sets or replaces the @RG header line and RG:Z tag on all reads.
- Required read group fields: RGID (identifier), RGLB (library), RGPL (platform), RGPU (platform unit), RGSM (sample name).
- All reads in the output carry the specified RG tag, replacing any pre-existing value.

### MergeSamFiles
- Merges multiple BAM/CRAM files into a single file.
- Combines @RG, @PG, and @CO header lines from all inputs, deduplicating identical entries.
- If inputs are coordinate-sorted, the output is coordinate-sorted via a merge-sort on alignment positions.
- If `MERGE_SEQUENCE_DICTIONARIES` is true, sequence dictionaries are merged (must be compatible).

### ValidateSamFile
- Validates a BAM/CRAM against the SAM specification.
- Checks include: header completeness, read group presence, mate-pair consistency (RNEXT, PNEXT, TLEN), CIGAR validity, base quality encoding, and sort-order integrity.
- Errors are classified by severity (ERROR, WARNING) and reported in a summary or verbose format.
- `--MODE SUMMARY` provides error counts; `--MODE VERBOSE` reports each individual error.

### Output
- AddOrReplaceReadGroups: BAM/CRAM with updated read groups.
- MergeSamFiles: merged BAM/CRAM.
- ValidateSamFile: validation report (text file).
