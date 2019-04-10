package com.game.sudoku;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Integer> getValidNumberSet(int row, int col) {
        int[] rows = cells[row];

        Set<Integer> existNumberSet = new HashSet<>();

        // 行
        for (int i = 0; i < rows.length; i++) {
            int num = rows[i];
            if (num > 0) {
                existNumberSet.add(num);
            }
        }

        // 列
        for (int i = 0; i < cells.length; i++) {
            int num = cells[i][col];
            if (num > 0) {
                existNumberSet.add(num);
            }
        }

        // 单元格
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
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

        List<Integer> validNumbers = allNumberList.stream()
                                              .filter(number -> !existNumberSet.contains(number))
                                              .collect(Collectors.toList());

        return validNumbers;
    }

    public boolean generate() {
        cells = new int[9][];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new int[9];
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = 0;
            }
        }

        return generateLoop();
    }

    private boolean generateLoop() {
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

        List<Integer> validNumberSet = getValidNumberSet(emptyRowIndex, emptyColIndex);

        for (int validNumber : validNumberSet) {
            cells[emptyRowIndex][emptyColIndex] = validNumber;
            isResolved = generateLoop();

            if (isResolved) {
                return isResolved;
            } else {
                cells[emptyRowIndex][emptyColIndex] = 0;
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
