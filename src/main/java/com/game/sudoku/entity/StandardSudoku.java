package com.game.sudoku.entity;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 标准数独
 */
@Getter
public class StandardSudoku extends Sudoku {

    public StandardSudoku() {
        super();
    }

    public StandardSudoku(int[][] cells) {
        super(cells);
    }

    @Override
    public boolean isValidNumber(int row, int col, int number) {
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

    @Override
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
}
