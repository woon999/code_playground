package baekjoon.tttwo.dp;

// #1446 dp 지름길 - 다익스트라 
import java.io.*;
import java.util.*;

public class FastRoute_1446 {

	static class Route {
		int to;
		int cost;
		public Route(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	static List<Route>[] routes;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		routes = new ArrayList[10_001];
		for(int i=0; i<10_001; i++) {
			routes[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			routes[Integer.parseInt(st.nextToken())]
					.add(new Route(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		
		dijkstra(0, d);
	}
	
	static void dijkstra(int s, int e) {
		Queue<Integer> q = new PriorityQueue<>();
		int[] dp = new int[10_001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[s] = 0;
		q.add(s);
		
		while(!q.isEmpty()) {
			int c = q.poll();
			
			if(c == e) {
				System.out.println(dp[e]);
				break;
			}
			
			for(Route r : routes[c]) {
				int nxt = r.to;
				int cost = r.cost;
				
				if(dp[nxt] > dp[c]+cost) {
					dp[nxt] = dp[c]+cost;
					q.add(nxt);
				}
			}
			
			if(dp[c+1] > dp[c]+1) {
				dp[c+1] = dp[c]+1;
				q.add(c+1);	
			}
			
		}
	}
}
