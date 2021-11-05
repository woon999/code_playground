package baekjoon.ttone.graph;

// #1238 graph (1238. 파티) -floyd warshall 
import java.io.*;
import java.util.StringTokenizer;

public class Party {

	static int INF = 1_000_000_000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x =  Integer.parseInt(st.nextToken());
		
		int[][] memo = new int[n+1][n+1];
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(i==j) {
					memo[i][j] =0;
				}else {
					memo[i][j] = INF;
				}
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			memo[from][to] = cost;
		}
		
		
		for(int k=1; k<n+1; k++) {
			for(int i=1; i<n+1; i++) {
				for(int j=1; j<n+1; j++) {
					if(memo[i][k] + memo[k][j] < memo[i][j]) {
						memo[i][j] = memo[i][k] + memo[k][j];
					}
				}
			}
		}
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				System.out.print(memo[i][j]+" ");
			}
			System.out.println();
		}
		
		int res = Integer.MIN_VALUE;
		for(int i=1; i<n+1; i++) {
			int dis = memo[i][x] + memo[x][i];
			if(dis >res ) {
				res = dis;
			}
		}
		
		System.out.println(res);
				
	}
	
	
}

