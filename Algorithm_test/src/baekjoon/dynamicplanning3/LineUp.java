package baekjoon.dynamicplanning3;

// #2631
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LineUp {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n + 1];
		int[] dp = new int[n + 1];

		int res = 0;

		for (int i = 1; i < n + 1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i < n + 1; i++) {
			dp[i] = 1;
			for (int j = 1; j < i; j++) {
				if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
					dp[i]++;
				}

				res = (res < dp[i]) ? dp[i] : res;
			}
		}

		System.out.println(n- res);

	}
}
