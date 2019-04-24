package com.game.sudoku;

import com.game.sudoku.entity.StandardSudoku;
import com.game.sudoku.entity.Sudoku;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SudokuTest {

    @Test
    public void givenSudoku_whenIsValidNumber_thenReturnIsValid() {
        StandardSudoku sudoku = new StandardSudoku(new int[][]{
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

        Assert.assertFalse(repeatInRow);
        Assert.assertFalse(repeatInCol);
        Assert.assertFalse(repeatInUnit);
        Assert.assertTrue(validNumber);
    }

    @Test
    public void givenSudokuWithSolution_whenResolve_thenResolve() {
        StandardSudoku sudoku = new StandardSudoku(new int[][]{
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

        Assert.assertTrue(isResolved);
    }

    @Test
    public void givenSudokuWithoutSolution_whenResolve_thenCannotResolve() {
        StandardSudoku sudoku = new StandardSudoku(new int[][]{
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

        Assert.assertFalse(isResolved);
    }

    @Test
    public void givenSudoku_whenGetValidNumberListAt_thenReturnValidNumberListInPosition() {
        StandardSudoku sudoku = new StandardSudoku(new int[][]{
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

        List<Integer> validNumberList = sudoku.getValidNumberListAt(2, 3);

        Assert.assertEquals(2, validNumberList.size());
    }

    @Test
    public void givenAnCompleteSudoku_whenIsComplete_thenCorrect() {
        StandardSudoku sudoku = new StandardSudoku(new int[][]{
                {8, 9, 5, 2, 1, 6, 3, 7, 4},
                {6, 7, 4, 5, 3, 9, 2, 1, 8},
                {3, 1, 2, 8, 4, 7, 5, 9, 6},
                {2, 3, 8, 6, 5, 1, 9, 4, 7},
                {7, 5, 1, 3, 9, 4, 8, 6, 2},
                {4, 6, 9, 7, 2, 8, 1, 5, 3},
                {1, 4, 6, 9, 8, 2, 7, 3, 5},
                {9, 2, 3, 4, 7, 5, 6, 8, 1},
                {5, 8, 7, 1, 6, 3, 4, 2, 9},
        });

        Assert.assertTrue(sudoku.isComplete());
    }

    @Test
    public void givenNotCompleteSudoku_whenIsComplete_thenFail() {
        StandardSudoku sudoku = new StandardSudoku(new int[][]{
                {8, 9, 5, 2, 0, 6, 3, 7, 4},
                {6, 7, 4, 5, 3, 9, 2, 1, 8},
                {3, 1, 2, 8, 4, 7, 5, 9, 6},
                {2, 3, 8, 6, 5, 1, 9, 4, 7},
                {7, 5, 1, 3, 9, 4, 8, 6, 2},
                {4, 6, 9, 7, 2, 8, 1, 5, 3},
                {1, 4, 6, 9, 8, 2, 7, 3, 5},
                {9, 2, 3, 4, 7, 5, 6, 8, 1},
                {5, 8, 7, 1, 6, 3, 4, 2, 9},
        });

        Assert.assertFalse(sudoku.isComplete());
    }

    @Test
    public void givenWrongSudoku_whenIsComplete_thenFail() {
        StandardSudoku sudoku = new StandardSudoku(new int[][]{
                {9, 9, 5, 2, 0, 6, 3, 7, 4},
                {6, 7, 4, 5, 3, 9, 2, 1, 8},
                {3, 1, 2, 8, 4, 7, 5, 9, 6},
                {2, 3, 8, 6, 5, 1, 9, 4, 7},
                {7, 5, 1, 3, 9, 4, 8, 6, 2},
                {4, 6, 9, 7, 2, 8, 1, 5, 3},
                {1, 4, 6, 9, 8, 2, 7, 3, 5},
                {9, 2, 3, 4, 7, 5, 6, 8, 1},
                {5, 8, 7, 1, 6, 3, 4, 2, 9},
        });

        Assert.assertFalse(sudoku.isComplete());
    }

    @Test
    public void givenNothing_whenGenerate_thenGenerateACompleteSudoku() {
        StandardSudoku sudoku = new StandardSudoku();
        sudoku.generate();
        Assert.assertTrue(sudoku.isComplete());
    }

    @Test
    public void givenSudokuWithEmptyCells_whenGetNextEmptyCell_thenReturnAnEmptyCell() {
        StandardSudoku sudoku = new StandardSudoku(new int[][]{
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

        Sudoku.Cell nextEmptyCell = sudoku.getNextEmptyCell();

        Assert.assertEquals(0, nextEmptyCell.getRow());
        Assert.assertEquals(2, nextEmptyCell.getCol());
        Assert.assertEquals(0, nextEmptyCell.getNumber());
    }

    @Test
    public void givenSudokuWithoutEmptyCells_whenGetNextEmptyCell_thenReturnNull() {
        StandardSudoku sudoku = new StandardSudoku(new int[][]{
                {8, 9, 5, 2, 1, 6, 3, 7, 4},
                {6, 7, 4, 5, 3, 9, 2, 1, 8},
                {3, 1, 2, 8, 4, 7, 5, 9, 6},
                {2, 3, 8, 6, 5, 1, 9, 4, 7},
                {7, 5, 1, 3, 9, 4, 8, 6, 2},
                {4, 6, 9, 7, 2, 8, 1, 5, 3},
                {1, 4, 6, 9, 8, 2, 7, 3, 5},
                {9, 2, 3, 4, 7, 5, 6, 8, 1},
                {5, 8, 7, 1, 6, 3, 4, 2, 9},
        });

        Sudoku.Cell nextEmptyCell = sudoku.getNextEmptyCell();

        Assert.assertNull(nextEmptyCell);
    }
}
