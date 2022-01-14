package baekjoon.tttwo.segmenttree;

// #12837 segmenttree 가계부 - 펜윅 트리 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AccountBook_Fenwick {

	static int n;
	static long[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		tree = new long[n+1];
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(op == 1) {
				update(a,b);
//				print(n);
			} else {
				sb.append(sum(b)- sum(a-1)+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static void print(int n) {
		for(int i=1; i<n+1; i++){
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
	
	static void update(int idx, int val) {
		while(idx <= n) {
			tree[idx] += val;
			idx += (idx & -idx);
		}
	}
	
	static long sum(int idx) {
		long result = 0;
		while(idx > 0) {
			result += tree[idx];
			idx -= (idx & -idx);
		}
		return result;
	}
	
}
