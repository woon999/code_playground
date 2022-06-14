package baekjoon.tttwo.dp;

// #2098 dp 외판원 순회 
import java.io.*;
import java.util.*;

public class TSP_2098 {

	static final int INF = 987654321;
	static int n, statusFullBit;
	static int[][] w, dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		statusFullBit = (1<<n) -1; 
		w = new int[n][n];
		dp = new int[n][statusFullBit];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			Arrays.fill(dp[i],-1);
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(tsp(0, 1));
	}
	
	static int tsp(int x, int check) {
		// 모든 도시 방문 완료 
		if(check == statusFullBit) {
			if(w[x][0] == 0) return INF;
			else return w[x][0];
		}
		
		// 이미 방문한 도시 
		if(dp[x][check] != -1) return dp[x][check];
		
		// 해당 도시 출석체크 
		dp[x][check] = INF;
		
		// 방문하지 않은 도시 탐색 
		for(int i=0; i<n; i++) {
			int nxt = check | (1<<i);
			
			if(w[x][i] == 0 || (check & (1<<i)) != 0) continue;
			
			dp[x][check] = Math.min(dp[x][check], tsp(i, nxt) + w[x][i]);
		}
		
		return dp[x][check];
	}
}
