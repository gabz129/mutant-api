package com.gabz129.ml.mutant.validator;

import com.gabz129.ml.mutant.controller.request.DnaRequest;
import com.gabz129.ml.mutant.validator.DnaValidator;
import org.junit.Before;
import org.junit.Test;

public class DnaValidatorTest {

    private DnaValidator dnaValidator;

    @Before
    public void setUp(){
        dnaValidator = new DnaValidator();
    }

    /**
     * Scenario:
     *  Validate arguments
     * Expectation:
     *  Validation is ok
     */
    @Test
    public void shouldHaveValidArguments(){
        String[] data = {
                "ATGC",
                "CAGT",
                "TTAT",
                "TAAT"};
        DnaRequest request = new DnaRequest(data);
        dnaValidator.validate(request);
    }

    /**
     * Scenario:
     *  Validate Null data
     * Expectation:
     *  throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void shouldValidateNullData(){
        dnaValidator.validate(null);
    }

    /**
     * Scenario:
     *  Validate size of data
     * Expectation:
     *  Throws IllegalArgumentException due to wrong size
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldValidateSizeOfData(){
        String[] data = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "TTATGT"};
        DnaRequest request = new DnaRequest(data);
        dnaValidator.validate(request);
    }

    /**
     * Scenario:
     *  Validate size of data
     * Expectation:
     *  Throws IllegalArgumentException due to wrong min size
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldValidateMinSizeOfData(){
        String[] data = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT"};
        DnaRequest request = new DnaRequest(data);
        dnaValidator.validate(request);
    }

    /**
     * Scenario:
     *  Validate characters of the grid
     * Expectation:
     *  Throws IllegalArgumentException due to not allowed character
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldValidateExpectedCharacters(){
        String[] data = {
                "XTGC",
                "CAGT",
                "TTAT",
                "TAAT"};
        DnaRequest request = new DnaRequest(data);
        dnaValidator.validate(request);
    }
}