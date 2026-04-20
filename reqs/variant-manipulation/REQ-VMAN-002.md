---
itemId: REQ-VMAN-002
itemType: Requirement
---
# VCF Merging and Concatenation

The system shall merge or concatenate multiple VCF files from different samples, intervals, or calling runs into a single unified VCF.

## Acceptance Criteria

1. MergeVcfs shall combine VCF files from different genomic intervals (same samples) into a single sorted VCF.
2. GatherVcfs shall concatenate VCF files that represent non-overlapping sorted intervals with minimal overhead.
3. The merged output shall preserve all INFO and FORMAT fields from all input VCFs.
4. Header lines from all inputs shall be combined, with conflicting definitions handled by keeping the first occurrence and emitting a warning.
