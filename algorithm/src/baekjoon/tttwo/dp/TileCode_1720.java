package baekjoon.tttwo.dp;

// #1720 dp 타일 코드 
import java.io.*;

public class TileCode_1720 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// 2xn (중복 고려 x)
		// 1: 1
		// 2: 3
		// 3: dp[2] + dp[1]*2
		int[] dp = new int[31];

		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i <= 30; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] * 2;
		}

		if (n % 2 == 1) { 
			// 가운데에 1x2 타일을 두고 좌우가 같은 타일들의 경우 dp[n/2] 
			System.out.println((dp[n] + dp[n / 2]) / 2);
		} else {
			// 가운데 2x(1, 2)을 두고 좌우가 같은 경우 2*dp[n/2-1]
			// 가운데에 아무 타일이 없는 경우 dp[n/2] 
			// dp[n/2] + 2*dp[n/2-1] = dp[n/2+1]
			System.out.println((dp[n] + dp[n / 2 +1]) / 2);
		}

	}
}
