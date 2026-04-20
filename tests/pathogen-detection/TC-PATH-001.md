---
itemId: TC-PATH-001
itemType: Test Case
itemTests: SPEC-PATH-001
---
# PathSeq Microbial Detection Sensitivity

## Preconditions
- Synthetic BAM containing a mixture of human reads (background) and spiked-in microbial reads from 5 known organisms at varying abundances (10, 100, 1000, 10000 reads each).
- Host (human) BWA index and k-mer database for GRCh38.
- Microbial BWA index and taxonomy database.
- Spike-in organisms: E. coli, S. aureus, M. tuberculosis, SARS-CoV-2, C. albicans.

## Test Steps

1. Run PathSeqPipelineSpark:
   ```
   gatk PathSeqPipelineSpark --input spike_in.bam \
     --filter-bwa-image host_bwa.img --kmer-file host_kmers.bfi \
     --microbe-bwa-image microbe_bwa.img --taxonomy-file microbe_taxonomy.db \
     --output classified.bam --scores-output pathseq_scores.tsv
   ```
2. Verify all 5 spike-in organisms are detected in the output.
3. Assess sensitivity at each spike-in level (10, 100, 1000, 10000 reads).
4. Verify host reads are removed: check that classified BAM contains < 1 % human reads.
5. Check LCA assignments for reads mapping to multiple closely related taxa.

## Expected Results

- All 5 organisms detected at ≥ 100 read spike-in level (sensitivity ≥ 90 %).
- At 10-read level, detection is expected for ≥ 3 of 5 organisms (reduced sensitivity acknowledged).
- Host read contamination in classified output < 1 %.
- Taxonomic assignments are correct at species level for unambiguous reads.
- LCA assignments are at appropriate taxonomic level for ambiguous reads.
