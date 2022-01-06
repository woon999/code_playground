package baekjoon.ttone.segmentTree;

// #14438 segmentTree 수열과 쿼리 17 
import java.io.*;
import java.util.StringTokenizer;

public class SequenceAndQuery17 {

	static int[] arr, tree;
	static int n;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		tree = new int[getTreeSize()];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(0, n-1, 1);
		
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			if(op == 1) {
				arr[a] = b+1;
				update(0, n-1, 1, a);
				
			}else {
				sb.append(getMin(0, n-1, 1, a, b)+"\n");
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void print() {
		for(int i=0; i<tree.length; i++) {
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
	
	static int getTreeSize() {
		int h = (int)Math.ceil(Math.log(n)/Math.log(2))+1;
		return (int)Math.pow(2, h)-1;
	}
	
	static int init(int s, int e, int node) {
		if(s == e) return tree[node] = arr[s];
		
		int mid = (s+e)/2;
		
		return tree[node] = Math.min(init(s, mid, node*2), init(mid+1, e, node*2+1)); 
	}
	
	static int update(int s, int e, int node, int idx) {
		if(s > idx || e < idx ) return tree[node];
		if(s == e)  return tree[node] = arr[idx];
		
		int mid = (s+e)/2;
		return tree[node] = Math.min(update(s, mid, node*2, idx), update(mid+1, e, node*2+1, idx));
	}
	
	static int getMin(int s, int e, int node, int l, int r) {
		if(r < s || l > e ) return Integer.MAX_VALUE;
		if(l <= s && e <= r) return tree[node];
		
		int mid = (s+e)/2;
		return Math.min(getMin(s, mid, node*2, l, r), getMin(mid+1, e, node*2+1, l, r));
	}
	
	
}

