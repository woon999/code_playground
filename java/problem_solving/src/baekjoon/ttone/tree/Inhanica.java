package baekjoon.ttone.tree;

// #12784 tree 인하니카 공화국 
import java.io.*;
import java.util.*;

public class Inhanica {

	static class Node{
		int to;
		int w;
		
		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
	static int n,m, INF = 987654321;
	static List<Node>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[n+1];
			for(int i=1; i<n+1; i++) {
				list[i] = new ArrayList<>();	
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				list[a].add(new Node(b,w));
				list[b].add(new Node(a,w));
			}
			int res = dfs(1, -1, INF);
			
			sb.append((res==INF? 0:res) +"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int dfs(int here, int pa, int bomb) {
		int ret=0;
		for(Node nxt : list[here]) {
			if(pa != nxt.to) {
				ret += dfs(nxt.to, here, nxt.w);
			}
		}
		if(ret == 0) { // leaf 
			ret = bomb;
		}
		return Math.min(ret, bomb);
		
	}
}
