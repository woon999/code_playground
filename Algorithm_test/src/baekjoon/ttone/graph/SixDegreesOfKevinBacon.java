package baekjoon.ttone.graph;

// #1389 graph 케빈 베이컨의 6단계 법칙 (bfs, dfs, floyd-warshall) 
import java.io.*;
import java.util.*;

public class SixDegreesOfKevinBacon {

	static int n, m;
	static List<Integer>[] list;
	static int[] memo, move;
	static boolean[] check;
	static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		memo = new int[n+1];
		list = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		// DFS, BFS input
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		
//		----------- DFS main code ------------
		// 1 , 2,3,4,5
		// 2 , 1,3,4,5 ...
//		for(int i=1; i<n+1; i++) {
//			for(int j=1; j<n+1; j++) {
//				answer= Integer.MAX_VALUE; 
//				check = new boolean[n+1];
//				if(i!=j) {
//					dfs(i,j,0);
////					System.out.println(i+"->"+j +" : " +answer);
//					memo[i] += answer;
//				}
//			}
//		}
//		-------------------------------------------
		
		
//		----------- BFS main code ------------
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				check = new boolean[n+1];
				move = new int[n+1];
				if(i!=j) {
					int res = bfs(i,j);
					System.out.println(i+"->"+j +" : " + res);
					memo[i] += res;
				}
			}
		}
		
//		-------------------------------------------
		
//		----------- Floyd-Warshall main code ------------
		
//		int[][] floyd = new int[n+1][n+1];
//		
//		// 초기화 
//		for(int i=1; i<n+1; i++) {
//			for(int j=1; j<n+1; j++) {
//				if(i==j) floyd[i][j] = 0;
//				else floyd[i][j] = 99999999;
//			}
//		}
//		
//		for(int i=0; i<m; i++) {
//			st = new StringTokenizer(br.readLine());
//			
//			int a = Integer.parseInt(st.nextToken());
//			int b = Integer.parseInt(st.nextToken());
//			
//			floyd[a][b] =1;
//			floyd[b][a] =1;
//		}
//		
//		
//		for(int k=1; k<n+1; k++) {
//			for(int i=1; i<n+1; i++) {
//				for(int j=1; j<n+1; j++) {
//					if(floyd[i][j] > floyd[i][k] + floyd[k][j]) {
//						floyd[i][j] = floyd[i][k] + floyd[k][j];
//					}
//				}
//				
//			}
//			
//		}
//		
//		for(int i=1; i<n+1; i++) {
//			for(int j=1; j<n+1; j++) {
//				System.out.print(floyd[i][j] +" ");
//			}
//			System.out.println();
//		}
//		
//		for(int i=1; i<n+1; i++) {
//			for(int j=1; j<n+1; j++) {
//				memo[i] += floyd[i][j];
//			}
//		}
		
		
		
//		----------- 결과 출력 (공통) -----------
		int min = Integer.MAX_VALUE;
		int idx =0;
		for(int i=1; i<n+1; i++) {
			if(min > memo[i]) {
				min = memo[i];
				idx =i;
			}
		}
		System.out.println(idx);

		
	}
	
	static int bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		check[start] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			int p_node = q.poll();
			
			if(p_node == end) {
//				System.out.println("coin! " +move[p_node]);
				return move[p_node];
			}
			for(int i=0; i<list[p_node].size(); i++) {
				int next = list[p_node].get(i);
				if(!check[next]) {
					check[next]= true;
					move[next] = move[p_node] +1;
					q.add(next);
				}
			}
		}
		return 0;
	}
	
	static void dfs(int start, int end, int cnt) {
		if(start == end) {
//			System.out.println("coin! "+ cnt);
			answer = Math.min(answer, cnt);
			return;
		}
		
		check[start] = true;
		
		for(int i=0; i<list[start].size(); i++) {
			int next = list[start].get(i);
			if(!check[next]) {
				dfs(next,end, cnt+1);
			}
		}
		
		check[start]=false;
		
	}
}