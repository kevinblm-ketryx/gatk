package org.broadinstitute.hellbender.tools.walkers.mutect.filtering;

import org.broadinstitute.hellbender.GATKBaseTest;
import org.broadinstitute.hellbender.utils.Nucleotide;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit tests for the FFPE-mode helpers introduced for REQ-SSNV-002 acceptance criterion 5
 * (suppression of C&gt;T / G&gt;A deamination artifacts in formalin-fixed samples).
 *
 * <p>Verifies that {@link ReadOrientationFilter#isDeaminationSubstitution} recognises the
 * cytosine-deamination signature on both strands and rejects every other substitution class,
 * so that the read-orientation posterior boost only applies where the FFPE artifact actually
 * lives. These tests cover the deterministic logic; end-to-end FDR / sensitivity behaviour
 * remains the responsibility of TC-SSNV-002 and TC-SSNV-003.
 */
public class FFPEModeUnitTest extends GATKBaseTest {

    @DataProvider(name = "deaminationSubstitutions")
    public Object[][] deaminationSubstitutions() {
        return new Object[][]{
                {Nucleotide.C, Nucleotide.T, true},   // FFPE forward-strand deamination
                {Nucleotide.G, Nucleotide.A, true},   // FFPE reverse-complement deamination
                {Nucleotide.C, Nucleotide.A, false},  // C>A (e.g. OxoG) is a different artifact class
                {Nucleotide.C, Nucleotide.G, false},
                {Nucleotide.T, Nucleotide.C, false},
                {Nucleotide.A, Nucleotide.G, false},
                {Nucleotide.G, Nucleotide.T, false},  // OxoG forward-strand
                {Nucleotide.G, Nucleotide.C, false},
                {Nucleotide.A, Nucleotide.T, false},
                {Nucleotide.T, Nucleotide.A, false},
                {Nucleotide.A, Nucleotide.C, false},
                {Nucleotide.T, Nucleotide.G, false},
                {Nucleotide.C, Nucleotide.C, false},  // identity is not a substitution
                {Nucleotide.G, Nucleotide.G, false},
        };
    }

    @Test(dataProvider = "deaminationSubstitutions")
    public void testDeaminationRecognition(final Nucleotide ref, final Nucleotide alt, final boolean expected) {
        Assert.assertEquals(ReadOrientationFilter.isDeaminationSubstitution(ref, alt), expected,
                String.format("Unexpected FFPE deamination classification for %s>%s", ref, alt));
    }

    /**
     * REQ-SSNV-002 acceptance criterion 5 requires that default behaviour is unchanged when
     * --ffpe-mode is not set. The {@link M2FiltersArgumentCollection} therefore defaults the
     * mode flag to false and the boosts to inert values; verify that explicitly so a future
     * change to the defaults breaks this test rather than silently shifting calibration.
     */
    @Test
    public void testFFPEDefaultsAreInert() {
        final M2FiltersArgumentCollection mtfac = new M2FiltersArgumentCollection();
        Assert.assertFalse(mtfac.ffpeMode, "ffpeMode must default to false (REQ-SSNV-002 #5)");
        Assert.assertTrue(mtfac.ffpeDeaminationPriorBoost >= 1.0,
                "ffpeDeaminationPriorBoost default must be >= 1.0 to avoid weakening the filter");
        Assert.assertTrue(mtfac.ffpeContaminationBoost >= 1.0,
                "ffpeContaminationBoost default must be >= 1.0 to avoid weakening the filter");
    }
}
