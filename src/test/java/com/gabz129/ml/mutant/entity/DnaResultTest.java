package com.gabz129.ml.mutant.entity;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class DnaResultTest {

    /**
     * Scenario:
     *  Instance by constructor
     * Expectation:
     *  Sets all data by contructor
     */
    @Test
    public void shouldInstanceByConstructor() {
        Long expectedId = 1L;
        String expectedData = "data";
        boolean expectedResult = true;
        DnaResult dnaResult = new DnaResult(expectedId, expectedData, expectedResult);
        assertThat(dnaResult.getId(), is(expectedId));
        assertThat(dnaResult.getDna(), is(expectedData));
        assertThat(dnaResult.isMutant(), is(expectedResult));
    }

    /**
     * Scenario:
     *  Instance default constructor
     * Expectation:
     *  Instance without data
     */
    @Test
    public void shouldInstanceByDefaultConstructor(){
        DnaResult dnaResult = new DnaResult();
        assertThat(dnaResult, is(notNullValue()));
    }

}