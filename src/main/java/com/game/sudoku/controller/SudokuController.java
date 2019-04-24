package com.game.sudoku.controller;

import com.game.sudoku.entity.StandardSudoku;
import com.game.sudoku.entity.Sudoku;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sudoku")
public class SudokuController {

    public int[][] getSudoku() {
        Sudoku sudoku = new StandardSudoku();
        sudoku.generate();

        int[][] cells = sudoku.getCells();

        int clearCount = 5;

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

    @GetMapping
    public ModelAndView home() {
        int[][] cells = getSudoku();

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("cells", cells);
        return modelAndView;
    }

    @GetMapping("/api/generate")
    @ResponseBody
    public int[][] generate() {
        return getSudoku();
    }
}
