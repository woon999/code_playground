package baekjoon.DFSandBFS;

// #2606
import java.io.*;
import java.util.*;

public class Virus {

	static int n;
	static int[][] com;
	static boolean[] checked;
	static int first =1;
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		 n = Integer.parseInt(br.readLine());
		int linkNum = Integer.parseInt(br.readLine());

		com = new int[n + 1][n + 1];
		checked = new boolean[n+1];

		for (int i = 0; i < linkNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			com[start][end] = com[end][start] = 1;
		}
		
		

		bfs();
		System.out.println(count);
		
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(first);
		checked[first] = true;
		
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
	
//			System.out.println(tmp);
			for(int j=1; j< n+1; j++) {
				if(com[tmp][j] ==1 && checked[j]==false) {
					q.offer(j);
					checked[j] = true;
					count++;
					
				}
			}
		}
	}
}
