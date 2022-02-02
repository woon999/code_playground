package baekjoon.tttwo.segmenttree;

// #10090 segmenttree Counting Inversions 
import java.io.*;
import java.util.StringTokenizer;

public class CountingInversion {

	static int[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n+1];
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		tree = new int[n*4];
		long res = 0;
		for(int i=n; i>0; i--) {
			int x = arr[i];
			res += query(1, n, 1, 1, x-1);
			update(1, n, 1, x);
		}
        System.out.println(res);
	}
	
	static void print() {
		for(int i=1; i<tree.length; i++) {
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
	
	static int update(int s, int e, int node, int idx) {
		if(e < idx || idx < s) return tree[node];
		if(s == e) return tree[node] +=1 ;
		
		int mid = (s+e)/2;
		return tree[node] = update(s, mid, node*2, idx) + update(mid+1, e, node*2+1, idx);
	}
	
	static int query(int s, int e, int node, int l, int r) {
		if(r < s || e < l) return 0;
		if(l <= s && e <= r)return tree[node];
		int mid = (s+e)/2;
		return query(s, mid, node*2, l, r) + query(mid+1, e, node*2+1, l, r);
	}
}