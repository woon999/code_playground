package baekjoon.ttone.tree;

// #1693 tree 트리 색칠하기 - 트리dp, dfs 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class TreeColor {

	static int n, INF = 987654321;
	static List<Integer>[] list;
	static List<Integer>[] tree;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		tree = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
			tree[i] = new ArrayList<>();
		}
		
		StringTokenizer st = null;
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		makeTreeData(1,-1);
		
		
		dp = new int[n+1][18];
		for(int i=1; i<n+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		int res = INF;
		for(int c=1; c<18; c++) {
			res = Math.min(res, painting(1,c));		
		}
		System.out.println(res);
//		print();
		
		
	}
	static int painting(int cur, int color) {
		if(dp[cur][color] != -1) return dp[cur][color];
		
		dp[cur][color]=0;
		for(int nxt : tree[cur]) {
			int tmp = INF;
			for(int i=1; i<18; i++) {
				if(color!=i) {
					tmp = Math.min(tmp, painting(nxt,i));
				}
			}
			
			dp[cur][color] += tmp; 
		}
		
		return dp[cur][color]+=color;
	}
	
	static void makeTreeData(int idx, int pa) {
		for(int nxt : list[idx]) {
			if(nxt != pa){
				tree[idx].add(nxt);
				makeTreeData(nxt ,idx);
			}
		}
	}
	
	static void print() {
		System.out.println("---- tree data ---- ");
		for(int i=1; i<n+1; i++	) {
			System.out.print(i +" - ");
			for(int nxt : tree[i]) {
				System.out.print(nxt+" ");
			}
			System.out.println();
		}
		
		System.out.println("---- tree color data ---- ");
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<18; j++) {
				System.out.print(dp[i][j] +" ");
			}
			System.out.println();
		}
	}
}

//17
//1 2
//1 3
//1 4
//1 5
//3 6
//3 7
//4 8
//4 9
//8 13
//5 10
//5 11
//5 12
//10 14
//10 15
//11 16
//14 17
