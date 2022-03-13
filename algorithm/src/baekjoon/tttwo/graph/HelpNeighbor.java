package baekjoon.tttwo.graph;

// #1414 graph 불우이웃돕기 - 크루스칼 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class HelpNeighbor{

	static class Node implements Comparable<Node>{
		int to;
		int from;
		int value;
		public Node(int to, int from, int value) {
			this.to = to;
			this.from = from;
			this.value = value;
		}
		@Override
		public int compareTo(Node o) {
		return this.value - o.value;
		}
	}
	static int n;
	static int[] parents;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		char[][] map = new char[n][n];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		
		parents = new int[n+1];
		for(int i=1; i<=n; i++) {
			parents[i] = i;
		}
		
		int total = 0;
		Queue<Node> pq = new PriorityQueue<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if('a' <= map[i][j] && map[i][j] <= 'z') {
					total += map[i][j]-96;
					pq.add(new Node(i+1,j+1, map[i][j]-96));
				}else if('A' <= map[i][j] && map[i][j] <= 'Z') {
					total += map[i][j]-38;
					pq.add(new Node(i+1,j+1, map[i][j]-38));
				}
			}
		}
		
		
		int size = pq.size();
		int cycleNode = 1;
		int result = 0;
		for(int i=0; i<size; i++) {
			Node node = pq.poll();
			int rx = find(node.to);
			int ry = find(node.from);
			
			if(rx != ry) {
				cycleNode++;
				result += node.value;
				union(node.to, node.from);
			}
			
		}
		
		if(cycleNode != n) {
			System.out.println(-1);
		}else {
			System.out.println(total-result);	
		}
	}
	
	static int find(int x) { 
		if (parents[x] == x) { 
			return x; 
	     } 
		return parents[x] = find(parents[x]);
	} 
	     
	static void union(int x, int y) {
		x = find(x); 
		y = find(y); 
	    if (x < y) { 
	    	parents[y] = x; 
	    } 
	    else { 
	    	parents[x] = y; 
	    } 
	}
	
}
