package baekjoon.ttone.graph;

// #1647 graph 도시 분할 계획 - prim  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class CityDivisionPlan {

	static class Node implements Comparable<Node>{
		int to;
		int w;
		
		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	static List<Node>[] list;
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
		}
		int res = prim(1);
		System.out.println(res);
	}
	
	static int prim(int start) {
		Queue<Node> q = new PriorityQueue<>();
		boolean[] checked = new boolean[n+1];
		q.add(new Node(start,0));
		
		int cnt=0;
		int connect=0;
		int maxLine =0;
		while(!q.isEmpty()) {
			Node now = q.poll();
			int to = now.to;
			int w = now.w;
			if(checked[to]) continue;
			
			checked[to] = true;
			cnt += w;
			maxLine = Math.max(maxLine, w); 
			connect++;
			
			if(connect == n) break;
			
			for(Node nxt : list[to]) {
				if(!checked[nxt.to]) {
					q.add(nxt);
				}
			}
		}
		return cnt-maxLine;
	}
}
