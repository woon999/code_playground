package baekjoon.ttzero.dynamicplanning1;

// #2579
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ClimbingStairs {

	static int[] stair;
	static int[] sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		stair = new int[n + 1];
		sum = new int[n + 1];

		StringTokenizer st;
		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			stair[i] = Integer.parseInt(st.nextToken());
		}

		if (n == 1) {
			sum[1] = stair[1];
		} else if (n == 2) {
			sum[2] = stair[2] + stair[1];
		} else {
			sum[1] = stair[1];
			sum[2] = stair[2] + stair[1];
			sum[3] = Math.max(stair[1] + stair[3], stair[2] + stair[3]);
			for (int i = 4; i < n + 1; i++) {
				sum[i] = Math.max(sum[i - 3] + stair[i] + stair[i - 1], sum[i - 2] + stair[i]);
			}

		}
		

		System.out.println(sum[n]);
	}
}
