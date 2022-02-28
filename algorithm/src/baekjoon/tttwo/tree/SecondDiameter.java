package baekjoon.tttwo.tree;

// #19581 tree 두 번째 트리의 지름  
import java.io.*;
import java.util.*;

public class SecondDiameter {
	
	static class Node{
		int idx;
		int dis;
		
		Node(int idx, int dis){
			this.idx = idx;
			this.dis = dis;
		}
	}

	static int n;
	static List<Node>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b,d));
			list[b].add(new Node(a,d));
		}
		
		Node f = bfs(1,0);
		Node r = bfs(f.idx,0);
		
		int dis1 = bfs(f.idx, r.idx).dis;
		int dis2 = bfs(r.idx, f.idx).dis;
		System.out.println(Math.max(dis1, dis2));
	}
	
	
	static Node bfs(int s, int e) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		Node endNode = new Node(s,0);
		q.add(endNode);
		visited[s] = true;
		while(!q.isEmpty()) {
			Node p = q.poll();
			
			if(p.dis > endNode.dis && p.idx != e) {
				endNode.idx = p.idx;
				endNode.dis = p.dis;
			}
			
			for(Node nxt : list[p.idx]) {
				if(visited[nxt.idx]) continue;
				visited[nxt.idx] = true;
				
				q.add(new Node(nxt.idx, p.dis + nxt.dis));
				
			}
		}
		
		return endNode;
	}
}
