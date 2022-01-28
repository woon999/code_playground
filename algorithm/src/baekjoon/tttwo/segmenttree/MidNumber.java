package baekjoon.tttwo.segmenttree;

// #1572 segmenttree 중앙값  
import java.io.*;
import java.util.*;

public class MidNumber {
	
	static int[] arr, tree;
	static final int MAX = 65536;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		tree = new int[MAX*4];
		arr = new int[n+1];
		long sum = 0;
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			update(0, MAX, 1, arr[i], 1);
			if(i>=k) {
				int x = query(0,MAX,1,(k+1)/2);
//				System.out.println(i +" - " + x);
				sum += x;
				update(0, MAX, 1, arr[i-k+1], -1);
			}
		}
		
		System.out.println(sum);
	}
	
	static void print() {
		for(int i=1; i<tree.length; i++) {
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
	
	static void update(int s, int e, int node, int idx, int val) {
		if(idx < s|| e < idx) return;
		
		tree[node] += val;
		
		if(s == e) return;
		
		int mid = (s+e)/2;
		update(s, mid, node*2, idx, val);
		update(mid+1, e, node*2+1, idx, val);
	}
	
	static int query(int s, int e, int node ,int cnt) {
		if(s == e) return s;
		int mid = (s+e)/2;
		if(tree[node*2] >= cnt) return query(s, mid, node*2, cnt);
		else return query(mid+1, e, node*2+1, cnt-tree[node*2]);
	}
	
}
