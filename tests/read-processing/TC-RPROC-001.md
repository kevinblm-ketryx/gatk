---
itemId: TC-RPROC-001
itemType: Test Case
itemTests: SPEC-RPROC-001
---
# MarkDuplicates Accuracy and Metrics Validation

## Preconditions
- Aligned BAM with known duplication profile (e.g., NA12878 WGS with library-level duplication rate ~ 5–15 %).
- BAM from a patterned flow cell (HiSeq 4000/X or NovaSeq) for optical duplicate testing.

## Test Steps

1. Run MarkDuplicates:
   ```
   gatk MarkDuplicates -I NA12878.bam -O NA12878_markdup.bam -M dup_metrics.txt \
     --OPTICAL_DUPLICATE_PIXEL_DISTANCE 2500
   ```
2. Verify duplicate flag (0x400) is set on the expected reads using `samtools flagstat`.
3. Confirm the primary representative of each duplicate set has the highest base quality sum.
4. Parse metrics file: verify PERCENT_DUPLICATION, ESTIMATED_LIBRARY_SIZE, and READ_PAIR_OPTICAL_DUPLICATES.
5. Compare optical duplicate rate between pixel distance 100 and 2500 to confirm patterned flow cell detection.
6. Verify total read count is preserved (no reads lost or gained).

## Expected Results

- Duplicate rate reported in metrics matches independent calculation (samtools markdup stats within ± 0.5 %).
- Optical duplicate count increases significantly with pixel distance 2500 vs. 100 for patterned flow cells.
- Total read count in output BAM equals input BAM read count.
- No reads are removed (only flagged) when REMOVE_DUPLICATES is false.
