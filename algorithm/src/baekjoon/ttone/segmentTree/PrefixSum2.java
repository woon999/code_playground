package baekjoon.ttone.segmentTree;

// #10999 dataStructure 구간 합 구하기 2 - 세그먼트 트리 with lazy propagation
// 단순 세그먼트 트리로 접근하게 되면 update할 때마다 최악의 경우 트리 전체를 업데이트해야 하기 때문에 
// 총 O(NM + KlogN) 시간이 걸림
//  lazy propagation을 사용하면 O((M+K)logN)으로 해결 가능 

import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum2 {

	static int n;
	static long[] elements, tree, lazy;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		elements = new long[n];
		for(int i=0; i<n; i++) {
			elements[i] = Long.parseLong(br.readLine());
		}
		
		tree = new long[n*4];
		lazy = new long[n*4];
		
		
		init(0,n-1,1);
		for(int i=0; i<m+k; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken())-1;
			if(op==1) {
				long dif = Long.parseLong(st.nextToken());
				
				update(0,n-1, 1, l, r, dif);
			}else {
				bw.write(pSum(0, n-1, 1, l, r)+"\n");
			}
		}
		bw.flush();
		bw.close();
	}
	
	static long init(int start, int end, int node) {
		if(start == end) return tree[node] = elements[start];
		
		int mid = (start+end)/2;
		return tree[node] =  init(start, mid, node*2) +init(mid+1, end, node*2+1);
	}
	
	static void propagate(int start, int end, int node) {
		// 값이 들어있는 경우 업데이트 처리 시작 
		if(lazy[node] !=0){ 
			// 리프노드가 아닌 경우 자식에게 lazy 물려줌(자식은 나중에 갱신)
			if(start != end) { 
				lazy[node*2] += lazy[node];
				lazy[node*2 +1] += lazy[node];
			}
			 
			tree[node] += lazy[node] * (end-start+1); // tree[node] = 업데이트 값 * 해당 자식노드의 총 갯수
			lazy[node] = 0; // 업데이트 완료 후 초기화 
		}
		
	}
	
	static void update(int start, int end, int node, int left, int right, long dif) {
		propagate(start, end, node); // 해당 노드에 갱신할 값이 있다면 업데이트 
		if(end < left || right < start) return;
		if(left <= start && end <= right) {
			lazy[node] = dif; // 범위에 속할 경우 업데이트 lazy값 갱신  
			propagate(start, end, node); // 해당 노드에 갱신할 값이 있다면 업데이트 
			return;
		}
		
		int mid = (start+end)/2;
		update(start, mid, node*2, left, right, dif);
		update(mid+1, end, node*2+1, left, right, dif);
		
		// update가 다 일어난 후 부모 노드에 반영
		tree[node] = tree[node*2]+tree[node*2+1];
		
	}
	
	
	static long pSum(int start, int end, int node, int left, int right) {
		propagate(start, end, node); // 남은 lazy update 처리
		if(end < left || right < start) return 0;
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		return pSum(start, mid, node*2, left, right) + pSum(mid+1, end, node*2+1, left, right);
	}
}




