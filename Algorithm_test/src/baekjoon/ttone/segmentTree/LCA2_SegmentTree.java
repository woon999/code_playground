package baekjoon.ttone.segmentTree;

// #11438 tree LCA2 - segmentTree
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//6
//1 2
//1 3
//2 4
//2 5
//4 6
//3
//6 5
//4 3
//4 5
public class LCA2_SegmentTree {
	static int n, h, serialNum=1;
	static List<Integer>[] list;
	static List<Integer> trip;
	static int[] tree;
	static int[] depth, no2serial, serial2no, locInTrip;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		StringTokenizer st = null;
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		h = (int)Math.ceil(Math.log(n)/Math.log(2)) +1;
		
		trip = new ArrayList<>(); 
		depth = new int[n+1]; no2serial = new int[n+1];
		serial2no = new int[n+1]; locInTrip = new int[n+1];
		traversal(1,1,0);

		int len = trip.size();
		int size = getTreeSize(len);
		tree = new int[size];
//		System.out.println(len+" ," + size);
		init(0, len-1, 1);
		
		for(int i=0; i<tree.length; i++) {
			System.out.print(tree[i]+" ");
		}
		System.out.println();
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			a = locInTrip[a];
			b = locInTrip[b];
			if(a>b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			int lca = query(0, len-1, a, b ,1);
//			System.out.println(a+"~"+b+" answer :" + lca +" -> " +serial2no[lca]);
			sb.append(serial2no[lca]+"\n");
			
		}
		System.out.println(sb.toString());
		
//		for(int num : trip) {
//			System.out.print(num +" ");
//		}
//		System.out.println();
//		for(int num : no2serial) {
//			System.out.print(num+" ");
//		}
//		System.out.println();
//		
//		for(int num : serial2no) {
//			System.out.print(num+" ");
//		}
//		System.out.println();
//		
//		for(int num : locInTrip) {
//			System.out.print(num+" ");
//		}
//		System.out.println();
	}
	
	static int getTreeSize(int size) {
		int h = (int)Math.ceil(Math.log(size)/Math.log(2)) +1;
		return (int)Math.pow(2, h)-1;
	}

	static void traversal(int cur, int h, int pa) {
		depth[cur] = h;
		no2serial[cur] = serialNum;
		serial2no[serialNum] = cur;
		serialNum++;
		
		locInTrip[cur] = trip.size();
//		System.out.println(cur +" : "+ no2serial[cur]);
		trip.add(no2serial[cur]);
		
		for(int nxt : list[cur]) {
			if(nxt != pa) {
				traversal(nxt, h+1, cur);
//				System.out.println("#" +cur +" : "+ no2serial[cur]);
				trip.add(no2serial[cur]);
			}
		}
	}
	
	static int init(int start, int end, int node) {
		if(start == end) {
//			System.out.println(node+":"+trip.get(start));
			return tree[node] = trip.get(start);
		}
		int mid = (start+end)/2;
		return tree[node] = Math.min(init(start, mid, node*2), init(mid+1, end, node*2+1));
	}
	
	static int query(int start, int end, int left, int right, int node) {
		if(right < start || end < left) return Integer.MAX_VALUE;
		if(left <= start && end <= right) {
//			System.out.println(start+" ~ " + end + " : " + tree[node]);
			return tree[node];
		}
		
		int mid = (start+end) /2;
		
		return Math.min(query(start, mid, left, right, node*2),
				query(mid+1, end, left, right, node*2+1));
	}
}





