package baekjoon.tttwo.tree;

// #20924 tree 트리의 기둥과 기지 
import java.io.*;
import java.util.*;

public class TreePilarAndBranch {

	static int r;
	static List<int[]>[] list;
	static int pilar, maxBranch;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			
			list[a].add(new int[] {b,len});
			list[b].add(new int[] {a,len});
		}
		
		dfs(r, -1,  0, 1);
		System.out.println(pilar + " " + maxBranch);
	}
	
	static void dfs(int here,  int pa, int len, int flag) {
		for(int[] nxt : list[here]) {
			if(nxt[0] == pa) continue;
			
			if(flag ==1) { 
				if((here == r && list[here].size()>1) || (here!=r && list[here].size()>2)) {
					pilar = len;
					dfs(nxt[0],here,  nxt[1], 0);	
				} else dfs(nxt[0], here, len +nxt[1], 1);
			} else if(flag == 0) {
				dfs(nxt[0], here, len +nxt[1], 0);
			} 
		}
		
//		System.out.println("size : " +list[here].size());
		if(list[here].size()==1) {
//			System.out.println(flag + "# " + here + ", len :" + len );
			if(flag == 1) {
				pilar = Math.max(pilar, len);
			} else {
				maxBranch = Math.max(maxBranch, len);
			}
		}
	}
}




