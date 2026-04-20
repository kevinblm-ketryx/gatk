---
itemId: TC-PATH-002
itemType: Test Case
itemTests: SPEC-PATH-002
---
# PathSeq Abundance Scoring Accuracy

## Preconditions
- Classified BAM and scores from TC-PATH-001.
- Known spike-in read counts and organism genome sizes for normalization validation.

## Test Steps

1. Parse the PathSeq scores TSV output from TC-PATH-001.
2. Verify all 5 spike-in organisms appear in the report with non-zero scores.
3. Verify read counts match expected spike-in values (within ± 10 % accounting for filtering losses).
4. Manually calculate expected normalized scores:
   - Expected score = (read_count / genome_size_Mb) × (1,000,000 / total_classified_reads).
5. Compare calculated scores to reported scores (tolerance: ± 5 %).
6. Verify taxonomic rollup: genus-level score ≥ species-level score for each organism.
7. Verify unambiguous read counts ≤ total read counts for each taxon.
8. Check report includes total reads processed, host reads removed, classified and unclassified counts.

## Expected Results

- All 5 organisms present in the report with correct taxonomy.
- Normalized scores within ± 5 % of manually calculated expected values.
- Genome-size normalization correctly adjusts for genome size differences (e.g., SARS-CoV-2 with ~30 kb genome vs. E. coli with ~5 Mb).
- Taxonomic hierarchy is consistent (parent scores ≥ child scores).
- Report format matches specification with all required columns.
