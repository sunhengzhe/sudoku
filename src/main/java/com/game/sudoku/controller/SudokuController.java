package com.game.sudoku.controller;

import com.game.sudoku.vo.Sudoku;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sudoku")
public class SudokuController {

    @GetMapping
    public int[][] generate() {
        Sudoku sudoku = new Sudoku();
        sudoku.generate();

        int[][] cells = sudoku.getCells();

        int clearCount = 40;

        while (clearCount > 0) {
            int randomRow = (int)(Math.random() * 9);
            int randomCol = (int)(Math.random() * 9);

            if (cells[randomRow][randomCol] != 0) {
                cells[randomRow][randomCol] = 0;
                clearCount--;
            }
        }

        return cells;
    }
}
