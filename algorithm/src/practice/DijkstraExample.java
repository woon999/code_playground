package practice;

import java.util.*;

public class DijkstraExample {

	static List<Node>[] list;
	static int[] dp;
	static boolean[] check; 
	
	public static void main(String[] args) {
		int[][] a = {{1,2,2}, {1,4,1}, {1,3,5}, {2,3,3}, {2,4,2},
				{3,4,3}, {3,5,1}, {4,5,1}, {5,6,2}, {3,6,5}};
		
		int n =6;
		
		solution(n,a);
	}
	
	static void solution(int n , int maps[][]) {
		check = new boolean[n+1];
		dp = new int[n+1];
		
		list = new ArrayList[n+1];
		
		for(int i=1; i<n+1; i++	) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<maps.length; i++) {
			int a = maps[i][0];
			int b = maps[i][1];
			int c = maps[i][2];
			
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
		}
		
		dijkstra(1);
		
		for(int num : dp) {
			System.out.print(num +" ");
		}
	}
	
	
	static void dijkstra(int start) {
		Queue<Node> q = new PriorityQueue<>();
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		q.add(new Node(start,0));
		
		dp[start] = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int to = node.to;
			
			if(check[to]) continue;
			else check[node.to] = true;
			
			for(Node nxt : list[to]) {
				if(dp[nxt.to]  >= dp[to] + nxt.weight) {
					dp[nxt.to] = dp[to] + nxt.weight;
					q.add(new Node(nxt.to, dp[nxt.to]));
				}
			}
		}
		
		
		
	}
}

class Node implements Comparable<Node>{
	int to;
	int weight;
	
	Node(int to, int weight){
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.weight, o.weight);
	}
}
