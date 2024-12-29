package baekjoon.ttzero.DFSandBFS;

//  #1260

import java.io.*;
import java.util.*;

public class DfsandBfs {

	static int[][] check;
	static boolean[] checked;
	static int n;
	static int m;
	static int start;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		  
		check = new int[1001][1001]; 
		checked = new boolean[1001];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
	   		check[x][y]=check[y][x] =1;
	  	}
		
		dfs(start);

		sb.append("\n");
		Arrays.fill(checked, false);
		
		bfs();
		System.out.println(sb);
	}
	
	static void dfs(int i) {
		checked[i] =true;
		sb.append(i + " ");
		for(int j=1; j<n+1; j++) {
			if(check[i][j] ==1 && checked[j] ==false) {
				dfs(j);
			}
		}
		
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		checked[start] = true;
		sb.append(start +" ");
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			for(int j=1; j<n+1; j++) {
				if(check[tmp][j] ==1 && checked[j]==false) {
					q.offer(j);
					checked[j] = true;
					sb.append(j + " ");
				}
			}

//			System.out.println(q);
		}
	}
}
