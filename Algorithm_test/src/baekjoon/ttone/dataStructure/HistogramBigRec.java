package baekjoon.ttone.dataStructure;

// #6549 dataStructure,segmentTree 히스토그램 가장 큰 직사각형
import java.io.*;
import java.util.StringTokenizer;

public class HistogramBigRec {

	static int n,size;
	static int[] arr, tree;	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while(true) {
			String s = br.readLine();
			if(s.equals("0")) break;
			
			st = new StringTokenizer(s);
			n = Integer.parseInt(st.nextToken());
			
			arr = new int[n];
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			size = getTreeSize();
			tree= new int[size];
			init(arr,1,0,n-1);
//			for(int i=1; i<size; i++) {
//				System.out.print(tree[i]+" ");
//			}
//			System.out.println();
			
//			System.out.println("최대넓이구하기========");
			System.out.println(getMax(0,n-1));
//			System.out.println("==================");
		}
	}
	
	static int getTreeSize() {
		int h = (int)Math.ceil(Math.log(n)/Math.log(2)) +1;
		return (int)Math.pow(2, h);
	}
	
	static void init(int[] arr, int node, int start, int end) {
		if(start == end) {
			tree[node] = start;
		}
		else {
			int mid = (start + end) /2;
			init(arr, node*2, start, mid);
			init(arr, node*2+1, mid+1, end);
			
			if(arr[tree[node*2]] < arr[tree[node*2+1]]) {
					tree[node] = tree[node*2];
			}
			else {
					tree[node] = tree[node*2+1];
			}
		}
	}
	static long getMax(int left, int right) {
		int n = arr.length;
		int m = query(1,0,n-1,left,right);
		
		long area = (long)(right - left +1)*(long)arr[m];
//		System.out.println(left+"~"+right+ " : " + m + " -> size :" + area);
		
		if(left<= m-1) {
			long tmp = getMax(left, m-1);
			if(area < tmp) {
				area = tmp;
			}
		}
		
		if(m+1 <= right) {
			long tmp = getMax(m+1, right);
			if(area < tmp) {
				area = tmp;
			}
		}
		return area;
	}
	
	static int query(int node, int start, int end, int left, int right) {
		
		if(left > end || right < start) {
			
			return -1;
		}
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		int m1  = query(node*2, start, mid, left, right);
		int m2 = query(node*2+1, mid+1, end, left, right);
		
		if(m1 == -1) {
			return m2;
		}
		else if(m2 == -1) {
			return m1;
		}
		else {
			if(arr[m1]<=arr[m2])
				return m1;
			else 
				return m2;
		}
	}
	
}
