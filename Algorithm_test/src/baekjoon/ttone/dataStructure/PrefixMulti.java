package baekjoon.ttone.dataStructure;

// #11505 dataStructure 구간 곱 구하기 (세그먼트 트리)
import java.io.*;
import java.util.StringTokenizer;

public class PrefixMulti {
	static int n, MOD = 1_000_000_007;
	static int[] elements;
	static long[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		elements = new int[n+1];
		for(int i=1; i<n+1; i++) {
			elements[i] = Integer.parseInt(br.readLine());
		}
		tree = new long[getTreeSize()];
		init(1, n, 1);
		for(int i=0; i<m+k; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if(op ==1) {
				int idx = Integer.parseInt(st.nextToken());
				int dif = Integer.parseInt(st.nextToken());
				elements[idx] = dif;
				update(1, n, 1, idx, dif);
			}else {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				sb.append(pMul(1, n, 1, left, right) % MOD+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static int getTreeSize() {
		int h = (int)Math.ceil(Math.log(n)/Math.log(2));
		return (int) Math.pow(2, h+1);
	}
	
	static long init(int start, int end, int node) {
		if(start == end) return tree[node] = elements[start];
		
		int mid = (start+end)/2;
		return tree[node] = (init(start,mid,node*2) * init(mid+1, end, node*2+1))%MOD;
	}
	
	static long update(int start, int end, int node, int idx, int dif) {
		if(end < idx || idx< start) return tree[node];
		if(start == end) return tree[node] = dif;
		
		int mid = (start+end)/2;
		return tree[node] = (update(start, mid, node*2, idx, dif)*update(mid+1, end, node*2+1, idx, dif))%MOD;
	}
	
	static long pMul(int start, int end, int node, int left, int right) {
		if(end < left || right < start) return 1;
		if(left <= start && end <= right) {
			return tree[node];
		}
		int mid = (start+end)/2;
		return (pMul(start, mid, node*2, left, right)* pMul(mid+1, end, node*2+1, left, right))%MOD;
	}
}
