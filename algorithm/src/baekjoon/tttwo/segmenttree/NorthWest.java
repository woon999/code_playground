package baekjoon.tttwo.segmenttree;

// #5419 segmenttree 북서풍 
import java.io.*;
import java.util.*;

public class NorthWest {

	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while(t-->0){
			int n = Integer.parseInt(br.readLine());
			
			List<Point> p = new ArrayList<>();
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				p.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			Collections.sort(p, (a,b) -> b.y-a.y);
			
			int[] tmp = new int[n];
			int cnt =1;
			for(int i=0; i<n; i++) {
				if(i>0 && p.get(i).y != p.get(i-1).y) cnt++;
				tmp[i] = cnt;
			}
			
			for(int i=0; i<n; i++) {
				p.get(i).y = tmp[i];
			}
			
			Collections.sort(p, (a,b) -> {
				if(a.x == b.x) return a.y - b.y;
				else return a.x-b.x;
			});
			
//			for(Point pp : p) {
//				System.out.println(pp.x +"," +pp.y);
//			}
			
			tree = new int[4*n];
			long res = 0;
			for(int i=0; i<n; i++) {
				res += query(1, n, 1, 1, p.get(i).y);
//				System.out.println(res);
				update(1, n, 1, p.get(i).y);
//				print();
			}
			System.out.println(res);
		}
	}
	
	static void print() {
		for(int i=1; i<tree.length; i++) {
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
	
	static void update(int s, int e, int node, int idx) {
		if(idx < s || e < idx) return;
		tree[node] += 1;
		if(s == e) return;
		int mid = (s+e)/2;
		update(s, mid, node*2, idx);
		update(mid+1, e, node*2+1, idx);
	}
	
	static int query(int s, int e, int node, int l ,int r) {
		if(e < l || r < s) return 0;
		if(l <= s && e <= r) return tree[node];
		int mid = (s+e)/2;
		return query(s, mid, node*2, l, r) + query(mid+1, e, node*2+1, l, r);
	}
}
