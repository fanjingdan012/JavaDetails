package net;

public class Sudoku {
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9], col = new boolean[9][9], grid = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = (int)(board[i][j] - '1');
                    System.out.println(num);
                    row[i][num] = true; col[j][num] = true; grid[3*(i/3)+j/3][num] = true;
                }
            }
        }
        backtracking(board, 0, 0, row, col, grid);
    }

    private boolean backtracking(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] grid) {
        // if we reach out of the board, we know that the board is valid now
        if (i == 9 && j == 0) return true;

        // each time we would check the next number (normally the right one)
        // if it's out of range, we check the next row
        int nexti = j == 8 ? i + 1 : i, nextj = j == 8 ? 0 : j + 1;
        if (board[i][j] != '.') return backtracking(board, nexti, nextj, row, col, grid);

        for (int num = 1; num <= 9; num++) {
            //row[i][num - 1] :row i has num
            if (!row[i][num - 1] && !col[j][num - 1] && !grid[3*(i/3)+j/3][num - 1]) {
                board[i][j] = (char)('0' + num);
                row[i][num - 1] = true; col[j][num - 1] = true; grid[3*(i/3)+j/3][num - 1] = true;

                if (backtracking(board, nexti, nextj, row, col, grid)) return true;
                row[i][num - 1] = false; col[j][num - 1] = false; grid[3*(i/3)+j/3][num - 1] = false;
                board[i][j] = '.';
            }
        }

        // we have already tried all numbers, however, we haven't got a solution
        // thus here we should return false
        return false;
    }
}
