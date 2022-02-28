package baekjoon.ttone.tree;

//#2132 tree 나무 위의 벌레 
import java.io.*;
import java.util.*;

public class BugOfTree {

	static int r, s, e;
	static int[] data;
	static List<int[]>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		data = new int[n+1];
		list = new ArrayList[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			data[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(new int[] {b, data[b]});
			list[b].add(new int[] {a, data[a]});
		}

		
		s = 1; e = 10001;
		dfs(1, 0, data[1]);
		
		s = e; r = 0; e = 10001;
		dfs(s, 0, data[s]);
		
		System.out.println(r +" " +  Math.min(s, e));
	}
	
	
	static void dfs(int idx, int pa, int cost) {
		if(cost > r) {
			r = cost;
			e = idx;
		}else if(cost == r) {
			e = Math.min(e, idx);
		}
		
		for(int[] nxt : list[idx]) {
			if(nxt[0] != pa) {
				dfs(nxt[0], idx, cost+nxt[1]);
			}
		}
	}
}



