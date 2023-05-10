package baekjoon.ttone.tree;

// #11437 tree LCA 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LCA {
	static List<Integer>[] list;
	static int[] parent, depth;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		parent = new int[n+1];
		depth = new int[n+1];
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
		
		init(1,1,0);

//		for(int i=1; i<n+1; i++) {
//			System.out.print(depth[i]+" ");
//		}
//		System.out.println();
//		for(int i=1; i<n+1; i++) {
//			System.out.print(parent[i]+" ");
//		}
//		System.out.println();
		
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
	
	static void init(int cur, int h, int pa) {
		depth[cur] = h;
		parent[cur] = pa;
		for(int nxt : list[cur]) {
			if(nxt != pa) {
				init(nxt, h+1, cur);
			}
		}
	}
	
	static int LCA(int a, int b) {
		int ah = depth[a];
		int bh = depth[b];
		
		while(ah > bh) {
			a = parent[a];
			ah--;
		}
		
		while(bh > ah) {
			b = parent[b];
			bh--;
		}
		
		while(a!=b) {
			a = parent[a];
			b = parent[b];
		}
		return a;
		
	}
}
