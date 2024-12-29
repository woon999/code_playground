package baekjoon.ttzero.dynamicplanning3;

// #1005
import java.io.*;
import java.util.*;

public class ACMCraft {
	
	static int n,k;
	static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			dp = new int[n+1];
			
			List<List<Integer>> list = new ArrayList<List<Integer>>();
			
			for(int i=0; i<n+1; i++)
				list.add(new ArrayList<Integer>());
			
			int[] cnt = new int[n+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<n+1; i++) {
				dp[i] = Integer.parseInt(st.nextToken());
			}
			
			
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				
				// v1 -> v2
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				
				list.get(v1).add(v2);
				cnt[v2]++;
			}
			
			int w = Integer.parseInt(br.readLine());
			
			
			topologicalSort(cnt, list, w);	
			
		}
	}
	
	static void topologicalSort(int[] cnt, List<List<Integer>> list, int w) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		int[] res = new int[n+1];
		
		for(int i=1; i<n+1; i++) {
			res[i] = dp[i];
			
			if(cnt[i]==0) q.offer(i);
		}
		
		
		// 건물의 총 소요시간 = 이전까지의 소요시간 + 현재 건물 소요시간
		while(!q.isEmpty()) {
			int node = q.poll();
			
			for(Integer i : list.get(node)) {
				res[i] = Math.max(res[i], res[node] + dp[i]); //이전 테크가 다 올라야 현재 건물을 지음 
				cnt[i]--;
				
				if(cnt[i] ==0) q.offer(i);
			}
		}
		
		System.out.println(res[w]);
	}

}
