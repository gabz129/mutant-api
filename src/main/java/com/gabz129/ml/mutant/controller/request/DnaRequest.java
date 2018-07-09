package com.gabz129.ml.mutant.controller.request;

/**
 * The type Request dna.
 */
public class DnaRequest {

    private String[] dna;

    /**
     * Needs to bind request
     */
    public DnaRequest() {
    }

    /**
     * Instantiates a new Dna request.
     *
     * @param dna the dna
     */
    public DnaRequest(final String[] dna) {
        this.dna = dna.clone();
    }

    /**
     * Get dna string[].
     *
     * @return the string[]
     */
    public String[] getDna() {
        return dna.clone();
    }

    /**
     * Sets dna.
     *
     * @param dna the dna
     */
    public void setDna(final String[] dna) {
        this.dna = dna.clone();
    }
}
