package baekjoon.dp;

// #1535 dp 안녕 (01배낭)
import java.io.*;
import java.util.StringTokenizer;

public class Hi {

	static int n;
	static int[] dp;
	static int[] energy;
	static int[] gift;
	public static void main(String[] args) throws IOException{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		energy = new int[n+1];
		for(int i=1; i<n+1; i++) {
			energy[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		gift = new int[n+1];
		for(int i=1; i<n+1; i++) {
			gift[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[101];
//		bottom-up
//		for(int i=1; i<n+1; i++) {
//			for(int j=99; j>=0; j--) {
//				int ene = energy[i]+j;
//				if(ene<100) {
//					dp[ene] = Math.max(dp[ene], dp[j]+gift[i]);
//				}
//			}
//		}
		
//		for(int j=1; j<101; j++) {
//			System.out.print(dp[j]+" ");
//		}
		System.out.println(dp[99]);
		
		
	}
	
}
