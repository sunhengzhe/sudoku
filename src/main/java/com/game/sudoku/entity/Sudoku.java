package com.game.sudoku.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
abstract public class Sudoku {
    protected int[][] cells;

    @Getter
    @ToString
    public class Cell {
        private int row;
        private int col;
        private int number;

        public Cell(int row, int col, int number) {
            this.row = row;
            this.col = col;
            this.number = number;
        }
    }

    public Sudoku() {
        cells = new int[9][];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new int[9];
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = 0;
            }
        }
    }

    public Sudoku(int[][] cells) {
        this.cells = cells;
    }

    abstract public boolean isValidNumber(int row, int col, int number);

    abstract public List<Integer> getValidNumberListAt(int rowIndex, int colIndex);

    public boolean resolve() {
        Cell nextEmptyCell = getNextEmptyCell();

        if (nextEmptyCell == null) {
            return true;
        }

        int emptyRowIndex = nextEmptyCell.getRow();
        int emptyColIndex = nextEmptyCell.getCol();

        for (int i = 1; i < 10; i++) {
            if (isValidNumber(emptyRowIndex, emptyColIndex, i)) {
                cells[emptyRowIndex][emptyColIndex] = i;

                if (resolve()) {
                    return true;
                } else {
                    cells[emptyRowIndex][emptyColIndex] = 0;
                }
            }
        }


        return false;
    }

    public boolean generate() {
        Cell nextEmptyCell = getNextEmptyCell();

        if (nextEmptyCell == null) {
            return true;
        }

        int emptyRowIndex = nextEmptyCell.getRow();
        int emptyColIndex = nextEmptyCell.getCol();

        List<Integer> validNumberSet = getValidNumberListAt(emptyRowIndex, emptyColIndex);

        for (int validNumber : validNumberSet) {
            cells[emptyRowIndex][emptyColIndex] = validNumber;

            if (generate()) {
                return true;
            } else {
                cells[emptyRowIndex][emptyColIndex] = 0;
            }
        }

        return false;
    }

    public Cell getNextEmptyCell() {
        for (int i = 0; i < cells.length; i++) {
            int[] rows = cells[i];
            for (int j = 0; j < rows.length; j++) {
                int number = cells[i][j];

                if (number == 0) {
                    return new Cell(i, j, number);
                }
            }
        }

        return null;
    }

    public boolean isComplete() {
        for (int i = 0; i < cells.length; i++) {
            int[] rows = cells[i];
            for (int j = 0; j < rows.length; j++) {
                int number = cells[i][j];
                if (number == 0 || !isValidNumber(i, j, number)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void print() {
        for (int[] rows: cells) {
            for (int number: rows) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
    }
}
