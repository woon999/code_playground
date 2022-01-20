package baekjoon.tttwo.segmenttree;

// #9426 segmenttree 중앙값 측정 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GetMid {
	static final int SIZE = 65536;
	static int[] arr, tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1];
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		tree = new int[SIZE*4];
		for(int i=1; i<k; i++) {
			update(0,SIZE,1,arr[i],1);
		}
//		print();
		int prev = 1;
		int mid = (k+1)/2;
		long ans = 0;
		for(int i=k; i<=n; i++) {
			update(0,SIZE,1,arr[i],1);
			ans += find(0,SIZE,1,mid);
			update(0,SIZE,1,arr[prev++],-1);
		}
		System.out.println(ans);
	}
	
	static void print() {
		for(int i=1; i<tree.length; i++	) {
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
	
	static int update(int s, int e, int node, int idx, int dif) {
		if(idx < s || e < idx) return tree[node];
		if(s == e) {
			return tree[node] += dif;
		}
		
		int mid = (s+e)/2;
		return tree[node] = update(s, mid, node*2, idx, dif)+update(mid+1, e, node*2+1, idx, dif);
	}
	
	static int find(int s, int e, int node, int k) {
		if(s == e) {
			return s;
		}
		
		int mid = (s+e)/2;
		if(tree[node*2] >= k) {
			return find(s, mid, node*2, k);
		}else {
			return find(mid+1, e, node*2+1, k-tree[2*node]);
		}
	}

}
