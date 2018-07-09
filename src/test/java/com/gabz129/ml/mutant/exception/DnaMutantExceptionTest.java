package com.gabz129.ml.mutant.exception;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DnaMutantExceptionTest {

    /**
     * Scenario:
     *  Throws DnaMutantException
     * Expectation:
     *  Gets the expected message of the exception
     */
    @Test
    public void shouldInstanceDnaMutantException(){
        String errorMsg = "error";
        try {
            throw new DnaMutantException(errorMsg);
        }catch (DnaMutantException e){
            assertThat(e.getMessage(), is(errorMsg));
        }
    }

}