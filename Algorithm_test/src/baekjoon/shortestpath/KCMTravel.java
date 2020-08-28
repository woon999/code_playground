package baekjoon.shortestpath;

// #10217
import java.io.*;
import java.util.*;

public class KCMTravel {

	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int INF = 567891234;
	static int n,m,k;
	static List<Airplane> list[];
	static int[][] dp;
	static int result;
	public static void main(String[] args) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<test; t++) {
		
			init();
			dijkstra();
			sb.append(result == INF ? "Poor KCM\n" : result +"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[n+1][m+1];
		list = new ArrayList[n+1];
		
		for(int i=1; i<n+1; i++) {
			Arrays.fill(dp[i], INF);
		}
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			list[u].add(new Airplane(v,c,d));
		}
		result = Integer.MAX_VALUE;
		
	}
	
	static void dijkstra() {		
		Queue<Airplane> q = new PriorityQueue<>();
		q.add(new Airplane(1,0,0));
		dp[1][0] = 0;
		
		while(!q.isEmpty()) {
			Airplane a = q.poll();
			int node = a.end;
			int cost = a.cost;
			int time = a.time;
			
			
			if(node == n)break;
			if(dp[node][cost] < time) continue;
			dp[node][cost] = time;
			
			for(int i=0; i<list[node].size(); i++) {
				Airplane toA = list[node].get(i);
				int toNode = toA.end;
				int toCost = cost + toA.cost;
				int toTime = time + toA.time;
				
				if(toCost >m) continue;
				if(dp[toNode][toCost] > toTime) {
					for(int j = toCost; j<=m; j++) {
						if(dp[toNode][j] > toTime) dp[toNode][j] = toTime;
					}
					
					q.add(new Airplane(toNode, toCost, toTime));
				}
			}
		}
		
		for(int i=1; i<m+1; i++) {
			result = Math.min(result, dp[n][i]);
		}
	}
	
	static class Airplane implements Comparable<Airplane>{

		int end;
		int cost;
		int time;
		
		Airplane(int end, int cost, int time){
			this.end = end;
			this.cost = cost;
			this.time = time;
		}
		@Override
		public int compareTo(Airplane o) {
			if(this.time - o.time ==0) return cost - o.cost;
			return this.time - o.time;
		}
		
	}
}
