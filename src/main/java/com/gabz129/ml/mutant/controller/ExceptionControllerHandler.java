package com.gabz129.ml.mutant.controller;

import com.gabz129.ml.mutant.controller.response.ErrorResponse;
import com.gabz129.ml.mutant.exception.DnaMutantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller Advice to catch exceptions
 */
@ControllerAdvice
public class ExceptionControllerHandler {

    /**
     * Handler for Unexpected Exceptions
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity unexpectedException(final Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    /**
     * Handler for Illegal Argument in the request
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgumentException(final IllegalArgumentException e) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * Handler for dna that is not a mutant
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(DnaMutantException.class)
    public ResponseEntity dnaMutantException(final DnaMutantException e) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(response);
    }

}
