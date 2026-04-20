---
itemId: TC-SV-001
itemType: Test Case
itemTests: SPEC-SV-001
---
# Structural Variant Discovery Sensitivity on GIAB HG002

## Preconditions
- WGS BAM for HG002 (Ashkenazi son) at ≥ 30× coverage.
- GRCh38 reference genome.
- GIAB Tier 1 SV truth set for HG002 (v0.6 or later).
- Pre-installed SV callers (Manta, Wham) and GATK-SV pipeline.

## Test Steps

1. Collect SV evidence from HG002 BAM:
   ```
   gatk CollectSVEvidence -I HG002.bam -R GRCh38.fa \
     --split-read-file HG002_sr.txt --discordant-read-file HG002_pe.txt
   ```
2. Run ensemble SV callers and merge candidates using GATK-SV clustering.
3. Refine breakpoints using split-read evidence.
4. Evaluate discovered SVs against the GIAB Tier 1 truth set using truvari:
   ```
   truvari bench -b GIAB_HG002_SV.vcf -c gatksv_discovered.vcf \
     -r 500 -p 0.5 -o eval_results/
   ```
5. Stratify results by SV type (DEL, DUP, INV, INS) and size range.

## Expected Results

- Overall SV sensitivity ≥ 85 % for events ≥ 300 bp in Tier 1 regions.
- DEL sensitivity ≥ 90 %, DUP sensitivity ≥ 80 %.
- Breakpoint accuracy: median distance to truth breakpoints ≤ 10 bp for split-read resolved events.
- Multi-caller evidence annotations (SR_COUNT, PE_COUNT) are present in the output VCF.
