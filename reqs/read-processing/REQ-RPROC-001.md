---
itemId: REQ-RPROC-001
itemType: Requirement
---
# Duplicate Read Marking

The system shall identify and mark duplicate reads arising from PCR amplification or optical/sequencing artifacts so that downstream variant callers do not double-count evidence from the same original DNA fragment.

## Acceptance Criteria

1. MarkDuplicates shall accept one or more aligned BAM/CRAM files and produce an output with duplicate reads flagged (SAM flag 0x400).
2. Duplicate detection shall consider read alignment position, orientation, and mate alignment for paired-end data.
3. Among duplicate sets, the read with the highest sum of base qualities shall be designated as the primary (non-duplicate) representative.
4. The tool shall produce a duplication metrics file reporting the library-level duplication rate, estimated library size, and optical duplicate count.
5. Optical duplicate detection shall use configurable pixel distance thresholds for patterned (e.g., 2500 px for HiSeq 4000/X) and non-patterned flow cells.
