package baekjoon.ttone.tree;

// #1761 tree 정점들의 거리 - LCA with DP
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DistanceOfVertices {

	static class Node{
		int to;
		int w;
		
		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
	
	static int n,h;
	static List<Node>[] list;
	static int[][] dp;
	static int[] dis;
	static int[] depth;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st =null;
		
		list = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w= Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to,w));
			list[to].add(new Node(from,w));
		}
		
		h = getTreeH();
		depth = new int[n+1];
		dis = new int[n+1];
		dp = new int[n+1][h];
		
		init(1,1,0);
		fillParents();
		
//		print();
		
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int res = LCA(a,b);
			sb.append(dis[a] + dis[b] -2*dis[res]).append("\n");
			
		}
		
		System.out.println(sb.toString());
		
	}
	
	static int getTreeH() {
		return (int)Math.ceil(Math.log(n)/Math.log(2))+1;
	}
	
	static void init(int cur, int h, int pa) {
		depth[cur] = h;
		for(Node nxt : list[cur]) {
			if(nxt.to!=pa) {
				dis[nxt.to] = dis[cur] + nxt.w;
				init(nxt.to, h+1, cur);
				dp[nxt.to][0] = cur;
			}
		}
	}
	
	static void fillParents() {
		for(int i=1; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				dp[j][i] = dp[dp[j][i-1]][i-1];
			}
		}
	}
	
	static int LCA(int a, int b) {
		int ah = depth[a];
		int bh = depth[b];
		
		if(ah < bh) {
			int tmp =a;
			a= b;
			b= tmp;
		}
		
		for(int i=h-1; i>=0; i--) {
			if(Math.pow(2, i) <= depth[a] - depth[b]) {
				a = dp[a][i];
			}
		}
		
		if(a==b) return a;
		
		for(int i=h-1; i>=0; i--) {
			if(dp[a][i] != dp[b][i]) {
				a = dp[a][i];
				b = dp[b][i];
			}
		}
		
		return dp[a][0];
		
	}

	static void print() {
		System.out.println("----depth-----");
		for(int i=1; i<n+1; i++) {
			System.out.print(depth[i]+" ");
		}
		System.out.println();
		
		System.out.println("----dis-----");
		for(int i=1; i<n+1; i++) {
			System.out.print(dis[i]+" ");
		}
		System.out.println();
		
		
		System.out.println("----dp(parents)-----");
		for(int i=0; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				System.out.print(dp[j][i]+" ");	
			}
			System.out.println();	
		}
		
	}
}
