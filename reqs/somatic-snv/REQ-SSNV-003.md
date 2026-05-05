---
itemId: REQ-SSNV-003
itemType: Requirement
---
# Cross-Sample Contamination Estimation for Somatic Calling

The system shall estimate cross-sample contamination levels in tumor and normal samples to prevent germline variants from contaminating samples being misclassified as somatic mutations.

## Acceptance Criteria

1. GetPileupSummaries shall generate allele-frequency pileup data at common germline variant sites.
2. CalculateContamination shall estimate the fraction of cross-sample contamination from pileup summaries.
3. Contamination estimates shall be within ± 1 % of the true contamination fraction on in-silico mixture data.
4. The estimated contamination shall be consumable by FilterMutectCalls to adjust the somatic probability model.

## Traceability

- Implemented by: SPEC-SSNV-003
- Verified by: TC-SSNV-003
- Consumed by: REQ-SSNV-002 (FilterMutectCalls applies the contamination estimate)
