package baekjoon.ttone.graph;

// #13549 graph 숨바꼭질3 - 01BFS  
import java.io.*;
import java.util.*;

public class HideAndSeek3 {
	
	static int[] dx = {-1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		bfs(n, k);
	}
	
	static void bfs(int start, int destination) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] check = new boolean[100_001];
		q.add(new int[]{start,0});
		check[start] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int pos = p[0];
			int move = p[1];
			if(pos == destination) {
				System.out.println(move);
				return;
			}
			int jump = pos*2;
			if(jump < 100001 && !check[jump]) {
				check[jump] = true;
				q.add(new int[]{jump,move});
			}

			for(int i=0; i<2; i++) {
				int next = pos + dx[i];
				if (next>=0 && next <100001 && !check[next]) {
					check[next] = true;
					q.add(new int[]{next,move+1});
				}
			}
		}
	}
}
