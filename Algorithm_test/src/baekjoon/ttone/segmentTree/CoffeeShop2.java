package baekjoon.ttone.segmentTree;

// #1275 segmentTree 커피숍2 
import java.io.*;
import java.util.StringTokenizer;

public class CoffeeShop2 {

	static long[] elements, tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		elements = new long[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			elements[i] = Integer.parseInt(st.nextToken());
		}
		int size = getTreeSize(n);
		tree = new long[size];
		init(0,n-1,1);

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			
			if(x>y) {
				int tmp =x;
				x = y;
				y = tmp; 
			}
			int idx = Integer.parseInt(st.nextToken())-1;
			int value = Integer.parseInt(st.nextToken());
			
			long pSum = prefixSum(0, n-1, 1, x, y);
			sb.append(pSum+"\n");
			
			long dif = value - elements[idx];
			update(0, n-1, 1, idx, dif);
			elements[idx] = value;
		}
		System.out.println(sb.toString());
		
	}
	
	static int getTreeSize(int n) {
		int h = (int)Math.ceil(Math.log(n)/Math.log(2));
		return (int) Math.pow(2, h+1);
	}
	
	static long init(int s, int e, int node) {
		if(s == e) {
			return tree[node] = elements[s];
		}
		
		int mid = (s+e)/2;
		return tree[node] = init(s, mid, node*2)+init(mid+1, e, node*2+1);
	}
	
	static void update(int s, int e, int node, int idx, long dif) {
		if(s <= idx && idx <= e) {
			tree[node] += dif;
		}else return;
		
		if(s == e) return;
		
		int mid = (s+e)/2;
		update(s, mid, node*2, idx, dif);
		update(mid+1, e, node*2+1, idx, dif);
	}
	
	static long prefixSum(int s, int e, int node, int l, int r) {
		if(e < l || r < s) return 0;
		if(l <= s && e <= r) {
			return tree[node];
		}
		
		int mid = (s+e)/2;
		return prefixSum(s, mid, node*2, l, r)+ prefixSum(mid+1, e, node*2+1, l, r);
		
	}
	
}
