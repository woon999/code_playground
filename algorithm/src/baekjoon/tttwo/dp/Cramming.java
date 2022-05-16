package baekjoon.tttwo.dp;

// #14728 dp 벼락치기 - 배낭 문제 
import java.io.*;
import java.util.StringTokenizer;

public class Cramming {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		
		// study[i][0] = time, study[i][1] = score  
		int[][] study = new int[n+1][2];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			study[i][0] = Integer.parseInt(st.nextToken());
			study[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i][t] = score, 1~i번 문제 중 t시간 내에 얻을 수 있는 최대 점수 
		int[][] dp = new int[n+1][t+1];
		dp[0][0] = study[0][0];
		dp[0][1] = study[0][1];
		for(int i=1; i<=n; i++) {
			for(int j=0; j<=t; j++) {
				if(study[i][0] <= j) { // 해당 단원 공부할 시간이 있다면
					// 1~i-1번 문제 중 j시간내에 얻을 수 있는 최대 점수 vs 1~i-1번 문제 중 (j-i번 문제 시간) + i번 문제 점수 
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-study[i][0]] + study[i][1]); 
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[n][t]);
	}
	
}
