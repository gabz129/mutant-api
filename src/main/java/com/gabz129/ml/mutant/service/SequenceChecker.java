package com.gabz129.ml.mutant.service;

/**
 * Sequence checker interface
 */
public interface SequenceChecker {

    /**
     * Method to check if the current position has a sequence of the character
     *
     * @param grid      grid or matrix to check
     * @param rowPos    row position
     * @param columnPos column position
     * @param character    character to find a sequence
     * @return the size of the sequence for the character
     */
    int sizeOfSequence(char[][] grid, int rowPos, int columnPos, char character);

    /**
     * Number of sequences found
     * @param grid grid or matrix to check
     * @param rowPos row position
     * @param columnPos column position
     * @param character character to find a sequence
     * @return the number of sequences found for the character
     */
    int numberOfSequences(char[][] grid, int rowPos, int columnPos, char character);
}
