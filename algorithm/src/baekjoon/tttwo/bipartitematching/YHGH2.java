package baekjoon.tttwo.bipartitematching;

// #11376 bipartitematching 열혈강호 2
import java.io.*;
import java.util.*;

public class YHGH2 {

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
		
		task = new int[m+1];
		check = new boolean[m+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int nn = Integer.parseInt(st.nextToken());
			for(int j=0; j<nn; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int cnt = 0;
		for(int i=1; i<=n; i++) {
			for(int j=0; j<2; j++) {
				Arrays.fill(check, false);
				if(dfs(i)) cnt++; 
			}
		}
		
		System.out.println(cnt);
		
		for(int i=1; i<=n; i++) {
			if(task[i] != 0) {
				System.out.println(task[i] +" - " + i);
			}
		}
		
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
