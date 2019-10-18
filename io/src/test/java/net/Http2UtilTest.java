package net;

import org.junit.Test;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static net.Sudoku2.*;

public class Http2UtilTest {
    @Test
    public void solveSvetoku() throws ExecutionException, InterruptedException {
        HttpResponse.BodyHandler<String> stringBodyHandler = HttpResponse.BodyHandlers.ofString();
        boolean printlog = true;
        Http2Util http2Util = new Http2Util();

        boolean cont = true;
        while (cont) {
            String temp = http2Util.sendRequest("http://10.55.129.204:8888/api/svetoku/puzzles","Post",new HashMap<>(),"");

            final String puzzle = temp.substring(1, temp.length() - 1);
            System.out.println(puzzle);

            long start = System.currentTimeMillis();
            final String[][][][] blocks = new String[3][3][3][3];
            CompletableFuture<HttpResponse<String>> responses[] = new CompletableFuture[9];
            for (int i = 0; i < 9; i++) {
                responses[i] =http2Util.sendRequestAsync("http://10.55.129.204:8888/api/svetoku/puzzles/" + puzzle + "/blocks/" + (i / 3) + "/" + (i % 3),"Get",new HashMap<>(),"");
            }

            boolean[] finished = new boolean[9];
            boolean hasNotDone = true;
            while (hasNotDone) {
                hasNotDone = false;
                for (int i = 0; i < 9; i++) {
                    if (finished[i]) {
                        continue;
                    }

                    if (responses[i].isDone()) {
                        finished[i] = true;
                        blocks[i / 3][i % 3] = parseBlock(responses[i].get().body());
                    } else {
                        hasNotDone = true;
                    }
                }
            }
            if (printlog)
                System.out.println(System.currentTimeMillis() - start);

            int[][] tt = Sudoku2.convert1(blocks);

            solve(tt);
            String[][][][] result = convert2(tt);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            blocks[i][j][k][l] = result[i][j][k][l];
                        }
                    }
                }
            }


            for (int i = 0; i < 3; i++) {
                final int ii = i;
                for (int j = 0; j < 3; j++) {
                    final int jj = j;
                    new Thread() {
                        public void run() {
                            try {
                                System.out.println(ii + " " + jj + " :" + submit(puzzle, ii, jj, concat(blocks[ii][jj])));
                            } catch (IOException e) {
                            }
                        }
                    }.start();

                }
            }

        }
    }
}

