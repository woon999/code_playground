package baekjoon.greedy;

// #1339 backtracking
import java.io.*;
import java.util.*;

public class WordMath {

	static int n;
	static List<Integer> list = new ArrayList<>();
	static int[] values;
	static char[][] words;
	static boolean[] visited = new boolean[10];
	static int max;
	static int size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		words = new char[n][];

		for (int i = 0; i < n; i++) {
			words[i] = br.readLine().toCharArray();
			for (int j = 0; j < words[i].length; j++) {
				if (!list.contains(words[i][j] - 65)) {
					list.add(words[i][j] - 65);
				}
			}
		}
		size = list.size();
		values = new int[list.size()];

		solve(0, 0);
		
		System.out.println(max);
	}

	static void solve(int idx, int cnt) {
		if (size == cnt) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				int num = 0;
				for (int j = 0; j < words[i].length; j++) {
					num *= 10;
					num += values[list.indexOf(words[i][j] - 65)];
				}
				sum += num;
			}
			max = Math.max(max, sum);
			return;
		}

		for (int i = 9; i >= 0; i--) {
			if (visited[i])
				continue;

			visited[i] = true;
			values[idx] = i;
			solve(idx + 1, cnt + 1);
			values[idx] = 0;
			visited[i] = false;
		}
	}
}
