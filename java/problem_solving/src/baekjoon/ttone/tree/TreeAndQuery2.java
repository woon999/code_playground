package baekjoon.ttone.tree;

// #13511 tree 트리와 쿼리2 - lca 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeAndQuery2 {
	
	static class Node{
		int to;
		int cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	static int n,h;
	static List<Node>[] list;
	static int[] depth;
	static long[] cost;
	static int[][] parents;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		h = (int)Math.ceil(Math.log(n)/Math.log(2)) +1;
		list = new ArrayList[n+1];
		parents = new int[n+1][h];
		depth = new int[n+1];
		cost = new long[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[u].add(new Node(v,w));
			list[v].add(new Node(u,w));
			
		}
		
		init(1,0,-1);
		fillParents();
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			int lca = LCA(u,v);
			if(op == 1) {
				sb.append(cost[u] + cost[v] - 2*cost[lca] +"\n");
			}else {
				int k = Integer.parseInt(st.nextToken());
				sb.append(kNode(u, v, lca, k) +"\n");
			}
		}
		System.out.println(sb.toString());
	}
	static int kNode(int x, int y, int root, int k) {
		int xh = depth[x];
		int yh = depth[y];
		
		int mid = xh-depth[root]+1;
		int tmp= 0;
		if(mid == k) {
			return root;
		}else if(mid > k) { // left
			k -=1;
			tmp = x;
		}else { // right
			k = mid + yh - depth[root] - k;
			tmp = y;
		}
		
		for(int i=h-1; i>=0; i--) {
			if((k & (1<<i)) !=0) { 
				k ^= (1<<i); // 2^i번째 부모로 이동 
				tmp = parents[tmp][i];
			}
		}
		return tmp;
	}
	
	static int LCA(int x, int y) {
		int xh = depth[x];
		int yh = depth[y];
		
		// make (xh > yh)
		if(xh <yh) {
			int tmp  = x;
			x = y;
			y = tmp;
			xh = depth[x];
			yh = depth[y];
		}
		
		// matching depth 
		for(int i=h-1; i>=0; i--) {
			if(Math.pow(2, i) <= depth[x]-depth[y]) {
				x = parents[x][i];
			}
		}
		if(x==y) return x;
		// find LCA
		for(int i=h-1; i>=0; i--) {
			if(parents[x][i] != parents[y][i]) {
				x = parents[x][i];
				y = parents[y][i];
			}
		}
		
		return parents[x][0];
	}

	static void init(int cur, int h, int pa) {
		depth[cur] = h;
		for(Node nxt : list[cur]) {
			if(nxt.to != pa) {
				cost[nxt.to] += cost[cur] + nxt.cost;
				init(nxt.to, h+1, cur);
				parents[nxt.to][0] = cur; // nxt의 부모 cur 
			}
		}
	}
	
	static void fillParents() {
		for(int i=1; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				parents[j][i] = parents[parents[j][i-1]][i-1];
			}
		}
	}
	
	
	static void print() {
		for(int i=1; i<n+1; i++) {
			System.out.print(depth[i]+" ");
		}
		System.out.println();
		
		System.out.println("-------");
		for(int i=1; i<n+1; i++) {
			System.out.print(cost[i]+" ");
		}
		System.out.println();
		
		System.out.println("-------");
		
		for(int i=1; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				System.out.print(parents[j][i-1]+" ");
			}
			System.out.println();
		}
	}
}
