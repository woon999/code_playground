package baekjoon.tttwo.segmenttree;

// #7469 segmenttree k번째 수 
import java.io.*;
import java.util.*;

public class KthNumber {
	
	static List<Integer>[] tree;
	static int[] arr;
	static final int MAX = 1_000_000_001;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		tree = new ArrayList[n*4];
		for(int i=0; i<4*n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			update(1,n, 1, i);
		}
		
		
		for(int i=0; i<4*n; i++) {
			Collections.sort(tree[i]);
		}
		
//		print();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			int l = -MAX, r = MAX;
			while(l<=r) {
				int mid = (l+r)/2;
				// mid보다 작은 값의 갯수 < k 
				if(query(1,n,1,a,b,mid) < k) {
					l = mid +1;
				}else {
					r = mid-1;
				}
			}
			sb.append(r+"\n");
		}
		System.out.println(sb.toString());
		
		
	
	}
	static void print() {
		for(int i=1; i<tree.length; i++) {
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
	
	static void update(int s, int e, int node, int idx) {
		if(idx < s|| e < idx) return;
		
		tree[node].add(arr[idx]);
		if(s == e) return;
		
		int mid = (s+e)/2;
		update(s, mid, node*2, idx);
		update(mid+1, e, node*2+1, idx);
	}
	
	static int query(int s, int e, int node ,int l, int r, int val) {
		if(r < s || l > e ) return 0;
		if(l <= s && e <= r) {
			return lowerbound(tree[node], val);
		}
		
		int mid = (s+e)/2;
		return query(s, mid, node*2, l, r, val) +query(mid+1, e, node*2+1, l, r, val);
	}
	
	static int lowerbound(List<Integer> data, int val) {
		int s = 0;
		int e = data.size();
		
		while(s < e){
			int mid = (s+e)/2;
			if(data.get(mid) >= val) {
				e = mid;
			}else {
				s = mid+1;
			}
		}
		return e;
	}

}
