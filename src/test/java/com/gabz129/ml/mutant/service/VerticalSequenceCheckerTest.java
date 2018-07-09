package com.gabz129.ml.mutant.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class VerticalSequenceCheckerTest {

    private VerticalSequenceChecker verticalSequenceChecker;
    private char[][] grid;

    @Before
    public void setUp(){
        final String[] data = {
                "AATAGACAA",
                "AATAACGAC",
                "ATTTTCACA",
                "AGTAGXAAC",
                "AGTCAXCAA",
                "ACTTAXAAA",
                "ACTGGXGTC",
                "ACTTCXAAT",
                "ACTTCXAAA"
        };
        int sizeOfGrid = data.length;
        verticalSequenceChecker = new VerticalSequenceChecker(sizeOfGrid,4);

        grid = new char[sizeOfGrid][sizeOfGrid];
        int row = 0;
        for (String item : data) {
            grid[row] = item.toCharArray();
            row++;
        }
    }

    /**
     * Scenario:
     *  Check grid and find a sequence
     * Expectation:
     *  Found a sequence of 9 Characters
     */
    @Test
    public void shouldHaveAVerticalSequenceOfEightCharacters() {
        int sizeOfSequence = verticalSequenceChecker.sizeOfSequence(grid,0,2,'T');
        assertThat(sizeOfSequence, is(9));
    }

    /**
     * Scenario:
     *  Check grid and find a sequence
     * Expectation:
     *  Found a sequence of 6 Characters
     */
    @Test
    public void shouldHaveAVerticalSequenceOfSix() {
        int sizeOfSequence = verticalSequenceChecker.sizeOfSequence(grid,3,5,'X');
        assertThat(sizeOfSequence, is(6));
    }

    /**
     * Scenario:
     *  Check grid and find a sequence
     * Expectation:
     *  Found 2 sequences
     */
    @Test
    public void shouldReturnTwoSequences(){
        int numberOfSequence = verticalSequenceChecker.numberOfSequences(grid,0,0,'A');
        assertThat(numberOfSequence, is(2));
    }

    /**
     * Scenario:
     *  Check grid and find a sequence
     * Expectation:
     *  Found 0 sequences
     */
    @Test
    public void shouldNotReturnASequence(){
        int numberOfSequence = verticalSequenceChecker.numberOfSequences(grid,0,8,'A');
        assertThat(numberOfSequence, is(0));
    }
}