package baekjoon.ttone.tree;

// #1289 tree 트리의 가중치 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WeightOfTree {

	static long total;
	static List<int[]>[] list;
	static final int MOD = 1_000_000_007;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			list[u].add(new int[] {v,w});
			list[v].add(new int[] {u,w});
		}
		
		dfs(1,0);
		System.out.println(total);
	}

	static long dfs(int idx, int pa) {
		long res = 1;
		for(int[] nxt : list[idx]) {
			if(nxt[0] != pa) {
				long w = ((dfs(nxt[0], idx))*nxt[1])%MOD;
				
				total = (total+res*w)%MOD;
				res = (res+w)%MOD;
			}
		}
		return res;
	}
}
