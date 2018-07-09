package com.gabz129.ml.mutant.controller.response;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

public class StatsResponseTest {

    /**
     * Scenario:
     *  Instance an empty response
     * Expectation:
     *  Gets an instance without ata
     */
    @Test
    public void shouldInstance(){
        StatsResponse response = new StatsResponse();
        assertThat(response, is(notNullValue()));
    }
    /**
     * Scenario:
     *  Instance a StatsResponse with data
     * Expectation:
     *  Gets the expected data
     */
    @Test
    public void shouldInstanceAStatsResponse(){
        int expectedMutant = 100;
        int expectedHuman = 500;
        BigDecimal expectedRatio = BigDecimal.valueOf(90.0);
        StatsResponse response = new StatsResponse(expectedMutant, expectedHuman, expectedRatio);
        assertThat(response, is(notNullValue()));
        assertThat(response.getHumanCounter(), is(expectedHuman));
        assertThat(response.getMutantCounter(), is(expectedMutant));
        assertThat(response.getRatio(), is(expectedRatio));
    }

}