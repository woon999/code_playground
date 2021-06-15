package baekjoon.ttone.dp;

// #2089 dp, bitmasking 외판원순회 
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TSP {

	static int n, statusFullBit, INF = 987654321;
	static int[][] w;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		statusFullBit = (1<<n) -1;
		w = new int[n][n];
		dp = new int[n][statusFullBit];
		for(int i=0; i<n; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(tsp(0,1));
	}
	
	static int tsp(int x, int check) {
		if(check == statusFullBit) {
			if(w[x][0] == 0) return INF;
			else return w[x][0];
		}
		if(dp[x][check] != INF) return dp[x][check];
		
		for(int i=0; i<n; i++) {
			int next = check | (1<<i);
			// 경로가 없거나 i 도시를 이미 방문했을 경우 continue 
			if(w[x][i] ==0 || (check & (1<<i)) != 0) continue;
			
			dp[x][check] = Math.min(dp[x][check], tsp(i, next) + w[x][i]);
		}
		
		return dp[x][check];
	}
}
