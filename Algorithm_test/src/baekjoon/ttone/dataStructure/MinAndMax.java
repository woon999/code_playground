package baekjoon.ttone.dataStructure;

// #2357 dataStructure (2357. 최솟값과 최댓값) - segmentTree 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinAndMax {

	static int n, min, max;
	static int Init = 1_000_000_001;
	static int[] elements,minTree, maxTree;
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
		int size = getTreeSize();
		minTree =new int[size];
		maxTree =new int[size];
		init(0, minTree, 1,n,1);
		init(1, maxTree, 1,n,1);
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			min = Init; max =-1;
			query(0, minTree, 1,n,1,a,b);
			query(1,maxTree, 1,n,1,a,b);
			sb.append(min+" "+max+"\n");
		}
		System.out.println(sb.toString());
		
		
	}
	
	// 1-1. 세그먼트 트리 사이즈 구하기
	static int getTreeSize() {
		int h = (int)Math.ceil(Math.log(n)/Math.log(2)) +1;
		return (int)Math.pow(2, h);
	}
	
	// 1-2. 세그먼트 트리 생성
	// type 0: 최소, type 1: 최대
	static void init(int type, int[] tree, int start, int end, int node) {
		if(start == end) {
			tree[node] = elements[start];
		}else {
			int mid = (start+end)/2;
			init(type, tree, start, mid, node*2);
			init(type, tree, mid+1, end, node*2+1);
			
			if(type ==0) {
				if(tree[node*2] < tree[node*2+1]) {
					tree[node] = tree[node*2];
				}else {
					tree[node] = tree[node*2+1];
				}
			}else {
				if(tree[node*2] > tree[node*2+1]) {
					tree[node] = tree[node*2];
				}else {
					tree[node] = tree[node*2+1];
				}
			}
		}
	}
	
	// 2. 구간 [l,r] 최대 최소 구하기
	// type 0: 최소, type 1: 최대
	static void query(int type, int[] tree, int start, int end, int node, int l, int r) {
		if(l > end || r < start) return;
		if(l <= start && end <= r) {
//			System.out.println(start+","+ end+","+ node +","+l+","+r);
			if(type==0) {
				min = Math.min(min, tree[node]);
			}else {
				max = Math.max(max, tree[node]);
			}
			return;
		}

		
		int mid = (start+end)/2;
		query(type, tree, start, mid, node*2, l ,r);
		query(type, tree, mid+1, end, node*2+1, l ,r);
		
	}
}
