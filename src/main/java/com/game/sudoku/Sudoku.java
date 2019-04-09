package com.game.sudoku;

public class Sudoku {
    private int[][] cells;

    public Sudoku(int[][] cells) {
        this.cells = cells;
    }

    public boolean isValidNumber(int row, int col, int number) {
        int[] rows = cells[row];

        // 行判重
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == number) {
                return false;
            }
        }

        // 列判重
        for (int i = 0; i < cells.length; i++) {
            if (cells[i][col] == number) {
                return false;
            }
        }

        // 单元格判重
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (cells[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean resolve() {
        int emptyRowIndex = 0;
        int emptyColIndex = 0;

        boolean hasEmptyCell = false;

        for (int i = 0; i < cells.length; i++) {
            int[] rows = cells[i];
            for (int j = 0; j < rows.length; j++) {
                int number = cells[i][j];

                if (number == 0) {
                    emptyRowIndex = i;
                    emptyColIndex = j;
                    hasEmptyCell = true;
                    break;
                }
            }

            if (hasEmptyCell) {
                break;
            }
        }

        if (!hasEmptyCell) {
            print();
            return true;
        }

        boolean isResolved = false;

        for (int i = 1; i < 10; i++) {
            if (isValidNumber(emptyRowIndex, emptyColIndex, i)) {
                cells[emptyRowIndex][emptyColIndex] = i;
                isResolved = resolve();

                if (!isResolved) {
                    cells[emptyRowIndex][emptyColIndex] = 0;
                }
            }
        }

        return isResolved;
    }

    public void print() {
        for (int i = 0; i < cells.length; i++) {
            int[] rows = cells[i];
            for (int j = 0; j < rows.length; j++) {
                int number = cells[i][j];

                System.out.print(number + " ");
            }

            System.out.println();
        }
    }
}
