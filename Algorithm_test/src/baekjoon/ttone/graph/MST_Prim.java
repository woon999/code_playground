package baekjoon.ttone.graph;

// #1197 graph 최소 스패닝 트리 - Prim 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class MST_Prim {

	static int v,e, total;
	static List<Node>[] list;
	static boolean[] visited;
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
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[v+1];
		visited = new boolean[v+1];
		for(int i=1; i<v+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,w));
			list[b].add(new Node(a,w));
		}
		
		prim(1);
		System.out.println(total);
	}
	
	static void prim(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(start,0));
		while(!pq.isEmpty()) {
			Node p = pq.poll();
			int node = p.to;
			int weight = p.value;
			
			if(visited[node]) continue;
			// 선택한 간선의 정점으로부터 가장 낮은 가중치 갖는 정점 선택 
			visited[node]= true;
			total += weight;
			
			for(Node next : list[node]) {
				if(!visited[next.to]) {
					pq.add(next);
				}
			}
		}
		
	}
}
