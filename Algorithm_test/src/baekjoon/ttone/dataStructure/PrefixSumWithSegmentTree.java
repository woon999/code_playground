package baekjoon.ttone.dataStructure;

// #2042 prefixSum 구간 합 구하기 - segment tree 
import java.io.*;
import java.util.StringTokenizer;

public class PrefixSumWithSegmentTree {

	static int n;
	static long[] tree,arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new long[n];
		tree = new long[getTreeSize()];
		for(int i=0; i<n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		init(0, n-1, 1);
//		for(int i=0; i<tree.length; i++) {
//			System.out.print(tree[i]+" ");
//		}
//		System.out.println();
		while(true) {
			if(m==0 && k==0) break;
			
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if(op ==1) {
				int idx = Integer.parseInt(st.nextToken())-1;
				long num = Long.parseLong(st.nextToken());
				
				long dif = num - arr[idx];
				update(0, n-1, 1, idx, dif);
				arr[idx] = num;
				
				m--;
			}else {
				int left = Integer.parseInt(st.nextToken())-1;
				int right = Integer.parseInt(st.nextToken())-1;
				
				long sum =pSum(0, n-1, 1, left, right);
				sb.append(sum+"\n");
				
				k--;
			}
		}
		
		System.out.println(sb.toString());
	}
	static int getTreeSize() {
		int h = (int)Math.ceil(Math.log(n)/Math.log(2)) +1;
		
		return (int)Math.pow(2, h)-1;
	}
	
	static long init(int start, int end, int node) {
		if(start == end) return tree[node] = arr[start];
		int mid = (start+end)/2;
		
		return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
	}
	
	static void update(int start, int end, int node, int idx, long dif) {
		if(start <= idx && idx <= end) {
			tree[node] += dif;
		}else return;
		
		if(start == end) return;
		
		int mid = (start+end)/2;
		update(start, mid, node*2, idx, dif);
		update(mid+1, end, node*2+1, idx, dif);
		
	}
	
	static long pSum(int start, int end, int node, int l, int r) {
		
		if(r < start || l> end ) return 0;
		if(l <= start && end <= r )return tree[node];
		
		int mid = (start+end)/2;
		
		return pSum(start, mid, node*2, l, r) + pSum(mid+1, end, node*2+1, l, r);  
		
	}
}
