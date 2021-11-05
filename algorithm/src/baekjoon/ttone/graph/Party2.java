package baekjoon.ttone.graph;

// #1238 graph (1238. 파티) - 다익스트라  
import java.io.*;
import java.util.*;

class Town implements Comparable<Town>{
	int to;
	int cost;
	
	public Town(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Town o) {
		return this.cost - o.cost;
	}
	
}

public class Party2 {

	static int n,m,x;
	static List<Town>[] nList;
	static List<Town>[] rList;
	static int INF = 1_000_000_000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x =  Integer.parseInt(st.nextToken());
		
		nList = new ArrayList[n+1];
		rList = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			nList[i] = new ArrayList<>();
			rList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			nList[from].add(new Town(to, cost));
			rList[to].add(new Town(from, cost));
		}
		
		// 다른 모든 마을 -> X 마을 거리
		int[] go = dijkstra(nList, x);
		// X 마을 -> 다른 모든 마을 
		int[] back = dijkstra(rList, x);
		
		System.out.println("==========");
		for(int i=1; i<n+1; i++) {
			System.out.print(go[i]+" ");
		}
		System.out.println();
		System.out.println("==========");
		for(int i=1; i<n+1; i++) {
			System.out.print(back[i]+" ");
		}
		System.out.println();
		
		int res = Integer.MIN_VALUE;
		for(int i=1; i<n+1; i++) {
			int dis = go[i] + back[i];
			
			if(dis > res) { 
				res = dis;
			}
		}
		
		System.out.println(res);
				
	}
	
	static int[] dijkstra(List<Town>[] list, int start) {
		Queue<Town> q = new PriorityQueue<>();
		boolean[] check = new boolean[n+1];
		int[] dp = new int[n+1];
		Arrays.fill(dp, INF);
		
		q.add(new Town(start, 0));
		dp[start]=0;
		
		while(!q.isEmpty()) {
			Town pos = q.poll();
			
			int to = pos.to;
			
			if(check[to]) continue;
			
			check[to] = true;
			
			for(Town next : list[to]) {
				if(dp[to] + next.cost < dp[next.to]) {
					dp[next.to] = dp[to] + next.cost;
					q.add(new Town(next.to, dp[next.to]));
				}
			}
		}
		
		return dp;
		
	}
	
	
}


