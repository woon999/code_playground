package baekjoon.ttone.bipartitematching;

// #11377 bipartitematching 열혈강호 3
import java.io.*;
import java.util.*;

public class YHGH3 {

	static List<Integer>[] list;
	static int[] task;
	static boolean[] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
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
		for(int i=1; i<=2*n; i++) {
			Arrays.fill(check, false);
			if(i<=n) {
				if(dfs(i)) cnt++;
			}
			else{
				if(dfs(i-n) && k>0) {
					k--;
					cnt++;	
				}
			}
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
