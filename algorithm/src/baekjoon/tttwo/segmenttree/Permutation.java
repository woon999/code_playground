package baekjoon.tttwo.segmenttree;

// #1849 segmenttree 순열 
import java.io.*;

public class Permutation {

	static int n;
	static int[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		tree = new int[4*n];
		init(1,n,1);
		
		int[] res = new int[n+1];
		for(int i=1; i<=n; i++) {
			int x = query(1,n,1,Integer.parseInt(br.readLine()));
			System.out.println(i + " -- " + x );
			res[x] = i;
			update(1,n,1,x);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			sb.append(res[i]+"\n");
		}
		System.out.println(sb.toString());
		
		
	}
	
	static void print() {
		for(int i=1; i<tree.length; i++) {
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
	static void init(int s, int e, int node) {
		if(s == e) {
			tree[node] = 1;
			return;
		}
		
		int mid = (s+e)/2;
		init(s, mid, node*2);
		init(mid+1, e, node*2+1);
		tree[node] = tree[node*2] + tree[node*2+1]; 
	}
	
	static void update(int s, int e, int node, int idx) {
		if(idx < s || e < idx ) return;
		if(s == e) {
			tree[node] = 0;
			return;
		}
		int mid = (s+e)/2;
		update(s, mid, node*2, idx);
		update(mid+1, e, node*2+1, idx);
		tree[node] -= 1;
	}
	
	static int query(int s, int e, int node, int val) {
		if(s == e) return s;
	
		int mid = (s+e)/2;
		if(tree[node*2] > val) {
			return query(s, mid, node*2, val);
		} else {
			return query(mid+1, e, node*2+1, val - tree[node*2]);	
		}
	}
	
}
