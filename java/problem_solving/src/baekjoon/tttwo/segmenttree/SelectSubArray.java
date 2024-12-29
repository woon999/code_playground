package baekjoon.tttwo.segmenttree;

// #2104 segmenttree 부분배열 고르기 
import java.io.*;
import java.util.StringTokenizer;

public class SelectSubArray {
	
	static int n;
	static int[] arr, minTree;
	static long[] sumTree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1];
		arr[0] = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		sumTree = new long[n*4];
		minTree = new int[n*4];
		sumInit(1, n, 1);
		minInit(1, n, 1);
//		print();
		System.out.println(query(1,n));
	}
	
	static long query(int s, int e) {
		int min = pMin(1, n, 1, s, e);
		long area = pSum(1, n, 1, s, e)*arr[min];
		
		if(s == e) return area;
		
		if(s < min ) {
			long tmp = query(s, min-1);
			if(area < tmp) area = tmp;
		} 
		if(min < e) {
			long tmp = query(min+1, e);
			if(area < tmp) area = tmp;
		}
		return area;
	}
	
	static long sumInit(int s, int e, int node) {
		if(s == e) return sumTree[node] = arr[s];
		int mid = (s+e)/2;
		return sumTree[node] = sumInit(s, mid, node*2) + sumInit(mid+1, e, node*2+1); 
	}
	
	static int minInit(int s, int e, int node) {
		if(s == e) return minTree[node] = s;
		int mid = (s+e)/2;
		int left = minInit(s, mid, node*2);
		int right =  minInit(mid+1, e, node*2+1);
		return minTree[node] =  getIndex(left,right);
	}
	
	static long pSum(int s, int e, int node, int l, int r) {
		if(e < l || r < s) return 0;
		if(l <= s && e <= r) {
			return sumTree[node];
		}
		
		int mid = (s+e)/2;
		return pSum(s, mid, node*2, l, r) + pSum(mid+1, e, node*2+1, l, r);
	}
	
	static int pMin(int s, int e, int node, int l, int r) {
		if(e < l || r < s) return 0;
		if(l <= s && e <= r) {
			return minTree[node];
		}
		
		int mid = (s+e)/2;
		int left = pMin(s, mid, node*2, l, r);
		int right =  pMin(mid+1, e, node*2+1, l, r);
		return getIndex(left,right);
	}
	
	static int getIndex(int left, int right) {
		if(arr[left] < arr[right]) return left;
		else return right;
	}
	
	static void print() {
		System.out.println("----- sumTree ------");
		for(int i=1; i<4*n; i++) {
			System.out.print(sumTree[i]+" ");
		}
		System.out.println();
		
		System.out.println("----- minTree ------");
		for(int i=1; i<4*n; i++) {
			System.out.print(minTree[i]+" ");
		}
		System.out.println();
	}
}
