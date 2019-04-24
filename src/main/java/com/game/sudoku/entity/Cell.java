package com.game.sudoku.entity;

import lombok.Getter;
import lombok.ToString;

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
