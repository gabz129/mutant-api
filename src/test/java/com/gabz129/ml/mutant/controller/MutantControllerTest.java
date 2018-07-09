package com.gabz129.ml.mutant.controller;

import com.gabz129.ml.mutant.controller.request.DnaRequest;
import com.gabz129.ml.mutant.controller.response.StatsResponse;
import com.gabz129.ml.mutant.service.AnalizerService;
import com.gabz129.ml.mutant.validator.DnaValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class MutantControllerTest {

    private MutantController mutantController;

    @Mock
    private AnalizerService analizerService;

    @Mock
    private DnaValidator dnaValidator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mutantController = new MutantController(analizerService, dnaValidator);
    }

    @Test
    public void shouldBeAMutantAndResponseOk() {
        String[] dna = {"expectedDNA"};
        DnaRequest request = new DnaRequest(dna);

        doNothing().when(dnaValidator).validate(request);
        when(analizerService.isMutant(dna)).thenReturn(true);

        ResponseEntity response = mutantController.analizeDna(request);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Analizing sucessfully"));
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailedServiceAndCreateExceptionResponse(){
        String[] dna = null;
        DnaRequest request = new DnaRequest(dna);
//        request.setDna(dna);
        doNothing().when(dnaValidator).validate(request);
        doThrow(NullPointerException.class).when(analizerService).isMutant(dna);

        ResponseEntity response = mutantController.analizeDna(request);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
//        assertThat(response.getBody(), is("Analizing sucessfully"));

    }

    /**
     * Scenario:
     *  Execute {@link MutantController#reportDna()}
     * Expectation:
     *  Generate report with data
     */
    @Test
    public void shouldGenerateReport(){
        StatsResponse expectedReport = new StatsResponse(10, 50, BigDecimal.TEN);
        when(analizerService.generateReport()).thenReturn(expectedReport);

        ResponseEntity response = mutantController.reportDna();
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        StatsResponse report = (StatsResponse)response.getBody();
        assertThat(report.getMutantCounter(), is(expectedReport.getMutantCounter()));
        assertThat(report.getHumanCounter(), is(expectedReport.getHumanCounter()));
        assertThat(report.getRatio(), is(expectedReport.getRatio()));
    }

}