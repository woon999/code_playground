package baekjoon.ttzero.DFSandBFS;


// #11724
import java.io.*;
import java.util.StringTokenizer;

public class ConnectedComponent {

	static int[][] graph = new int[1001][1001];
	static int v;
	static int e;
	static boolean[] visited = new boolean[1001];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		int a,b;
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			graph[a][b] = graph[b][a] = 1;
		}
		
		int res = 0;
		
		//dfs 
		for(int i=1; i<v+1; i++) {
			if(visited[i] == false) {
				dfs(i);
				res++;
			}
		}
		
		System.out.println(res);
	
	}
	
	static void dfs(int idx) {
		if(visited[idx] == true) return;
		else {
			visited[idx] = true;
			for(int i=1; i<v+1; i++) {
				if(graph[idx][i] == 1) dfs(i);
			}
		}
	}
	
}
