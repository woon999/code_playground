package baekjoon.backtracking;

// #2580

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sudoku {

	static int[][] sudoku = new int[9][9];
	static ArrayList<int[]> list = new ArrayList<>();
	static boolean check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
				if (sudoku[i][j] == 0) {
					list.add(new int[] { i, j });
				}
			}
		}
		backtracking(0, 0);

	}

	static void backtracking(int index, int count) {
		if (count == list.size()) {
			print();
			check = true;
			return;
		}
		if (check)
			return;
		if (index >= list.size())
			return;

		int x = list.get(index)[0];
		int y = list.get(index)[1];

		for (int i = 1; i < 10; i++) {
			if (check(x, y, i)) {
				sudoku[x][y] = i;
				backtracking(index + 1, count + 1);
				sudoku[x][y] = 0;
			}

		}

	}

	static boolean check(int x, int y, int n) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[x][i] == n)
				return false;

			if (sudoku[i][y] == n)
				return false;
		}

//		3x3 중복 검사
		int s_x = x / 3 * 3;
		int s_y = y / 3 * 3;

		for (int i = s_x; i < s_x + 3; i++) {
			for (int j = s_y; j < s_y + 3; j++) {
				if (sudoku[i][j] == n)
					return false;
			}
		}

		return true;
	}

	static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(sudoku[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
