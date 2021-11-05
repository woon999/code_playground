package baekjoon.ttone.dp;

// #2629 dp topdown 양팔저울 
import java.io.*;
import java.util.*;

public class TwoArmScales {
	
	static int n;
	static int[] w;
	static boolean[][] result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		w = new int[n];
		result = new boolean[n+1][40001];
		for(int i=0; i<n; i++) {
			int src = Integer.parseInt(st.nextToken());
			w[i] = src;
		}
		
		dp(0,0);
		
		for(int j=0; j<20; j++) {
			if(result[n][j]) {
				System.out.println(j);
				System.out.print(result[n][j]+" ");
			}
		}
		
		int c = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<c; i++) {
			int t = Integer.parseInt(st.nextToken());
			
			if(result[n][t]) {
				sb.append("Y ");
			}else {
				sb.append("N ");
			}
		}
	
		System.out.println(sb.toString());
	}
	
	static void dp(int cnt, int num) {
		if(result[cnt][num]) return;
		result[cnt][num] = true;
		
		if(cnt == n	) return;
		
		dp(cnt+1, num+ w[cnt]);
		dp(cnt+1, num);
		dp(cnt+1, Math.abs(num- w[cnt]));
		
	}
}
