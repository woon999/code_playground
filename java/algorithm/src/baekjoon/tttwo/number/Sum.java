package baekjoon.tttwo.number;

// #17425 number 약수의 합  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sum {
	static final int MAX = 1_000_001;
	public static void main(String[] args) throws IOException{
		long[] cSum = getDivisorSum();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		while(t-- >0) {
			sb.append(cSum[Integer.parseInt(br.readLine())]+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static long[] getDivisorSum() {
		long[] dp = new long[MAX];
		Arrays.fill(dp, 1);
		for(int i=2; i<MAX; i++) {
			// i*j의 약수 i 구하기 
			// i*j = 4의 약수는 2*2, 4*1 -> 2와 4
			for(int j=1; i*j<MAX; j++) {
				dp[i*j] += i;
			}
		}
		
		long[] cSum = new long[MAX];
		// 4 124 => 7
		for(int i=1; i<MAX; i++) {
			cSum[i] = cSum[i-1] + dp[i];
		}
		return cSum;
	}
}
