package com.gabz129.ml.mutant.service;

/**
 * The type Diagonal right sequence checker.
 * This checks diagonal from left to right
 */
public class DiagonalRightSequenceChecker implements SequenceChecker {

    private boolean[][] checked;
    private int minSeqCharacter;

    /**
     * Instantiates a new Diagonal righ sequence checker.
     *
     * @param size          the size
     * @param minSeqCharacter the min seq character
     */
    public DiagonalRightSequenceChecker(final int size, final int minSeqCharacter) {
        this.checked = new boolean[size][size];
        this.minSeqCharacter = minSeqCharacter;
    }

    @Override
    public int sizeOfSequence(final char[][] grid, final int rowPos, final int columnPos, final char character) {
        //If the item was checked, then avoid it
        if (rowPos < grid.length && columnPos < grid[rowPos].length
                && checked[rowPos][columnPos]) {
            return 0;
        }

        if (rowPos < grid.length && columnPos < grid[rowPos].length
                && grid[rowPos][columnPos] == character) {
            checked[rowPos][columnPos] = true;
            return 1 + sizeOfSequence(grid, rowPos + 1, columnPos + 1, character);
        }
        return 0;
    }

    @Override
    public int numberOfSequences(final char[][] grid, final int rowPos, final int columnPos, final char character) {
        return sizeOfSequence(grid, rowPos, columnPos, character) / minSeqCharacter;
    }
}
