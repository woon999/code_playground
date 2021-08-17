package baekjoon.ttone.segmentTree;

// #2357 dataStructure 최솟값 - segmentTree 
import java.io.*;
import java.util.StringTokenizer;

public class Min{

	static int n, result, Init = Integer.MAX_VALUE;
	static int[] elements, tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		elements = new int[n+1];
		for(int i=1; i<n+1; i++) {
			elements[i] = Integer.parseInt(br.readLine());
		}
		
		int treeSize = getTreeSize();
		tree = new int[treeSize];
		init(1,n,1);
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			result = Init;
			getMin(1, n, 1, a, b);
			sb.append(result+"\n");
		}
		System.out.println(sb.toString());
	}
	static int getTreeSize() {
		int h = (int)Math.ceil(Math.log(n)/Math.log(2)) +1;
		return (int)Math.pow(2,h);
	}
	
	static void init(int start, int end, int node) {
		if(start == end) {
			tree[node] = elements[start];
		}else{
			int mid = (start+end)/2;
			
			init(start, mid, node*2);
			init(mid+1, end, node*2+1);
			
			if(tree[node*2] < tree[node*2+1]) {
				tree[node] = tree[node*2];
			}else {
				tree[node] = tree[node*2+1];
			}
		}
	}
	static void getMin(int start, int end, int node, int l, int r) {
		if(end < l || r < start) return;
		if(l<= start && end <= r) {
			result = Math.min(result, tree[node]);
			return;
		}
		
		int mid = (start+end)/2;
		
		getMin(start, mid, node*2, l,r);
		getMin(mid+1, end, node*2+1, l, r);
		
		
	}
	
	
}
