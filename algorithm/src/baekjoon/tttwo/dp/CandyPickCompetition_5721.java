package baekjoon.tttwo.dp;

// #5721 dp 사탕 줍기 대회 
import java.io.*;
import java.util.StringTokenizer;

public class CandyPickCompetition_5721 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			if(x==0 && y==0) {
				break;
			}
			
			int[][] map = new int[y][x];
			for(int i=0; i<y; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<x; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[y][x];
			for(int i=0; i<y; i++) {
				dp[i][0] = map[i][0];
				if(x>1) {
					dp[i][1] = Math.max(dp[i][0], map[i][1]);
					for(int j=2; j<x; j++) {
						dp[i][j] = Math.max(dp[i][j-1], dp[i][j-2] + map[i][j]);  
					}
				}
			}
			
			int[] result = new int[y];
			result[0] = dp[0][x-1];
			if(y>1) {
				result[1] = Math.max(result[0],dp[1][x-1]);
				for(int i=2; i<y; i++) {
					result[i] = Math.max(result[i-1], result[i-2]+dp[i][x-1]);
				}
			}
			
			System.out.println(result[y-1]);
		}
		
	}

}
