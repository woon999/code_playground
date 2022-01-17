package baekjoon.tttwo.segmenttree;

// #13537 segmentTree 수열과 쿼리 1 
import java.io.*;
import java.util.*;

public class SequenceAndQuery1 {
	static List<Integer>[] tree;
	static int[] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		tree = new ArrayList[n*4];
		for(int i=0; i<n*4; i++) {
			tree[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			update(1,n, 1, i);
		}
		
		for(int i=0; i<n*4; i++) {
			Collections.sort(tree[i]);
		}
		
//		print();
		
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			sb.append(getMoreThanNumOfK(1,n,1,a,b,k) +"\n");
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
		if(s > idx || e < idx ) return;	
		tree[node].add(arr[idx]);
		
		if(s == e) return;
		
		int mid = (s+e)/2;
		update(s, mid, node*2, idx);
		update(mid+1, e, node*2+1, idx);
	}
	
	static int getMoreThanNumOfK(int s, int e, int node, int l, int r, int k) {
		if(r < s || l > e ) return 0;
		if(l <= s && e <= r) {
			int x = upperbound(tree[node], k); // [s,e]에서 k보다 큰 개수 
			return tree[node].size()-x;
		}
		
		int mid = (s+e)/2;
		return getMoreThanNumOfK(s, mid, node*2, l, r, k) + getMoreThanNumOfK(mid+1, e, node*2+1, l, r, k);
	}
	
	static int upperbound(List<Integer> data, int val) {
		int s = 0;
		int e = data.size();
		
		while(s < e){
			int mid = (s+e)/2;
			if(data.get(mid) <= val) {
				s = mid+1;
			}else {
				e = mid;
			}
		}
		return e;
	}
}

