package baekjoon.ttone.tree;

// #11438 tree LCA2 - dp 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LCA2 {
	static int n, h;
	static List<Integer>[] list;
	static int[][] parent;
	static int[] depth;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		StringTokenizer st = null;
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		h = getTreeHeight();
		System.out.println(h);
		
		depth = new int[n+1];
		parent = new int[n+1][h];
 
		init(1,1,0);
		fillParents();

		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(LCA(a,b)+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int getTreeHeight() {
		return(int)Math.ceil(Math.log(n)/Math.log(2)) +1;
	}
	static void init(int cur, int h, int pa) {
		depth[cur] = h;
		for(int nxt : list[cur]) {
			if(nxt != pa) {
				init(nxt, h+1, cur);
				parent[nxt][0] = cur; // nxt의 부모 cur 
			}
		}
	}
	
	static void fillParents() {
		for(int i=1; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
				System.out.print(parent[j][i-1]+" ");
			}
			System.out.println();
		}
	}
	
	static int LCA(int a, int b) {
		int ah = depth[a];
		int bh = depth[b];
		 // ah > bh로 세팅
		if(ah < bh) {
			int tmp = a;
			a = b;
			b = tmp;
		} 
		
		System.out.println(a+","+b + " : " + depth[a]+","+depth[b]);
		
		for (int i=h-1; i>=0; i--) {
			if(Math.pow(2, i) <= depth[a] - depth[b]){
				a = parent[a][i];
			}
		}
		System.out.println(a+","+b + " : " + depth[a]+","+depth[b]);
		if(a==b) return a;
		
		for(int i=h-1; i>=0; i--) {
			if(parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		System.out.println("#" + a+","+b + " : " + depth[a]+","+depth[b]);
		System.out.println();
		return parent[a][0];
	}
}
