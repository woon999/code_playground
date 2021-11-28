package baekjoon.ttone.graph;

// #2887 graph 행성 터널 - MST(크루스칼) 
import java.io.*;
import java.util.*;

public class PlanetTunnel2 {

	static class Node implements Comparable<Node>{
		int to;
		int from;
		int value;
		
		public Node(int to, int from, int value) {
			super();
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

		List<int[]> data = new ArrayList<>();
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st =  new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			data.add(new int[] {i, x, y, z});
		}
		
		
		Queue<Node> q = new PriorityQueue<>();
		for(int idx=1; idx<=3; idx++) {
			int v = idx;
			Collections.sort(data, (o1,o2) -> (o1[v] - o2[v]));
			for(int i=1; i<n; i++) {
				int[] p1 = data.get(i-1);
				int[] p2 = data.get(i);
				int dis = Math.abs(p1[idx]-p2[idx]);
			
				q.add(new Node(p1[0], p2[0], dis));
			}
		}
		
		
		
		parents = new int[n];
		for(int i=0; i<n; i++) {
			parents[i] = i;
		}
		
		int total=0;
		while(!q.isEmpty()) {
			Node node = q.poll();
			int rx = find(node.to);
			int ry = find(node.from);
			
			if(rx != ry) {
				total += node.value;
				union(node.to, node.from);
			}
		}
		System.out.println(total);
	}
	
	static int find(int x) {
		if(parents[x] == x) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		
		parents[y] = x;
	}
}
