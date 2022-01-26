package baekjoon.tttwo.bipartitematching;

// #1298 bipartitematching 노트북의 주인을 찾아서 
import java.io.*;
import java.util.*;

public class FindNotebook {

	static List<Integer>[] list;
	static int[] task;
	static boolean[] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		task = new int[n+1];
		check = new boolean[n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
		}
		
		int cnt = 0;
		for(int i=1; i<=n; i++) {
			Arrays.fill(check, false);
			if(dfs(i)) cnt++; 
		}
		
		System.out.println(cnt);
		
	}
	
	static boolean dfs(int here) {
		for(int nxt : list[here]) {
			if(check[nxt]) continue;
			check[nxt] = true;
			
			if(task[nxt] == 0  || dfs(task[nxt])) {
				task[nxt] = here;
				return true;
			}
		}
		return false;
	}
}
