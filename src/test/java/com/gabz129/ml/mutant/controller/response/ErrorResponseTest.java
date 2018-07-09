package com.gabz129.ml.mutant.controller.response;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ErrorResponseTest {

    /**
     * Scenario:
     *  Instance a response
     * Expectation:
     *  Gets and sets of response
     */
    @Test
    public void shouldGetSetResponse(){
        ErrorResponse response = new ErrorResponse();
        assertThat(response.getErrorMessage(), is(nullValue()));

        String errorMsg = "Error";
        response.setErrorMessage(errorMsg);
        assertThat(response.getErrorMessage(), is(errorMsg));
    }

}