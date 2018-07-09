package com.gabz129.ml.mutant.controller;

import com.gabz129.ml.mutant.controller.response.ErrorResponse;
import com.gabz129.ml.mutant.exception.DnaMutantException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ExceptionControllerHandlerTest {

    private ExceptionControllerHandler exceptionControllerHandler;

    @Before
    public void setUp(){
        exceptionControllerHandler = new ExceptionControllerHandler();
    }

    /**
     * Scenario:
     *  Execute {@link ExceptionControllerHandler#unexpectedException(Exception)}
     * Expectation:
     *  Generate an internal error response
     */
    @Test
    public void shouldGenerateUnexpectedErrorResponse(){
        String errorMsg = "error";
        Exception exception = new Exception(errorMsg);

        ResponseEntity response = exceptionControllerHandler.unexpectedException(exception);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(((ErrorResponse)response.getBody()).getErrorMessage(), is(exception.getMessage()));
    }

    /**
     * Scenario:
     *  Execute {@link ExceptionControllerHandler#illegalArgumentException(IllegalArgumentException)}
     * Expectation:
     *  Generate an Bad Request response
     */
    @Test
    public void shouldGenerateBadRequestResponse(){
        String errorMsg = "Bad Request";
        IllegalArgumentException exception = new IllegalArgumentException(errorMsg);

        ResponseEntity response = exceptionControllerHandler.illegalArgumentException(exception);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(((ErrorResponse)response.getBody()).getErrorMessage(), is(exception.getMessage()));
    }

    /**
     * Scenario:
     *  Execute {@link ExceptionControllerHandler#dnaMutantException(DnaMutantException)}
     * Expectation:
     *  Generate a Forbidden response
     */
    @Test
    public void shouldGenerateForbiddenResponse(){
        String errorMsg = "Forbidden";
        DnaMutantException exception = new DnaMutantException(errorMsg);

        ResponseEntity response = exceptionControllerHandler.dnaMutantException(exception);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
        assertThat(((ErrorResponse)response.getBody()).getErrorMessage(), is(exception.getMessage()));
    }

}