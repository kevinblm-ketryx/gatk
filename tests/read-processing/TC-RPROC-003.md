---
itemId: TC-RPROC-003
itemType: Test Case
itemTests: SPEC-RPROC-003
---
# Read Group Management and BAM Merging Validation

## Preconditions
- Two BAM files from different sequencing lanes of the same sample, with distinct read group IDs.
- A BAM file with missing or incorrect read group information.

## Test Steps

1. Add read groups to a BAM lacking them:
   ```
   gatk AddOrReplaceReadGroups -I no_rg.bam -O with_rg.bam \
     -RGID lane1 -RGLB lib1 -RGPL ILLUMINA -RGPU unit1 -RGSM sample1
   ```
2. Verify all reads carry the specified RG tag and header contains the @RG line.
3. Merge two lane-level BAMs:
   ```
   gatk MergeSamFiles -I lane1.bam -I lane2.bam -O merged.bam -SO coordinate
   ```
4. Verify merged BAM contains read groups from both inputs.
5. Verify coordinate sort order is maintained after merging.
6. Validate the merged BAM:
   ```
   gatk ValidateSamFile -I merged.bam -MODE SUMMARY
   ```
7. Intentionally introduce a mate-pair error and verify ValidateSamFile detects it.

## Expected Results

- AddOrReplaceReadGroups output has correct @RG header and RG tags on all reads.
- Merged BAM contains all read groups and reads from both inputs.
- Merged BAM is in coordinate sort order.
- ValidateSamFile reports no errors for a valid BAM.
- ValidateSamFile detects and reports mate-pair inconsistencies on the corrupted BAM.
