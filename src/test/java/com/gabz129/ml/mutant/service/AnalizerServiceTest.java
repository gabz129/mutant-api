package com.gabz129.ml.mutant.service;

import com.gabz129.ml.mutant.controller.request.DnaRequest;
import com.gabz129.ml.mutant.controller.response.StatsResponse;
import com.gabz129.ml.mutant.entity.DnaResult;
import com.gabz129.ml.mutant.exception.DnaMutantException;
import com.gabz129.ml.mutant.repository.DnaResultRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AnalizerServiceTest {

    private AnalizerService analizerService;

    @Mock
    private DnaResultRepository dnaResultRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        analizerService = new AnalizerService(dnaResultRepository);
    }

    /**
     * Scenario:
     *  Check a mutant
     * Expectation:
     *  Checks successful
     */
    @Test
    public void shouldBeCheckedAsMutant(){
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
        DnaRequest request = new DnaRequest(dna);
        when(dnaResultRepository.save(any(DnaResult.class))).thenReturn(any(DnaResult.class));
        analizerService.check(request);
        verify(dnaResultRepository, times(1)).save(any());
    }

    /**
     * Scenario:
     *  Check a human dna
     * Expectation:
     *  Throws a DnaMutantException
     */
    @Test(expected = DnaMutantException.class)
    public void shouldNotBeAMutant(){
        String[] dna = {
                "ATGCGA",
                "CCGTAC",
                "TTATGT",
                "AGAAGG",
                "CCTCTA",
                "TCACTG"};
        DnaRequest request = new DnaRequest(dna);
        when(dnaResultRepository.save(any(DnaResult.class))).thenReturn(any(DnaResult.class));
        analizerService.check(request);
    }

    /**
     * Scenario:
     *  Generate report
     * Expectation:
     *  Generate a report with one mutant and not human
     */
    @Test
    public void shouldGenerateAReport(){
        StatsResponse expectedResponse = new StatsResponse(1, 0, BigDecimal.ONE);
        List<DnaResult> results = Arrays.asList(new DnaResult(1L, "dna", true));
        when(dnaResultRepository.findAll()).thenReturn(results);

        StatsResponse response = analizerService.generateReport();
        assertThat(response.getHumanCounter(), is(expectedResponse.getHumanCounter()));
        assertThat(response.getMutantCounter(), is(expectedResponse.getMutantCounter()));
        assertThat(response.getRatio(), is(expectedResponse.getRatio()));
    }

    /**
     * Scenario:
     *  Generate Report
     * Expectation:
     *  Generate an empty report with no data
     */
    @Test
    public void shouldGenerateAnEmptyReport(){
        StatsResponse expectedResponse = new StatsResponse(0, 0, BigDecimal.ZERO);
        List<DnaResult> results = Collections.emptyList();
        when(dnaResultRepository.findAll()).thenReturn(results);

        StatsResponse response = analizerService.generateReport();
        assertThat(response.getHumanCounter(), is(expectedResponse.getHumanCounter()));
        assertThat(response.getMutantCounter(), is(expectedResponse.getMutantCounter()));
        assertThat(response.getRatio(), is(expectedResponse.getRatio()));
    }

    /**
     * Scenario:
     *  Test all possible cases for being mutant
     * Expectation:
     *  Should be mutant
     */
    @Test
    public void shouldHaveTwoHorizontalSequences() {
        String[] dna = {
                "ATGCGA",
                "CAAAAC",
                "TTGTGT",
                "AGAAGG",
                "CGCCTA",
                "TCTTTT"
        };

        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveThreeHorizontalSequences() {
        String[] dna = {
                "ATGCGA",
                "AAAAAA",
                "TTGTGT",
                "GGGGGG",
                "CGCCTA",
                "TCTTTT"
        };

        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTwoHorizontalSequencesInSameRow() {
        String[] dna = {
                "AAAAAAAA",
                "CACAACGA",
                "TTGTGTAC",
                "AGAAGGAA",
                "CGCCTACG",
                "TCTTATAA",
                "TCTGTTCT",
                "TCTTCTAA"
        };
        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTwoSeparatedHorizontalSequencesInSameRow() {
        String[] dna = {
                "AAAACAAAA",
                "CACAACGAA",
                "TTGTGTACA",
                "AGAAGGAAC",
                "CGCCTACGA",
                "TCTTATAAA",
                "TCTGTTCTC",
                "TCTTCTAAT",
                "TCTTCTAAA"
        };
        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTwoVerticalSequences() {
        String[] dna = {
                "ATGCGA",
                "CACTGC",
                "CTCTGT",
                "CGCAGG",
                "CGCCTA",
                "CCACTG"};

        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveFourVerticalSequences() {
        String[] dna = {
                "ATGCGA",
                "CGCTGC",
                "CGTTGG",
                "CGCAGG",
                "CGCCTG",
                "CCACTG"};
        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTwoVerticalSequencesInSameColumn() {
        String[] dna = {
                "CATAGACA",
                "CACAACGA",
                "CTGTGTAC",
                "CGAAGGAA",
                "CGCCTACG",
                "CCTTATAA",
                "CCTGTTCT",
                "CCTTCTAA"
        };
        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTwoSeparatedVerticalSequencesInSameColumn() {
        String[] dna = {
                "CATAGACAA",
                "CACAACGAC",
                "CTGTGTACC",
                "CGAAGGAAA",
                "GGCCTACGG",
                "CCTTATAAA",
                "CCTGTTCTG",
                "CCTTCTACT",
                "CCTTCTAAT"
        };
        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTreeDiagonalToRighSequences() {
        String[] dna = {
                "ATGCGA",
                "CATTGC",
                "CGATGG",
                "TCCATC",
                "TGCCAT",
                "CCACTA"};
        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTwoDiagonalToRightSequencesInSameDiagonal() {
        String[] dna = {
                "CATAGACA",
                "ACCAACGA",
                "GTCTGTAC",
                "CGTCGGAA",
                "AGCTCACG",
                "CCTTTCAA",
                "TCTGTTCT",
                "CCTTCTTC"
        };
        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTwoSeparatedDiagonalToRightSequencesInSameDiagonal() {
        String[] dna = {
                "CATAGACAT",
                "ACCAACGAA",
                "GTCTGTACC",
                "CGGCGGAAT",
                "AGCTTACGC",
                "CCTATCAAT",
                "TCTGCTCTA",
                "CACTCAGCA",
                "CCTTCTTCC"
        };
        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTwoDiagonalToLeftSequences() {
        String[] dna = {
                "ATGCTA",
                "CCTTAC",
                "CGTAGG",
                "TTAGTC",
                "TATCAT",
                "CCACTA"};

        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveThreeDiagonalToLeftSequences() {
        String[] dna = {
                "ATGCTA",
                "CCTTAC",
                "CGTAGA",
                "TTAGAC",
                "TATAAT",
                "CCACTA"};

        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTwoDiagonalToLeftSequencesInSameDiagonal() {
        String[] dna = {
                "CATTGACA",
                "ATCAACAA",
                "GTCTGAAC",
                "CGAAAGAA",
                "AGCACACG",
                "CCATCGAA",
                "TATGTCCT",
                "ACTTCCTC"
        };
        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }

    @Test
    public void shouldHaveTwoSeparatedDiagonalToLeftSequencesInSameDiagonal() {
        String[] dna = {
                "CATTGACAA",
                "ATCAACCAT",
                "GTCTGGACA",
                "CGAGAAGAC",
                "AGCGCTCGG",
                "CCAACGAAT",
                "TCAGTCCTA",
                "AATTCATCA",
                "ACTTCCTCC"
        };
        boolean mutant = analizerService.isMutant(dna);
        assertThat(mutant, is(true));
    }
}