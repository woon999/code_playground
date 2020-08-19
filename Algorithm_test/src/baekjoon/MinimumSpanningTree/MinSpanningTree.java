package baekjoon.MinimumSpanningTree;

// #1197
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MinSpanningTree {

	public static int[] parent;
	public static ArrayList<Edge> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st  = new StringTokenizer(br.readLine());
		int u = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<Edge>();
		
		for(int i=0; i<v; i++) {
			st = new StringTokenizer(br.readLine());
			
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list.add(new Edge(v1,v2,cost));
		}
		
		
		parent = new int[u+1];
		

		for(int i=1; i<u+1; i++) {
			parent[i] = i;
		}
		
		Collections.sort(list);
		int sum=0;
		for(int i=0; i<list.size(); i++) {
			Edge e = list.get(i);
			if(!isSameParent(e.u, e.v)) {
				sum += e.cost;
				union(e.u, e.v);
			}
		}
		
		System.out.println(sum);
		

	}
	
	public static int find(int x) {
		if(parent[x] ==x ) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if(x!= y) {
			parent[y] =x;
			
		}
		
	}
	
	public static boolean isSameParent(int x, int y ) {
		x = find(x);
		y = find(y);
		if(x==y) return true;
		else return false;
		
	}
}

class Edge implements Comparable<Edge> {
	int u;
	int v;
	int cost;

	public Edge(int u, int v, int cost) {
		this.u = u;
		this.v = v;
		this.cost = cost;

	}

	@Override
	public int compareTo(Edge o) {

		if (this.cost < o.cost)
			return -1;
		else if (this.cost == o.cost)
			return 0;
		else
			return 1;
	}
}
