package baekjoon.tttwo.segmenttree;

// #2243 segmentTree 사탕상자 - 이분탐색 
import java.io.*;
import java.util.StringTokenizer;

public class CandyBox {

	static final int SIZE = 1_000_001;
	static int[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		tree = new int[SIZE*4];
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			if(op == 1) {
				int candy = query(1, SIZE, 1, a);
				sb.append(candy+"\n");
			}else {
				int b = Integer.parseInt(st.nextToken());	
				update(1, SIZE, 1, a, b);
			}
		}
		System.out.println(sb.toString());
	}
	static int query(int s, int e, int idx, int target) {
		if(s == e) {
			update(1, SIZE, 1, s, -1);
			return s;
		}
		
		int mid = (s+e)/2;
		if(target <= tree[idx*2]) {
			return query(s, mid, idx*2, target);
		}else {
			return query(mid+1, e, idx*2+1, target-tree[idx*2]);
		}
	}
	
	static void update(int s, int e, int idx, int target, int dif) {
		if(target < s || e < target) return;
		
		tree[idx] += dif;
		if(s == e) return;
		
		int mid = (s+e)/2;
		update(s, mid, idx*2, target, dif);
		update(mid+1, e, idx*2+1, target, dif);
	}
}
