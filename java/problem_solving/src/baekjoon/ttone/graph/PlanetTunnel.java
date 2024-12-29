package baekjoon.ttone.graph;

// #2887 graph 행성 터널 - MST(프림) 
import java.io.*;
import java.util.*;

public class PlanetTunnel {

	static class Node implements Comparable<Node>{
		int to;
		int value;
		
		public Node(int to, int value) {
			this.to = to;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return this.value - o.value;
		}
		
	}
	
	static int n;
	static List<Node>[] list;
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
			data.add(new int[] {i, x,y,z});
		}
		
		list= new ArrayList[n+1];
		for(int i=0; i<n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int idx=1; idx<=3; idx++) {
			int v = idx;
			Collections.sort(data, (o1,o2) -> (o1[v] - o2[v]));
			for(int i=1; i<n; i++) {
				int[] p1 = data.get(i-1);
				int[] p2 = data.get(i);
				int dis = Math.abs(p1[idx]-p2[idx]);
				
				list[p1[0]].add(new Node(p2[0],dis));
				list[p2[0]].add(new Node(p1[0],dis));
			}
		}
		
		prim(0);
		
	}
	
	static void prim(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		boolean[] checked = new boolean[n];
		pq.add(new Node(start, 0));
		
		long total=0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int node = cur.to;
			long value = cur.value;
			
			if(checked[node]) continue;
			
			checked[node] = true;
			total += value;
			
			for(Node nxt : list[node]) {
				if(!checked[nxt.to]) {
					pq.add(nxt);
				}
			}
		}
		System.out.println(total);
	}
	
}
