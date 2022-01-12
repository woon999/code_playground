package baekjoon.tttwo.segmenttree;

// #5675 segmenttree 음주 코딩 
import java.io.*;
import java.util.StringTokenizer;

public class AlcoholCoding {

	static int[] arr, tree;
	public static void main(String[] args){
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String line;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			while((line = br.readLine()) != null) {
				st = new StringTokenizer(line);
				
				int n = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				
				arr = new int[n];
				tree = new int[n*4];
				st = new StringTokenizer(br.readLine());
				for(int i=0; i<n; i++) {
					int num = Integer.parseInt(st.nextToken());
					setStatus(i, num);
				}
			
				init(0, n-1, 1);
				
				for(int i=0; i<k; i++) {
					st = new StringTokenizer(br.readLine());
					String op = st.nextToken();
					int a = Integer.parseInt(st.nextToken())-1;
					int b = Integer.parseInt(st.nextToken());
					if(op.equals("C")) {
						setStatus(a, b);
						update(0, n-1, 1, a);
					}else {
						int c = query(0, n-1, 1, a, b-1);
						if(c < 0) {
							sb.append("-");
						}else if (c > 0) {
							sb.append("+");
						}else {
							sb.append("0");
						}
					}
				}
				sb.append("\n");
			}
		}catch(Exception e) {
//			e.printStackTrace();
		} finally {
			System.out.println(sb.toString());
		}
	}
		
	static void setStatus(int idx, int num) {
		if(num < 0 ) {
			arr[idx] = -1;
		} else if (num > 0) {
			arr[idx] = 1;
		}else {
			arr[idx] = 0;
		}
	}
	
	static int init(int s, int e, int node) {
		if(s == e) return tree[node] = arr[s];
		int mid = (s+e)/2;
		return tree[node] = init(s, mid, node*2)* init(mid+1, e, node*2+1); 
	}
	
	static int update(int s, int e, int node, int idx) {
		if(idx < s || e < idx) return tree[node];
		if(s==e) return tree[node] = arr[idx];
		
		int mid = (s+e)/2;
		return tree[node] = update(s, mid, node*2, idx)* update(mid+1, e, node*2+1, idx);
	}
	
	static int query(int s, int e, int node, int l, int r) {
		if( r < s || e < l) return 1; 
		if(l <= s && e <= r) {
			return tree[node];
		}
		
		int mid = (s+e)/2;
		return query(s, mid, node*2, l, r)*query(mid+1, e, node*2+1, l, r);
	}
	
	static void print(int n) {
		System.out.println("----- tree ------");
		for(int i=0; i<4*n; i++) {
			System.out.print(tree[i] +" ");
		}
		System.out.println();
	}
}
