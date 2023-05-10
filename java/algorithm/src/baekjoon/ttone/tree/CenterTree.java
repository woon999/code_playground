package baekjoon.ttone.tree;

// #7812 tree 중앙 트리 
import java.io.*;
import java.util.*;

public class CenterTree {

	static int n;
	static long res;
	static int[] size;
	static long[] dp;
	static List<int[]>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n==0) break;
			System.out.println("----- n : " + n);
			dp = new long[n];
			size = new int[n];
			res = Long.MAX_VALUE;
			list = new ArrayList[n];
			for(int i=0; i<n; i++) {
				list[i] = new ArrayList<>();
			}
			
			StringTokenizer st;
			for(int i=0; i<n-1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				list[a].add(new int[] {b,w});
				list[b].add(new int[] {a,w});
			}
			
			init(0,-1);
			getCostByRoot(0, -1, dp[0]);
			
			sb.append(res+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void init(int cur, int pa) {
		size[cur] = 1;
		for(int[] nxt : list[cur]) {
			if(nxt[0] != pa) {
				init(nxt[0], cur);
				
				size[cur] += size[nxt[0]];
				dp[cur] += dp[nxt[0]] + nxt[1]*size[nxt[0]];
			}
		}
	}
	
	static void getCostByRoot(int cur, int pa, long cost) {
		res = Math.min(res, cost);
		
		for(int[] nxt : list[cur]) {
			if(nxt[0] != pa) {
				getCostByRoot(nxt[0], cur, cost- (size[nxt[0]]*nxt[1]) + (n-size[nxt[0]])*nxt[1]);
			}
		}
	}

	static void print() {
		System.out.println("size");
		for(int i=0; i<n; i++) {
			System.out.print(size[i]+" ");
		}
		System.out.println();
		System.out.println("prefix sum");
		for(int i=0; i<n; i++) {
			System.out.print(dp[i]+" ");
		}
		System.out.println();
	}
}
