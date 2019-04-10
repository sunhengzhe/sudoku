package com.game.sudoku;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SudokuTest {

    @Test
    public void should_is_valid_number_works_well() {
        Sudoku sudoku = new Sudoku(new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        });

        boolean repeatInRow = sudoku.isValidNumber(0, 1, 3);
        boolean repeatInCol = sudoku.isValidNumber(3, 8, 1);
        boolean repeatInUnit = sudoku.isValidNumber(7, 4, 6);
        boolean validNumber = sudoku.isValidNumber(7, 6, 1);

        Assert.assertEquals(false, repeatInRow);
        Assert.assertEquals(false, repeatInCol);
        Assert.assertEquals(false, repeatInUnit);
        Assert.assertEquals(true, validNumber);
    }

    @Test
    public void test_print() {
        Sudoku sudoku = new Sudoku(new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        });

        sudoku.print();
    }

    @Test
    public void should_resolve_if_has_solution() {
        Sudoku sudoku = new Sudoku(new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        });

        boolean isResolved = sudoku.resolve();

        Assert.assertEquals(true, isResolved);
    }

    @Test
    public void should_not_resolve_if_has_not_solution() {
        Sudoku sudoku = new Sudoku(new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 1, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {1, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 7, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        });

        boolean isResolved = sudoku.resolve();

        Assert.assertEquals(false, isResolved);
    }

    @Test
    public void should_get_valid_number_set() {
        Sudoku sudoku = new Sudoku(new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        });

        List<Integer> validNumberList = sudoku.getValidNumberSet(2, 3);

        Assert.assertEquals(2, validNumberList.size());
    }

    @Test
    public void should_generate_a_full_sudoku() {
        Sudoku sudoku = new Sudoku(new int[][]{});
        sudoku.generate();
    }
}
