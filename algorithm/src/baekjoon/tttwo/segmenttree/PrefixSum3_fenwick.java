package baekjoon.tttwo.segmenttree;

// #11658 segmenttree 구간 합 구하기 3 - fenwick 
import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum3_fenwick {

	static int n;
	static int[][] arr, tree;
	static void update(int x, int y, int val) {
		while(x <= n){
			for(int i=y; i<=n;) {
				tree[x][i] += val;
				i += i&-i;
			}
			x += x&-x;
		}
	}
	
	static int sum(int x, int y) {
		int result = 0;
		while(x > 0) {
			for(int i=y; i>0;) {
				result += tree[x][i];
				i -= i&-i;
			}
			x -= x&-x;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][n+1];
		tree = new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			 st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				update(i,j,arr[i][j]);
			}
		}
		
		print();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			if(op == 1) {
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				sb.append((sum(x2, y2) - sum(x2, y1-1) - sum(x1-1, y2) + sum(x1-1,y1-1))+"\n");
			}else {
				int c = Integer.parseInt(st.nextToken());
				update(x1, y1, c-arr[x1][y1]);
				arr[x1][y1] = c;
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void print() {
		for(int i=1; i<arr.length; i++) {
			for(int j=1; j<arr[i].length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("---");
		
		for(int i=1; i<tree.length; i++) {
			for(int j=1; j<tree[i].length; j++) {
				System.out.print(tree[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
