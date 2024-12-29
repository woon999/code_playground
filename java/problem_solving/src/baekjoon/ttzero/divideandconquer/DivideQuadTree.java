package baekjoon.ttzero.divideandconquer;

// #1992
import java.io.*;

public class DivideQuadTree {

	static StringBuilder sb = new StringBuilder();;
	static int tree[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		tree = new int[n][n];

		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				tree[i][j] = Integer.parseInt(line.substring(j, j + 1));
			}
		}
		divide(0, 0, n);

		System.out.println(sb);
	}

	static void divide(int x, int y, int n) {
		if (check(x, y, n)) {
			sb.append(tree[y][x]);
			return;
		}
		sb.append("(");
		divide(x, y, n / 2);
		divide(x + n / 2, y, n / 2);
		divide(x, y + n / 2, n / 2);
		divide(x + n / 2, y + n / 2, n / 2);
		sb.append(")");

	}

	static boolean check(int x, int y, int n) {
		int color = tree[y][x];
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if (color != tree[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
/*
	
	
*/