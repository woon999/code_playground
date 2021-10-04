package baekjoon.ttone.tree;

// #16437 tree 양 구출 작전 - dfs 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SheepRescue {

	static List<Integer>[] list; 
	static long[] memo;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		memo = new long[n+1];
		StringTokenizer st;
		for(int i=2; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			
			char c = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			list[p].add(i);
			if(c=='W') {
				a *= -1;
			}
			memo[i] = a;
		}
		
		
		dfs(1,-1);
		
		System.out.println(memo[1]);
	}
	
	static void dfs(int idx, int pa) {
		for(int nxt : list[idx]) {
			dfs(nxt, idx);
		}
		
		if(pa != -1) {
			if(memo[idx]>0) {
				memo[pa] += memo[idx];
			}
		}
		
	}
}
