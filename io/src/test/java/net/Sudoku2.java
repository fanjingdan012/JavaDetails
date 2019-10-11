package net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.IntStream;


public class Sudoku2 {

    private static final int[][][] ROWS = {
            {{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 0, 2}, {0, 1, 0, 0}, {0, 1, 0, 1}, {0, 1, 0, 2},
                    {0, 2, 0, 0}, {0, 2, 0, 1}, {0, 2, 0, 2}},
            {{0, 0, 1, 0}, {0, 0, 1, 1}, {0, 0, 1, 2}, {0, 1, 1, 0}, {0, 1, 1, 1}, {0, 1, 1, 2},
                    {0, 2, 1, 0}, {0, 2, 1, 1}, {0, 2, 1, 2}},
            {{0, 0, 2, 0}, {0, 0, 2, 1}, {0, 0, 2, 2}, {0, 1, 2, 0}, {0, 1, 2, 1}, {0, 1, 2, 2},
                    {0, 2, 2, 0}, {0, 2, 2, 1}, {0, 2, 2, 2}},

            {{1, 0, 0, 0}, {1, 0, 0, 1}, {1, 0, 0, 2}, {1, 1, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 2},
                    {1, 2, 0, 0}, {1, 2, 0, 1}, {1, 2, 0, 2}},
            {{1, 0, 1, 0}, {1, 0, 1, 1}, {1, 0, 1, 2}, {1, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 2},
                    {1, 2, 1, 0}, {1, 2, 1, 1}, {1, 2, 1, 2}},
            {{1, 0, 2, 0}, {1, 0, 2, 1}, {1, 0, 2, 2}, {1, 1, 2, 0}, {1, 1, 2, 1}, {1, 1, 2, 2},
                    {1, 2, 2, 0}, {1, 2, 2, 1}, {1, 2, 2, 2}},

            {{2, 0, 0, 0}, {2, 0, 0, 1}, {2, 0, 0, 2}, {2, 1, 0, 0}, {2, 1, 0, 1}, {2, 1, 0, 2},
                    {2, 2, 0, 0}, {2, 2, 0, 1}, {2, 2, 0, 2}},
            {{2, 0, 1, 0}, {2, 0, 1, 1}, {2, 0, 1, 2}, {2, 1, 1, 0}, {2, 1, 1, 1}, {2, 1, 1, 2},
                    {2, 2, 1, 0}, {2, 2, 1, 1}, {2, 2, 1, 2}},
            {{2, 0, 2, 0}, {2, 0, 2, 1}, {2, 0, 2, 2}, {2, 1, 2, 0}, {2, 1, 2, 1}, {2, 1, 2, 2},
                    {2, 2, 2, 0}, {2, 2, 2, 1}, {2, 2, 2, 2}},};


    public static String submit(String puzzle, int block1, int block2, String block)
            throws MalformedURLException, IOException {
        System.out.println(block1 + "/" + block2 + block);
        return Http2Util.request("http://10.55.129.204:8888/api/svetoku/puzzles/" + puzzle + "/blocks/" + block1 + "/" + block2, "PUT", block);
    }


    public static String[][] parseBlock(String block) {
        String inner = block.substring(1, block.length() - 1);
        inner = inner.replace("],", "]");
        inner = inner.replace("[", "");
        String[] first = inner.split("]");
        String[][] result = new String[3][3];
        for (int i = 0; i < 3; i++) {
            result[i] = first[i].split(",");
        }
        return result;
    }

    public static String concat(String[][] block) {
        return "[[" + block[0][0] + "," + block[0][1] + "," + block[0][2] + "],[" + block[1][0] + "," + block[1][1]
                + "," + block[1][2] + "],[" + block[2][0] + "," + block[2][1] + "," + block[2][2] + "]]";
    }

    public static int[][] convert1(String[][][][] blocks) {
        int[][] result = new int[9][9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        for (int m = 0; m < 9; m++) {
                            boolean find = false;
                            for (int n = 0; n < 9; n++) {
                                if (ROWS[m][n][0] == i && ROWS[m][n][1] == j && ROWS[m][n][2] == k
                                        && ROWS[m][n][3] == l) {
                                    result[m][n] = Integer.parseInt(blocks[i][j][k][l]);
                                    find = true;
                                    break;
                                }
                            }
                            if (find) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static String[][][][] convert2(int[][] result) {
        String[][][][] blocks = new String[3][3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        for (int m = 0; m < 9; m++) {
                            boolean find = false;
                            for (int n = 0; n < 9; n++) {
                                if (ROWS[m][n][0] == i && ROWS[m][n][1] == j && ROWS[m][n][2] == k
                                        && ROWS[m][n][3] == l) {
                                    blocks[i][j][k][l] = String.valueOf(result[m][n]);
                                    find = true;
                                    break;
                                }
                            }
                            if (find) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return blocks;
    }

    public static boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (board[row][column] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && solve(board)) {
                            return true;
                        }
                        board[row][column] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int[][] board, int row, int column) {
        return (rowConstraint(board, row) && columnConstraint(board, column)
                && subsectionConstraint(board, row, column));
    }

    private static boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[9];
        return IntStream.range(0, 9).allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    private static boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[9];
        return IntStream.range(0, 9).allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    private static boolean subsectionConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[9];
        int subsectionRowStart = (row / 3) * 3;
        int subsectionRowEnd = subsectionRowStart + 3;

        int subsectionColumnStart = (column / 3) * 3;
        int subsectionColumnEnd = subsectionColumnStart + 3;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c))
                    return false;
            }
        }
        return true;
    }

    private static boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
        if (board[row][column] != 0) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}