package com.k2senterprise.wissen;

import java.util.HashMap;
import java.util.Map;

/*Check validity of sudoku problem:
Determine if a 9 x 9 Sudoku board is valid.
The rules that need to be checked if the Sudoku grid is valid are :
        1.Each row must contain the digits 1-9 without repetition.
        2.Each column must contain the digits 1-9 without repetition.
        3.Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.*/

public class SudokuMain {
    public static void main(String[] args) {
        char[][] inputSudoku = new char[][]
                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        //Expected answer is : true

        boolean isSudokuValid = checkSudokuValidity(inputSudoku);
        System.out.println("Is given sudoku problem valid ? : " + isSudokuValid);

        boolean isSudokuValidCopilot = isValidSudoku(inputSudoku);
        System.out.println("Is given sudoku problem valid ? : " + isSudokuValidCopilot);
    }

    static boolean checkSudokuValidity(char[][] inputSudoku) {

        for (int i = 0; i < 9; i++) {
            Map<Character, Integer> rowMap = new HashMap<>();
            Map<Character, Integer> columnMap = new HashMap<>();
            Map<Character, Integer> subMap = new HashMap<>();
            for (int j = 0; j < 9; j++) {
                if (rowMap.containsKey(inputSudoku[i][j]) && inputSudoku[i][j] != '.') {
                    return false;
                } else {
                    rowMap.put(inputSudoku[i][j], 1);
                }

                if (columnMap.containsKey(inputSudoku[j][i]) && inputSudoku[j][i] != '.') {
                    return false;
                } else {
                    columnMap.put(inputSudoku[j][i], 1);
                }

            }
        }

        // Sub-box check
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                Map<Character, Integer> subMap = new HashMap<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        char val = inputSudoku[boxRow * 3 + i][boxCol * 3 + j];
                        if (subMap.containsKey(val) && val != '.') {
                            return false;
                        } else {
                            if (val != '.') subMap.put(val, 1);
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if a 9x9 Sudoku board is valid.
     * Rules:
     * 1. Each row must contain the digits 1-9 without repetition.
     * 2. Each column must contain the digits 1-9 without repetition.
     * 3. Each 3x3 sub-box must contain the digits 1-9 without repetition.
     *
     * @param board 2D char array representing the Sudoku board
     * @return true if valid, false otherwise
     */
    static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean[] row = new boolean[9];
            boolean[] col = new boolean[9];
            boolean[] box = new boolean[9];
            for (int j = 0; j < 9; j++) {
                // Row check
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    if (row[num]) return false;
                    row[num] = true;
                }
                // Column check
                if (board[j][i] != '.') {
                    int num = board[j][i] - '1';
                    if (col[num]) return false;
                    col[num] = true;
                }
                // Box check
                int boxRow = 3 * (i / 3) + j / 3;
                int boxCol = 3 * (i % 3) + j % 3;
                if (board[boxRow][boxCol] != '.') {
                    int num = board[boxRow][boxCol] - '1';
                    if (box[num]) return false;
                    box[num] = true;
                }
            }
        }
        return true;
    }
}