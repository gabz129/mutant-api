package com.gabz129.ml.mutant.service;

/**
 * The type Diagonal left sequence checker.
 */
public class DiagonalLeftSequenceChecker implements SequenceChecker {

    private boolean[][] checked;
    private int minSeqCharacters;

    /**
     * Instantiates a new Diagonal left sequence checker.
     *
     * @param size          the size
     * @param minSeqCharacters the min seq characters
     */
    public DiagonalLeftSequenceChecker(final int size, final int minSeqCharacters) {
        this.checked = new boolean[size][size];
        this.minSeqCharacters = minSeqCharacters;
    }

    @Override
    public int sizeOfSequence(final char[][] grid, final int rowPos, final int columnPos, final char character) {
        //If the item was checked, then avoid it
        if (rowPos < grid.length && columnPos >= 0
                && checked[rowPos][columnPos]) {
            return 0;
        }

        if (rowPos < grid.length && columnPos >= 0
                && grid[rowPos][columnPos] == character) {
            checked[rowPos][columnPos] = true;
            return 1 + sizeOfSequence(grid, rowPos + 1, columnPos - 1, character);
        }
        return 0;
    }

    @Override
    public int numberOfSequences(final char[][] grid, final int rowPos, final int columnPos, final char character) {
        return sizeOfSequence(grid, rowPos, columnPos, character) / minSeqCharacters;
    }
}
