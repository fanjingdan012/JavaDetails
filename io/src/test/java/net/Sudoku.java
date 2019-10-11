package net;

import java.util.ArrayList;
import java.util.List;

class Sudoku {

    public void printBoard(char[][] board){
        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public List<char[][]> getAllSudokuSolutions(char[][] board1){

        char[][] board = board1.clone();
        List<char[][]> result = new ArrayList<>();
        int[][][] candidateCnt = new int[9][9][9];
        List<int[]> operations = new ArrayList<int[]>();
        boolean hasNoCandidate = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                hasNoCandidate = recalcCandidateCnt(i, j, 0, board, candidateCnt);
            }
        }
        int i = 0;
        int j = 0;
        int n = 0;
        int sinceN = 0;
        outer:
        while ((i < 9) && (j < 9)) {

            while ((i < 9) && (j < 9)&&getIntByChar(board[i][j]) != 0) {
                if (j < 8) {
                    j++;
                } else {
                    j = 0;
                    i++;
                }


            }
            sinceN = 0;
            if(!((i < 9) && (j < 9))){
                //out of this means one solution
                result.add(board.clone());
//                System.out.println("another solution========================");
//                printBoard(board.clone());
//                System.out.println(result);
                boolean needTraceBack = true;
                while (needTraceBack) {

                    int[] lastOperation = operations.get(operations.size() - 1);
                    i = lastOperation[0];
                    j = lastOperation[1];
                    //originalN = operation[2];
                    sinceN = lastOperation[3];
                    operations.remove(operations.size() - 1);
                    hasNoCandidate = tryFill(i, j, 0, board, candidateCnt, operations);
                    n = findFirstCandidate(i, j, sinceN, candidateCnt);
                    needTraceBack = (n == -1);
                }
                hasNoCandidate = tryFill(i, j, n, board, candidateCnt, operations);
                sinceN = n;
                if(!hasNoCandidate){
                    continue;
                }
            }
            if((i < 9) && (j < 9)) {
                //found empty position, try fill it until found

                do {
                    boolean needTraceBack = false;
                    if (sinceN >= 9) {
                        needTraceBack = true;
                    }
                    n = findFirstCandidate(i, j, sinceN, candidateCnt);
                    if (n == -1) {
                        needTraceBack = true;
                    }
                    while (needTraceBack) {
                        if(operations.size()==0){
                            break outer;
                        }
                        int[] lastOperation = operations.get(operations.size() - 1);
                        i = lastOperation[0];
                        j = lastOperation[1];
                        //originalN = operation[2];
                        sinceN = lastOperation[3];
                        operations.remove(operations.size() - 1);
                        hasNoCandidate = tryFill(i, j, 0, board, candidateCnt, operations);
                        n = findFirstCandidate(i, j, sinceN, candidateCnt);
                        needTraceBack = (n == -1);


                    }
                    hasNoCandidate = tryFill(i, j, n, board, candidateCnt, operations);
                    sinceN = n;
                } while (hasNoCandidate);
            }


        }

        return result;


    }
    public void solveSudoku(char[][] board) {
        int[][][] candidateCnt = new int[9][9][9];
        List<int[]> operations = new ArrayList<int[]>();
        boolean hasNoCandidate = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                hasNoCandidate = recalcCandidateCnt(i, j, 0, board, candidateCnt);
            }
        }
        int i = 0;
        int j = 0;
        int n = 0;
        int sinceN = 0;
        while ((i < 9) && (j < 9)) {

            while ((i < 9) && (j < 9)&&getIntByChar(board[i][j]) != 0) {
                if (j < 8) {
                    j++;
                } else {
                    j = 0;
                    i++;
                }


            }
            if((i < 9) && (j < 9)) {
                //found empty position, try fill it until found
                sinceN = 0;
                do {
                    boolean needTraceBack = false;
                    if (sinceN >= 9) {
                        needTraceBack = true;
                    }
                    n = findFirstCandidate(i, j, sinceN, candidateCnt);
                    if (n == -1) {
                        needTraceBack = true;
                    }
                    while (needTraceBack) {

                        int[] lastOperation = operations.get(operations.size() - 1);
                        i = lastOperation[0];
                        j = lastOperation[1];
                        //originalN = operation[2];
                        sinceN = lastOperation[3];
                        operations.remove(operations.size() - 1);
                        hasNoCandidate = tryFill(i, j, 0, board, candidateCnt, operations);
                        n = findFirstCandidate(i, j, sinceN, candidateCnt);
                        needTraceBack = (n == -1);


                    }
                    hasNoCandidate = tryFill(i, j, n, board, candidateCnt, operations);
                    sinceN = n;
                } while (hasNoCandidate);
            }

        }
    }

    public boolean tryFill(int i, int j, int n, char[][] board, int[][][] candidateCnt, List<int[]> operations) {
        //System.out.println("tryFill(" + i + "," + j + ")" + n+operations.toString());
        int originalN = getIntByChar(board[i][j]);
        board[i][j] = getCharByInt(n);
        boolean hasNoCandidate = recalcCandidateCnt(i, j, originalN, board, candidateCnt);
        if (hasNoCandidate) {
            board[i][j] = getCharByInt(originalN);
        } else if(n!=0){
            int[] operation = new int[4];
            operation[0] = i;
            operation[1] = j;
            operation[2] = originalN;
            operation[3] = n;
            operations.add(operation);
        }
        return hasNoCandidate;

    }

    public char getCharByInt(int n) {
        switch (n) {
            case 1:
                return '1';
            case 2:
                return '2';
            case 3:
                return '3';
            case 4:
                return '4';
            case 5:
                return '5';
            case 6:
                return '6';
            case 7:
                return '7';
            case 8:
                return '8';
            case 9:
                return '9';
            default:
                return '.';
        }
    }

    public int getIntByChar(char c) {
        switch (c) {
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                return 0;
        }
    }

    //sinceN=0 means all
    public int findFirstCandidate(int i, int j, int sinceN, int[][][] candidateCnt) {
        //System.out.println("(" + i + "," + j + ")since" + sinceN);
        for (int l = sinceN; l < 9; l++) {
            if (candidateCnt[i][j][l] == 0) {
                return l + 1;
            }
            //System.out.println("(" + i + "," + j + ","+l+")" + candidateCnt[i][j][l]);
        }
        return -1;
    }

    public boolean hasCandidate(int i, int j, int[][][] candidateCnt) {
        return findFirstCandidate(i, j, 0, candidateCnt) > 0;
    }

    //if initialize, originalN=0, return whether this operation will cause some empty position has no candidate left
    public boolean recalcCandidateCnt(int i, int j, int originalN, char[][] board, int[][][] candidateCnt) {
        boolean hasNoCandidate = false;
        int n = getIntByChar(board[i][j]);
        if (n == 0 && originalN == 0) {
            return hasNoCandidate;
        }
        for (int k = 0; k < 9; k++) {
            int rowN = getIntByChar(board[i][k]);
            int colN = getIntByChar(board[k][j]);
            int boxN = getIntByChar(board[i / 3 * 3 + k % 3][j / 3 * 3 + k / 3]);
            if (k !=j) {
                if (originalN == 0 && n != 0) {
                    candidateCnt[i][k][n - 1]++;
                } else if (originalN != 0 && n == 0) {
                    candidateCnt[i][k][originalN - 1]--;
                } else if (originalN != 0 && n != 0) {
                    candidateCnt[i][k][originalN - 1]--;
                    candidateCnt[i][k][n - 1]++;
                }
                hasNoCandidate = hasNoCandidate && !hasCandidate(i, k, candidateCnt);


            }
            if (k!=i) {

                if (originalN == 0 && n != 0) {
                    candidateCnt[k][j][n - 1]++;
                } else if (originalN != 0 && n == 0) {
                    candidateCnt[k][j][originalN - 1]--;
                } else if (originalN != 0 && n != 0) {
                    candidateCnt[k][j][originalN - 1]--;
                    candidateCnt[k][j][n - 1]++;
                }
                hasNoCandidate = hasNoCandidate && !hasCandidate(k, j, candidateCnt);
            }
            if ((i / 3 * 3 + k % 3)!=i&&(j / 3 * 3 + k / 3)!=j) {
                if (originalN == 0 && n != 0) {
                    candidateCnt[i / 3 * 3 + k % 3][j / 3 * 3 + k / 3][n - 1]++;
                } else if (originalN != 0 && n == 0) {
                    candidateCnt[i / 3 * 3 + k % 3][j / 3 * 3 + k / 3][originalN - 1]--;
                } else if (originalN != 0 && n != 0) {
                    candidateCnt[i / 3 * 3 + k % 3][j / 3 * 3 + k / 3][originalN - 1]--;
                    candidateCnt[i / 3 * 3 + k % 3][j / 3 * 3 + k / 3][n - 1]++;
                }
                hasNoCandidate = hasNoCandidate && !hasCandidate(i / 3 * 3 + k % 3, i / 3 * 3 + k % 3, candidateCnt);
            }
        }
        return hasNoCandidate;

    }

    public void calcCandidateCnt(int i, int j, char[][] board, int[][][] candidateCnt) {
        int n = getIntByChar(board[i][j]);
        if (n != 0) {
            return;
        }
        for (int k = 0; k < 9; k++) {
            int rowN = getIntByChar(board[i][k]);
            int colN = getIntByChar(board[k][j]);
            int boxN = getIntByChar(board[i / 3 * 3 + k % 3][j / 3 * 3 + k / 3]);
            if (rowN != 0) {
                candidateCnt[i][j][rowN - 1]++;
            }
            if (colN != 0) {
                candidateCnt[i][j][colN - 1]++;
            }
            if (boxN != 0) {
                candidateCnt[i][j][boxN - 1]++;
            }
        }

    }

}