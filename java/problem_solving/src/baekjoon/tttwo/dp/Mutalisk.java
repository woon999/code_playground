package baekjoon.tttwo.dp;

// #12869 dp 뮤탈리스크 
import java.io.*;
import java.util.*;

public class Mutalisk {

	static int n;
	static final int HEALTH_SIZE = 61;
	static int[][][] dp;
	static List<int[]> testList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[3];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		testList = new ArrayList<>();
		int[] damage = { 1, 3, 9 };
		makeTestcase(damage, new Stack<Integer>());

		dp = new int[HEALTH_SIZE][HEALTH_SIZE][HEALTH_SIZE];
		for (int i = 0; i < HEALTH_SIZE; i++) {
			for (int j = 0; j < HEALTH_SIZE; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(dfs(arr[0], arr[1], arr[2]));
	}

	static void makeTestcase(int[] damage, Stack<Integer> stack) {
		if (stack.size() == 3) {
			int[] result = new int[3];
			int idx = 0;
			for (int s : stack) {
				result[idx++] = s;
			}
			testList.add(result);
			return;
		}

		for (int i = 0; i < 3; i++) {
			int num = damage[i];
			if (!stack.contains(num)) {
				stack.push(damage[i]);
				makeTestcase(damage, stack);
				stack.pop();
			}
		}
	}

	static int dfs(int a, int b, int c) {
		if (a < 0) {
			return dfs(0, b, c);
		}
		if (b < 0) {
			return dfs(a, 0, c);
		}
		if (c < 0) {
			return dfs(a, b, 0);
		}
		if (a == 0 && b == 0 && c == 0) {
			return 0;
		}
		if (dp[a][b][c] != -1) {
			return dp[a][b][c];
		}

		dp[a][b][c] = Integer.MAX_VALUE;
		for (int[] damage : testList) {
			dp[a][b][c] = Math.min(dp[a][b][c], dfs(a - damage[0], b - damage[1], c - damage[2]) + 1);
		}
		return dp[a][b][c];
	}
}
