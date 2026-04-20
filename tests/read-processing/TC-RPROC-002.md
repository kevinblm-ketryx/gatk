---
itemId: TC-RPROC-002
itemType: Test Case
itemTests: SPEC-RPROC-002
---
# BAM Sorting and Indexing Integrity

## Preconditions
- Unsorted or queryname-sorted BAM file with ≥ 1 million reads.
- GRCh38 reference genome (for CRAM testing).

## Test Steps

1. Sort by coordinate:
   ```
   gatk SortSam -I unsorted.bam -O sorted.bam -SO coordinate
   ```
2. Verify sort order using `samtools view -H sorted.bam | grep SO:coordinate`.
3. Verify reads are in ascending order by reference and position (spot-check first 10,000 reads).
4. Build BAM index:
   ```
   gatk BuildBamIndex -I sorted.bam -O sorted.bai
   ```
5. Verify random access: retrieve reads from a specific interval and confirm they match expected positions.
6. Test with a CRAM file and verify CRAI index generation.
7. Sort by queryname and verify lexicographic ordering.

## Expected Results

- Coordinate-sorted BAM has SO:coordinate header and reads in correct order.
- BAI index enables retrieval of reads for any genomic interval without full file scan.
- Read count is preserved between unsorted and sorted files.
- CRAM index (CRAI) is generated and functional.
- Queryname sort produces reads in lexicographic name order.
