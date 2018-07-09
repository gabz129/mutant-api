package com.gabz129.ml.mutant.controller.request;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class DnaRequestTest {

    /**
     * Scenario:
     *  Instance a new request and update data
     * Expectation:
     *  Gets and Sets data
     */
    @Test
    public void shouldSetGetDna(){
        String[] dna = {"data", "data2"};
        DnaRequest request = new DnaRequest(dna);
        assertThat(request.getDna(), is(dna));

        String[] dna2 = {"data", "data2"};
        request.setDna(dna2);
        assertThat(request.getDna(), is(dna2));
    }

    /**
     * Scenario:
     *  Instance a request
     * Expectation:
     *  Is not be null
     */
    @Test
    public void shouldBeInstanced(){
        DnaRequest request = new DnaRequest();
        assertThat(request, is(notNullValue()));
    }
}