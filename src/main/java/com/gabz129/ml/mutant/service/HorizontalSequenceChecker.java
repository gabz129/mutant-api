package com.gabz129.ml.mutant.service;

/**
 * Checker to verify if there is a horizontal sequence
 */
public class HorizontalSequenceChecker implements SequenceChecker {

    private boolean[][] checked;
    private int minSeqCharacters;

    /**
     * Instantiates a new Horizontal sequence checker.
     *
     * @param size          Size of the grid
     * @param minSeqCharacters Large of the sequence
     */
    public HorizontalSequenceChecker(final int size, final int minSeqCharacters) {
        this.checked = new boolean[size][size];
        this.minSeqCharacters = minSeqCharacters;
    }

    @Override
    public int sizeOfSequence(final char[][] grid, final int rowPos, final int columnPos, final char character) {
        //If the item was checked, then avoid it
        if (columnPos < grid[rowPos].length && checked[rowPos][columnPos]) {
            return 0;
        }

        if (columnPos < grid[rowPos].length && grid[rowPos][columnPos] == character) {
            checked[rowPos][columnPos] = true;
            return 1 + sizeOfSequence(grid, rowPos, columnPos + 1, character);
        }
        return 0;
    }

    @Override
    public int numberOfSequences(final char[][] grid, final int rowPos, final int columnPos, final char character) {
        return sizeOfSequence(grid, rowPos, columnPos, character) / minSeqCharacters;
    }
}
