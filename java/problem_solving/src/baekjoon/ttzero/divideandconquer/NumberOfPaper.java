package baekjoon.ttzero.divideandconquer;

// #1780
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberOfPaper {

	static int[][] paper;
	static int minus, zero, plus;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		minus = 0;
		zero = 0;
		plus = 0;
		paper = new int[n][n];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		divide(0, 0, n);
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(plus);
	}

	static void divide(int x, int y, int n) {
		if (check(x, y, n)) {
			if (paper[y][x] == -1) {
				minus++;
			} else if (paper[y][x] == 0) {
				zero++;
			} else
				plus++;
		} else {
			int size = n / 3;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					divide(x + size * i, y + size * j, size);
				}
			}
		}

	}

	static boolean check(int x, int y, int n) {
		int paperNum = paper[y][x];

		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if (paperNum != paper[i][j])
					return false;
			}
		}

		return true;
	}
}
