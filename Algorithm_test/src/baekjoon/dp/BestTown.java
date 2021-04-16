package baekjoon.dp;

// #1949 dp 우수마을 (dp, dfs, tree)
import java.io.*;
import java.util.*;

public class BestTown {

	static int[] town;
	static List<Integer>[] list;
	static int[][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		town = new int[n+1];
		for(int i=1; i<n+1; i++) {
			town[i] = Integer.parseInt(st.nextToken());
		}
		
		
		list = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		dp = new int[n+1][2];
		
		for(int i=0; i<n+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		
//		System.out.println(solve(1, -1, 1)); // 1번 우수마을 선정 후 시작 
//		System.out.println(solve(1, -1, 0)); // 1번 우수마을 x 후  시작
		
		System.out.println(Math.max(solve(1, -1, 1)+town[1], solve(1, -1, 0)));
		
//		for(int i=0; i<2; i++) {
//			for(int j=0; j<n+1; j++	) {
//				System.out.print(dp[j][i]+" ");
//			}
//			System.out.println();
//		}
		
	}
	static int solve(int pos, int prev, int flag) {

		
		if(dp[pos][flag] != -1) return dp[pos][flag];
		
		dp[pos][flag] =0;
		for(int i=0; i<list[pos].size(); i++) {
			int next = list[pos].get(i);
			// 왔던 길 반복 x 
			if(next != prev) {
				if(flag==1) { // 이전의 인접 노드(prev)가 우수 마을일 경우 
					dp[pos][flag] += solve(next, pos, 0); 
				}else { //이전의 인접노드(prev)가 우수 마을이 아닐 경우
					// 이전의 인접노드(prev)와 연결된 노드 중 우수 마을이 1개 나와야하므로 dfs탐색으로 최대값 구하기 
					dp[pos][flag] += Math.max(solve(next, pos, 1)+town[next], solve(next, pos, 0));
				}
			}
			
		}
		
		return dp[pos][flag];
	}
	
	
}


