package baekjoon.tttwo.dp;

// #2591 dp 숫자카드 
import java.io.*;

public class NumberCard_2591 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] card = br.readLine().toCharArray();
		int len = card.length;

		int prevNum = (card[0] - '0') * 10;
		/**
		 * dp[n]: 1 ~ n번째 수 
		 * dp[n][0]: 1의 자리 
		 * dp[n][1]: 10의 자리
		 */
		int[][] dp = new int[41][2];
		dp[1][0] = 1;

		for (int i = 2; i <= len; i++) {
			int curNum = card[i - 1] - '0';
			if (curNum == 0) {
				if (prevNum + curNum <= 34) {
					dp[i][1] = dp[i - 1][0];
				}
				continue;
			}

			dp[i][0] = dp[i - 1][1] + dp[i - 1][0];
			if (prevNum + curNum <= 34) {
				dp[i][1] = dp[i - 1][0];
			} 

			prevNum = curNum * 10;
		}

		System.out.println(dp[len][0] + dp[len][1]);
	}
}
