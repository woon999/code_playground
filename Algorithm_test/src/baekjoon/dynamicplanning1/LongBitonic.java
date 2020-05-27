package baekjoon.dynamicplanning1;

// #11054
import java.io.*;
import java.util.StringTokenizer;

public class LongBitonic {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] bitonic = new int[n];
		int[][] memo = new int[2][n];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			bitonic[i] = Integer.parseInt(st.nextToken());
		}

		memo[0][0] =1;
		memo[1][n-1] =1;

		for (int i = 1; i < n; i++) {
			memo[0][i] = 1;
			for (int j = 0; j < i; j++) {
				if (bitonic[i] > bitonic[j]) {
					memo[0][i] = Math.max(memo[0][i], memo[0][j]+1);
				}
			}
		}
		for (int i = n-2; i >=0; i--) {
			memo[1][i] = 1;
			for (int j = n-1; j >i; j--) {
				if (bitonic[i] > bitonic[j]) {
					memo[1][i] = Math.max(memo[1][i], memo[1][j]+1);
				}
			}
		}
	
		
		for(int i=0; i<n; i++) {
			memo[0][i] += memo[1][i];
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<n; i++) {
			max = Math.max(memo[0][i], max);
		}
		
		System.out.println(max-1);
	}
}
