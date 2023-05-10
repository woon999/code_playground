package baekjoon.ttone.graph;

// #5719 graph 거의 최단 경로 - 다익스트라 
import java.io.*;
import java.util.*;

public class AlmostShortPath {

	static class Node implements Comparable<Node>{
		int to;
		int w;
		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}
		
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	static final int MAX = Integer.MAX_VALUE;
	static int n,m;
	static int[] dp;
	static boolean[][] exRoute;
	static List<Node>[] list;
	static List<Integer>[] removeList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String input = null;
		StringBuilder sb = new StringBuilder();
		while(!(input=br.readLine()).equals("0 0")) {
			st = new StringTokenizer(input);
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[n];
			removeList = new ArrayList[n];
			dp = new int[n];
			for(int i=0; i<n; i++) {
				list[i] = new ArrayList<>();
				removeList[i] = new ArrayList<>();
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				list[u].add(new Node(v,cost));
			}
			
			exRoute = new boolean[n][n];
			dijkstra(s);
			removeMinRouteVertex(s,d);
			dijkstra(s);
			sb.append(dp[d]== MAX? -1 : dp[d]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	static void initDp() {
		Arrays.fill(dp, MAX);
	}
	
	static void removeMinRouteVertex(int s, int d) {
		if(s==d) return;
		for(int nxt : removeList[d]) {
			if(!exRoute[nxt][d]) {
				exRoute[nxt][d] = true;
				removeMinRouteVertex(s, nxt);
			}
		}
	}
	
	static void dijkstra(int s) {
		Queue<Node> q = new PriorityQueue<>();
		initDp();
		dp[s] = 0;
		q.add(new Node(s,0));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int cur = node.to;
			if(node.w > dp[cur]) continue;
			for(Node nxt : list[cur]) {
				if(exRoute[cur][nxt.to]) continue;
				if(dp[nxt.to] > dp[cur] + nxt.w) {
					dp[nxt.to] = dp[cur] + nxt.w;
					removeList[nxt.to] = new ArrayList<>();
					removeList[nxt.to].add(cur);
					q.add(new Node(nxt.to, dp[nxt.to]));	
				}else if(dp[nxt.to] == dp[cur] + nxt.w) {
					removeList[nxt.to].add(cur);
				}
			}
		}
	}
	
	static void print() {
		System.out.println("-----");
		for(int num : dp) {
			System.out.print(num+" ");
		}
		System.out.println();
		
		for(int i=0; i<n; i++) {
			System.out.println(i);
			for(Node n : list[i]) {
				System.out.print("("+n.to + " - " + n.w+") ");
			}
			System.out.println();
		}
		System.out.println("-----");
	}
}
