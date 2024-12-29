package baekjoon.ttone.dataStructure;

// #1517 dataStructure (1517. 버블 소트) - 세그먼트 트리 
import java.io.*;
import java.util.*;

public class BubbleSort {

	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		long[] elements = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			elements[i] = Integer.parseInt(st.nextToken());
		}

		// <K, V> = <segment tree index, elements in queue (중복 값 처리)>
		Map<Long, Queue<Integer>> pos = new HashMap<>();
		for (int i = 0; i < n; i++) {
			pos.computeIfAbsent(elements[i], k -> new LinkedList<>()).offer(i);
		}

		long[] index = elements.clone();
		Arrays.sort(index);

		long[] tree = new long[getTreeSize()];
		long ans =0;
		System.out.println("------------------");
		for(int i=0; i<n; i++)	{
			int idx = pos.get(index[i]).poll();
			ans += sum(tree,0, n-1, 1, idx+1, n-1);
			update(tree, 0, n-1, 1, idx, 1);
		}
		System.out.println(ans);
	}
	
	static int getTreeSize() {
		int h = (int)Math.ceil(Math.log(n)/Math.log(2))+1;
		return (int)Math.pow(2, h);
	}
	
	static long sum(long[] tree, int start, int end, int node, int left, int right) {
		if(end < left || right < start ) return 0;
		if(left <= start && end <= right) {
			return tree[node];
		
		}
		
		int mid = (start+end)/2;
		return sum(tree, start, mid, node*2, left, right) + sum(tree, mid+1, end, node*2+1, left, right);
	}
	
	static void update(long[] tree, int start, int end, int node, int idx, int dif) {
		if(start == end ) {
			tree[node] = dif;
			return;
		}
		int mid = (start+end)/2;
		if(idx <= mid) update(tree, start, mid, node*2, idx, dif);
		else update(tree, mid+1, end, node*2+1, idx, dif);
		
		tree[node] = tree[node*2]+tree[node*2+1];
	}
	
}


