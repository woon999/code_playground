package baekjoon.ttone.tree;

// #8012 tree 한동이는 영업사원! - LCA,DP
import java.io.*;
import java.util.*;

public class BusineessMan {

	static int n, h;
	static List<Integer>[] list, tree;
	static int[] depth, dis;
	static int[][] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
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
		
		h = getTreeHeight();
		dis = new int[n+1];
		depth = new int[n+1];
		parent = new int[n+1][h];
		
		init(1,1,0);
		fillParents();
		
//		print();
		
		int m = Integer.parseInt(br.readLine());
		int[] city = new int[m];
		for(int i=0; i<m; i++) {
			city[i] = Integer.parseInt(br.readLine());
			
		}
		
		int totalCost =0;
		for(int i=1; i<m; i++) {
			int a = city[i-1];
			int b = city[i];
			int lca = LCA(a,b);
			totalCost += dis[a]+dis[b]-2*dis[lca];
		}
		System.out.println(totalCost);
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
		
		if(a==b) return a;
		
		for(int i=h-1; i>=0; i--) {
			if(parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		return parent[a][0];
	}
	
	static int getTreeHeight() {
		return(int)Math.ceil(Math.log(n)/Math.log(2)) +1;
	}
	
	static void init(int cur, int h, int pa) {
		depth[cur] = h;
		for(int nxt : list[cur]) {
			if(nxt != pa) {
				dis[nxt] = dis[cur] +1;
				init(nxt, h+1, cur);
				parent[nxt][0] = cur; // nxt의 부모 cur 
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
		System.out.println("---- depth ------");
		for(int i=1; i<n+1; i++) {
			System.out.print(depth[i] +" ");
		}
		System.out.println();
		
		System.out.println("---- dis ------");
		for(int i=1; i<n+1; i++) {
			System.out.print(dis[i] +" ");
		}
		System.out.println();
		
		System.out.println("----- parent -----");
		for(int i=0; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				System.out.print(parent[j][i]+" ");
			}
			System.out.println();
		}
	}
}
