package baekjoon.ttone.graph;

// #5567 graph 결혼식 - dfs 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Wedding {

	static List<Integer>[] list;
	static List<Integer> ans;
	static boolean[] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		check = new boolean[n+1];
		ans = new ArrayList<>();
		list = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		StringTokenizer st = null;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		check[1] = true;
		dfs(1,0);
		System.out.println(ans.size());
	}
	
	static void dfs(int pos, int depth) {
		if(depth>2) return;
			
		if(pos!=1 && !ans.contains(pos)) {
			ans.add(pos);
		}
		
		check[pos] = true;
		for(int nxt : list[pos]) {
			if(check[nxt]) continue;
			
			if(pos==1) {
				dfs(nxt, depth+1);
			}else {
				if(depth!=0) {
					dfs(nxt, depth+1);
				}
			}
		}
		check[pos] = false;
	}
}
