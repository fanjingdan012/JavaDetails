package net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;


public class SvetokuHttp20 {

	private static final int[][][] ROWS = {
			{ { 0, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 0, 2 }, { 0, 1, 0, 0 }, { 0, 1, 0, 1 }, { 0, 1, 0, 2 },
					{ 0, 2, 0, 0 }, { 0, 2, 0, 1 }, { 0, 2, 0, 2 } },
			{ { 0, 0, 1, 0 }, { 0, 0, 1, 1 }, { 0, 0, 1, 2 }, { 0, 1, 1, 0 }, { 0, 1, 1, 1 }, { 0, 1, 1, 2 },
					{ 0, 2, 1, 0 }, { 0, 2, 1, 1 }, { 0, 2, 1, 2 } },
			{ { 0, 0, 2, 0 }, { 0, 0, 2, 1 }, { 0, 0, 2, 2 }, { 0, 1, 2, 0 }, { 0, 1, 2, 1 }, { 0, 1, 2, 2 },
					{ 0, 2, 2, 0 }, { 0, 2, 2, 1 }, { 0, 2, 2, 2 } },

			{ { 1, 0, 0, 0 }, { 1, 0, 0, 1 }, { 1, 0, 0, 2 }, { 1, 1, 0, 0 }, { 1, 1, 0, 1 }, { 1, 1, 0, 2 },
					{ 1, 2, 0, 0 }, { 1, 2, 0, 1 }, { 1, 2, 0, 2 } },
			{ { 1, 0, 1, 0 }, { 1, 0, 1, 1 }, { 1, 0, 1, 2 }, { 1, 1, 1, 0 }, { 1, 1, 1, 1 }, { 1, 1, 1, 2 },
					{ 1, 2, 1, 0 }, { 1, 2, 1, 1 }, { 1, 2, 1, 2 } },
			{ { 1, 0, 2, 0 }, { 1, 0, 2, 1 }, { 1, 0, 2, 2 }, { 1, 1, 2, 0 }, { 1, 1, 2, 1 }, { 1, 1, 2, 2 },
					{ 1, 2, 2, 0 }, { 1, 2, 2, 1 }, { 1, 2, 2, 2 } },

			{ { 2, 0, 0, 0 }, { 2, 0, 0, 1 }, { 2, 0, 0, 2 }, { 2, 1, 0, 0 }, { 2, 1, 0, 1 }, { 2, 1, 0, 2 },
					{ 2, 2, 0, 0 }, { 2, 2, 0, 1 }, { 2, 2, 0, 2 } },
			{ { 2, 0, 1, 0 }, { 2, 0, 1, 1 }, { 2, 0, 1, 2 }, { 2, 1, 1, 0 }, { 2, 1, 1, 1 }, { 2, 1, 1, 2 },
					{ 2, 2, 1, 0 }, { 2, 2, 1, 1 }, { 2, 2, 1, 2 } },
			{ { 2, 0, 2, 0 }, { 2, 0, 2, 1 }, { 2, 0, 2, 2 }, { 2, 1, 2, 0 }, { 2, 1, 2, 1 }, { 2, 1, 2, 2 },
					{ 2, 2, 2, 0 }, { 2, 2, 2, 1 }, { 2, 2, 2, 2 } }, };

	public static void main(String[] args) throws Exception {
		HttpResponse.BodyHandler<String> stringBodyHandler = HttpResponse.BodyHandlers.ofString();
		boolean printlog = false;
		if (args.length > 0)
			printlog = true;

		boolean cont = true;
		while(cont) {
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://10.55.129.204:8888/api/svetoku/puzzles"))
				.POST(BodyPublishers.ofString("")).build();

		HttpResponse<String> response = client.send(request, stringBodyHandler);

		String temp = response.body();
		final String puzzle = temp.substring(1, temp.length() - 1);
		System.out.println(puzzle);

		long start = System.currentTimeMillis();
		final String[][][][] blocks = new String[3][3][3][3];
		CompletableFuture<HttpResponse<String>> responses[] = new CompletableFuture[9];
		for (int i = 0; i < 9; i++) {
			HttpRequest request2 = HttpRequest.newBuilder().uri(new URI(
					"http://10.55.129.204:8888/api/svetoku/puzzles/" + puzzle + "/blocks/" + (i / 3) + "/" + (i % 3)))
					.GET().build();

			responses[i] = client.sendAsync(request2, stringBodyHandler);
		}

		boolean[] finished = new boolean[9];
		boolean hasNotDone = true;
		while (hasNotDone) {
			hasNotDone = false;
			for (int i = 0; i < 9; i++) {
				if(finished[i]) {
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

		int[][] tt = convert1(blocks);

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
//		for (int i = 0; i < 9; i++) {
//			HttpRequest request3 =  HttpRequest
//					.newBuilder().uri(new URI("http://10.55.129.204:8888/api/svetoku/puzzles/" + puzzle + "/blocks/"
//							+ (i / 3) + "/" + (i % 3)))
//					.PUT(BodyPublishers.ofString(concat(blocks[i / 3][i % 3]))).build();
//
//			HttpResponse<String> response3 = client.send(request3, stringBodyHandler);
//
//			System.out.println(response3.statusCode());
//			String result2 = response3.body();
//			System.out.println(result2);

//			HttpRequest request3 = HttpRequest
//					.newBuilder().uri(new URI("http://10.55.129.204:8888/api/svetoku/puzzles/" + puzzle + "/blocks/"
//							+ (i / 3) + "/" + (i % 3)))
//					.PUT(BodyPublishers.ofString(concat(blocks[i / 3][i % 3]))).build();
//
//			responses[i] = client.sendAsync(request3, stringBodyHandler);
//		}
//
//		responses = new CompletableFuture[9];
//		for (int i = 0; i < 9; i++) {
//			HttpRequest request3 = HttpRequest
//					.newBuilder().uri(new URI("http://10.55.129.204:8888/api/svetoku/puzzles/" + puzzle + "/blocks/"
//							+ (i / 3) + "/" + (i % 3)))
//					.PUT(BodyPublishers.ofString(concat(blocks[i / 3][i % 3]))).build();
//
//			responses[i] = client.sendAsync(request3, stringBodyHandler);
//		}
//
//		finished = new boolean[9];
//		hasNotDone = true;
//		while (hasNotDone) {
//			hasNotDone = false;
//			for (int i = 0; i < 9; i++) {
//				if(finished[i]) {
//					continue;
//				}
//
//				if (responses[i].isDone()) {
//					System.out.println(responses[i].get().body());
//				} else {
//					hasNotDone = true;
//				}
//			}
//		}
	}
	}
	private static String submit(String puzzle, int block1, int block2, String block)
			throws MalformedURLException, IOException {
		System.out.println(block1 + "/" + block2 + block);
		return request("http://10.55.129.204:8888/api/svetoku/puzzles/" + puzzle + "/blocks/" + block1 + "/" + block2, "PUT", block);
	}

	private static String request(String u, String method, String data) throws MalformedURLException, IOException {
		URL url = new URL(u);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(method);
		connection.setRequestProperty("Content-Type", " application/json");
		if (data != null) {
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Length", data.getBytes().length + "");
			connection.connect();
			OutputStream os = connection.getOutputStream();
			os.write(data.getBytes());
			os.flush();
		} else {
			connection.connect();
		}
		InputStream is = connection.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len;
		try {
			while ((len = is.read(b)) > 0) {
				baos.write(b, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(baos.toByteArray());
	}


	private static String[][] parseBlock(String block) {
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

	private static String concat(String[][] block) {
		return "[[" + block[0][0] + "," + block[0][1] + "," + block[0][2] + "],[" + block[1][0] + "," + block[1][1]
				+ "," + block[1][2] + "],[" + block[2][0] + "," + block[2][1] + "," + block[2][2] + "]]";
	}

	private static int[][] convert1(String[][][][] blocks) {
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

	private static String[][][][] convert2(int[][] result) {
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

	private static boolean solve(int[][] board) {
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