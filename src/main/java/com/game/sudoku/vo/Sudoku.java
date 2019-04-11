package com.game.sudoku.vo;

import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Sudoku {
    private int[][] cells;

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

    public boolean isValidNumberAt(int row, int col) {
        int number = cells[row][col];

        if (number == 0) {
            return false;
        }

        return isValidNumber(row, col, number);
    }

    public boolean isValidToPutNumberAt(int row, int col, int number) {
        int existNumber = cells[row][col];

        if (existNumber != 0) {
            return false;
        }

        return isValidNumber(row, col, number);
    }

    private boolean isValidNumber(int row, int col, int number) {
        int[] rows = cells[row];

        // 行判重
        for (int i = 0; i < rows.length; i++) {
            if (i != col && rows[i] == number) {
                return false;
            }
        }

        // 列判重
        for (int i = 0; i < cells.length; i++) {
            if (i != row && cells[i][col] == number) {
                return false;
            }
        }

        // 单元格判重
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (!(i == row && j == col) && cells[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isComplete() {
        for (int i = 0; i < cells.length; i++) {
            int[] rows = cells[i];
            for (int j = 0; j < rows.length; j++) {
                if (!isValidNumberAt(i, j)) {
                    return false;
                }
            }
        }

        return true;
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

    public boolean resolve() {
        Cell nextEmptyCell = getNextEmptyCell();

        if (nextEmptyCell == null) {
            return true;
        }

        int emptyRowIndex = nextEmptyCell.getRow();
        int emptyColIndex = nextEmptyCell.getCol();

        for (int i = 1; i < 10; i++) {
            if (isValidToPutNumberAt(emptyRowIndex, emptyColIndex, i)) {
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

    public List<Integer> getValidNumberListAt(int rowIndex, int colIndex) {
        int[] row = cells[rowIndex];

        Set<Integer> existNumberSet = new HashSet<>();

        // 行
        for (int number: row) {
            if (number > 0) {
                existNumberSet.add(number);
            }
        }

        // 列
        for (int[] rows: cells) {
            int num = rows[colIndex];
            if (num > 0) {
                existNumberSet.add(num);
            }
        }

        // 单元格
        int startRow = rowIndex / 3 * 3;
        int startCol = colIndex / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                int num = cells[i][j];
                if (num > 0) {
                    existNumberSet.add(num);
                }
            }
        }

        List<Integer> allNumberList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            allNumberList.add(i);
        }

        Collections.shuffle(allNumberList);

        return allNumberList.stream()
                            .filter(number -> !existNumberSet.contains(number))
                            .collect(Collectors.toList());
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

    public void print() {
        for (int[] rows: cells) {
            for (int number: rows) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
    }
}
