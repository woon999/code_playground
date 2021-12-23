package baekjoon.ttone.tree;

// #15480 tree LCA와 쿼리 - LCA 
import java.io.*;
import java.util.*;

public class LCAAndQuery {

	static int n, h;
	static List<Integer>[] list;
	static int[] depth;
	static int[][] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++){
			list[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
	
		h = (int)Math.ceil(Math.log(n)/Math.log(2)) +1;
		parent = new int[n+1][h];
		depth = new int[n+1];
		
		initTree(1,0);
		fillParents();
		
//		print();
		
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(comparingDepth(LCA(r,a), comparingDepth(LCA(r,b), LCA(a,b)))+"\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	static int comparingDepth(int a, int b) {
		if(depth[a] > depth[b]) {
			return a;
		}else {
			return b;
		}
	}
	
	static int LCA(int a, int b) {
		int ah = depth[a];
		int bh = depth[b];
		if(ah < bh) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		for(int i=h-1; i>=0; i--) {
			if(Math.pow(2, i) <= depth[a] - depth[b]) {
				a = parent[a][i];
			}
		}
		
		if(a == b) return a;
		
		for(int i=h-1; i>=0; i--) {
			if(parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		return parent[a][0];
	}
	
	static void initTree(int idx, int pa) {
		for(int nxt : list[idx]) {
			if(nxt != pa) {
				depth[nxt] = depth[idx]+1;
				initTree(nxt, idx);
				parent[nxt][0] = idx; 
			}
		}
	}
	
	static void fillParents() {
		for(int i=1; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
			}
		}
	}
	
	static void print() {
		System.out.println("----- depth -----");
		for(int i=1; i<n+1; i++) {
			System.out.print(depth[i]+" ");
		}
		System.out.println();
		
		System.out.println("----- depth -----");
		for(int i=0; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				System.out.print(parent[j][i]+" ");	
			}
			System.out.println();
		}
		
		
	}
}

