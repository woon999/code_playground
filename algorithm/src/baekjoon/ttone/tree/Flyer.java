package baekjoon.ttone.tree;

// #19542 tree 전단지 돌리기 - dfs 
import java.io.*;
import java.util.*;

public class Flyer {

	static int n,s,d,cnt;
	static List<Integer>[] list;
	static int[] depth;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		depth = new int[n+1];
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		dfs(s, -1);
		System.out.println(cnt*2);
	}
	
	static int dfs(int idx, int pa) {
		for(int nxt : list[idx]) {
			if(nxt != pa) {
				depth[idx] = Math.max(depth[idx], dfs(nxt, idx)+1);
			}
		}
		
		if(idx!=s && depth[idx] >=  d) {
			cnt++;
		}
		return depth[idx];
	}
}



