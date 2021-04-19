package baekjoon.ttzero.dynamicplanning3;

// #2533
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SNS {

	static int[] visit;
	static int[][] dp;
	static LinkedList<Integer>[] list;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n  = Integer.parseInt(br.readLine());
		
		visit = new int[n+1];
		dp = new int[n+1][2];
		list = new LinkedList[n+1];
		
		for(int i=1; i<n+1; i++) {
			list[i] = new LinkedList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
		 	
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		int start = 1;
		dfs(start);
		
		System.out.println(Math.min(dp[start][0], dp[start][1]));
		
	}
	
	public static void dfs(int idx) {
		visit[idx] = 1;
		dp[idx][0] = 0; //얼리어답터 x
		dp[idx][1] = 1; //얼리어답터 o
		
		LinkedList<Integer> item = list[idx];
		
		for(int i: item) {
			if(visit[i] == 0) {
				dfs(i);
				dp[idx][0] += dp[i][1];
				dp[idx][1] += Math.min(dp[i][0], dp[i][1]);
				
//				System.out.println("idx : "+idx +", dp 0 : "+ dp[idx][0] + ", dp 1: "+ dp[idx][1]);
			}
		}
	}
}
