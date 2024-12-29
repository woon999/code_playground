package baekjoon.ttzero.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChessRepainting {

	private static char[][] chess;
	private static char[][] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		check = new char[2][8];
		check[0] = "WBWBWBWB".toCharArray();
		check[1] = "BWBWBWBW".toCharArray();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		chess = new char[n][m];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				chess[i][j] = line.charAt(j);
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n - 7; i++) {
			for (int j = 0; j < m - 7; j++) {
				min = Math.min(min, check(i, j));
			}
		}
		System.out.println(min);
	}

	public static int check(int y, int x) {

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 2; i++) {
			int count = 0;
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					if (chess[y + j][x + k] != check[(i + j) % 2][k])
//					i+j -> ó���� W�϶�, B�϶� �� ���
						count++;
				}
			}
			min = Math.min(min, count);
		}
		return min;

	}
}
