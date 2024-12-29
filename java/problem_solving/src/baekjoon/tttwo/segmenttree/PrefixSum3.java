package baekjoon.tttwo.segmenttree;

// #11658 prefixsum 구간 합 구하기3 
import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum3 {

	static int[][] arr, dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][n+1];
		dp = new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			 st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i][j-1] + arr[i][j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			if(op == 1) {
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				
				int res = 0;
				for(int x=x1; x<=x2; x++) {
					res += dp[x][y2] - dp[x][y1-1];
				}
				sb.append(res+"\n");
			}else {
				int c = Integer.parseInt(st.nextToken());
				int dif = c - arr[x1][y1];
				for(int y=y1; y<=n; y++) {
					dp[x1][y] += dif; 
				}
				arr[x1][y1] = c;
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void print() {
		for(int i=1; i<arr.length; i++) {
			for(int j=1; j<arr[i].length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("---");
		
		for(int i=1; i<dp.length; i++) {
			for(int j=1; j<dp[i].length; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
