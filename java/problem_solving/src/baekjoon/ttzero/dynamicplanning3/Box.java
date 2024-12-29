package baekjoon.ttzero.dynamicplanning3;

// #1965
import java.io.*;
import java.util.StringTokenizer;

public class Box {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			num[i] = Integer.parseInt(st.nextToken());

		}

		int[] dp = new int[n + 1];
		dp[0] =1;
		int max = 0;
		for (int i = 1; i < n + 1; i++) {
			dp[i] =1;
			for (int j = 1; j < i; j++) {
				if (num[j] < num[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
			max = max < dp[i] ? dp[i] : max;
		}
		
		System.out.println(max);
	}
	

}
