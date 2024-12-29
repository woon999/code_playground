package baekjoon.tttwo.dp;

// #1727 dp 커플 만들기 
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MakeCouple {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] mArr = new int[n+1];
		int[] wArr = new int[m+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			mArr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<m+1; i++) {
			wArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(mArr);
		Arrays.sort(wArr);
		
		// dp[i][j] = 1~i, 1~j 커플 매칭했을 때의 최솟값 
		int[][] dp = new int[n+1][m+1];
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<m+1; j++) {
				dp[i][j] = dp[i-1][j-1]  + Math.abs(mArr[i]-wArr[j]);
 
				if(i>j) { // 남자의 수가 더 많으면
					dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
				}else if(i<j) { // 여자의 수가 더 많으면 
					dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[n][m]);
		
	}
}

