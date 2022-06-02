package baekjoon.tttwo.graph;

// #21317 graph 징검다리 건너기 - dfs 
import java.io.*;
import java.util.*;

public class CrossTheSteppingBridge_21317 {

	static int n, k, answer = Integer.MAX_VALUE;
	static int[] dp;
	static int[][] ene;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		ene = new int[n+1][2];
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			ene[i+1][0] = Integer.parseInt(st.nextToken()); // 작은 점프 
			ene[i+1][1] = Integer.parseInt(st.nextToken()); // 큰 점프 
		}
		
		k = Integer.parseInt(br.readLine());
		
		// 작은 점프 +1(ene[][0]), 큰 점프 +2(ene[][1]), 매우 큰 점프 +3(k, 단 1번)
		dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dfs(1, 0, true);
		System.out.println(answer);
	}
	
	static void dfs(int num, int total, boolean flag) {
		if(num == n) {
			answer = Math.min(answer, total);
		}
		if(num > n) return ;
		
		dfs(num+1, total+ene[num][0], flag);
		dfs(num+2, total+ene[num][1], flag);
		if(flag) {
			dfs(num+3, total+k, !flag);
		}
	}
	
}
