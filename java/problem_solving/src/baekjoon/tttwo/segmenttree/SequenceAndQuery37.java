package baekjoon.tttwo.segmenttree;

// #18436 segmenttree 수열과쿼리 37 
import java.io.*;
import java.util.StringTokenizer;

public class SequenceAndQuery37 {

	static int[] arr, tree;
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		tree = new int[4*n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		init(1,n,1);
		
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(op == 1) {
				// 홀 > 짝 
				if(arr[a]%2==1 && b%2 ==0) {
					update(1,n,1,a,1);	
				}
				// 짝 > 홀 
				else if(arr[a]%2==0 && b%2==1) { 
					update(1,n,1,a,0);	
				}
				arr[a] =b ;
				
			}else if(op==2 || op == 3){
				long x = query(1,n,1,a,b);
				if(op == 2) {
					sb.append(x+"\n");
				} else {
					sb.append((b-a+1-x)+"\n");
				}
			} 
		}
		System.out.println(sb.toString());
	}
	
	static int init(int s, int e, int node) {
		if(s==e) {
			if(arr[s]%2==0) return tree[node] = 1;
			else return 0;
		}
		int mid = (s+e)/2;
		return tree[node] = init(s, mid, node*2) +init(mid+1, e, node*2+1);
	}
	
	static int update(int s, int e, int node, int idx, int val) {
		if(idx < s|| e < idx ) return tree[node];
		if(s == e)  {
			return tree[node] = val;
		}
		
		int mid = (s+e)/2;
		return tree[node] = update(s, mid, node*2, idx, val) + update(mid+1, e, node*2+1, idx, val);
	}
	
	static int query(int s, int e, int node, int l, int r) {
		if(r < s || e < l) return 0;
		if(l <= s && e <= r) {
			return tree[node];
		}
		int mid = (s+e)/2;
		return query(s,mid,node*2,l,r)+query(mid+1,e,node*2+1,l,r);
	}
	
	
	
	static void print() {
		for(int i=1; i<4*n; i++) {
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
}

