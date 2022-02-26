package baekjoon.tttwo.tree;

// #20530 tree 양분 
import java.io.*;
import java.util.*;

public class Nutrition {

	static List<Integer>[] list;
	static int[] parent, group;	
	static boolean[] isCycle, visited, finished;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		isCycle = new boolean[n+1];
		visited = new boolean[n+1];
		finished = new boolean[n+1];
		parent = new int[n+1];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		findCycle(1, 0);
		
		group = new int[n+1];
		Arrays.fill(visited, false);
		for(int i=1; i<=n; i++) {
			if(!visited[i] && isCycle[i]) {
				grouping(i,i);	
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(group[a] == group[b]) {
				sb.append(1);
			}else {
				sb.append(2);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void grouping(int here, int g) {
		visited[here] = true;
		group[here] = g;
		for(int nxt : list[here]) {
			if(isCycle[nxt] || visited[nxt]) continue;
			grouping(nxt, g);
		}
	}
	
	static void findCycle(int here, int pa) {
		visited[here] = true;
		for(int nxt : list[here]) {
			if(!visited[nxt]) {
				parent[nxt] = here;
				findCycle(nxt, here);
			} else if(!finished[nxt] && nxt != pa) {
				checkCycle(here, nxt);
			}
		}
		finished[here] = true;
	}
	
	static void checkCycle(int here, int root) {
		isCycle[here] = true;
		if(here == root) return;
		checkCycle(parent[here], root);
		
	}
	
}
