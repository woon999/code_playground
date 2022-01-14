package baekjoon.tttwo.segmenttree;

// #12837 segmenttree 가계부 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AccountBook {

	static long[] arr, tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		arr = new long[n+1];
		tree = new long[n*4];
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(op == 1) {
				arr[a] += b;
				update(1, n, 1, a);
//				print(n);
			} else {
				sb.append(pSum(1,n,1,a,b)+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static void print(int n) {
		for(int i=1; i<4*n; i++){
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
	
	static long update(int s, int e, int node, int idx) {
		if(idx < s || e < idx) return tree[node];
		if(s == e) {
			return tree[node] = arr[idx];
		}
		int mid = (s+e)/2;
		return tree[node] = update(s, mid, node*2, idx) + update(mid+1, e, node*2+1, idx);
	}
	
	static long pSum(int s, int e, int node, int l, int r) {
		if(r < s || e < l) return 0;
		if(l <= s && e <= r) {
			return tree[node];
		}
		int mid = (s+e)/2;
		return pSum(s, mid, node*2, l, r) + pSum(mid+1, e, node*2+1, l, r);
	}
}
