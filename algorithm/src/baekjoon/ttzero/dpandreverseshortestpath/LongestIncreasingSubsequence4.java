package baekjoon.ttzero.dpandreverseshortestpath;

// #14002
// dp풀이 O(n^2)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class LongestIncreasingSubsequence4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n + 1];
		int[] dp = new int[n + 1];

		int result = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
					result = Math.max(dp[i], result);
				}
			}
		}
//		System.out.println(Arrays.toString(dp));
		sb.append(result + " \n");

		int len = result;
		Stack<Integer> s = new Stack<Integer>();

		for (int i = n; i > 0; i--) {
			if (dp[i] == len) {
				s.push(arr[i]);
				len--;
			}

		}
		while (!s.isEmpty()) {
			sb.append(s.pop() + " ");
		}

		System.out.println(sb.toString());

	}
}
