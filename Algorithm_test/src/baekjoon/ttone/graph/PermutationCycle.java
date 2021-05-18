package baekjoon.ttone.graph;

// #10451 순열사이클 - dfs 
import java.io.*;
import java.util.*;

public class PermutationCycle {

	static int[] map;
	static boolean[] check;
	static int cycle;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
        
		int t = Integer.parseInt(br.readLine());
		for(int test=0; test<t; test++) {
			int n = Integer.parseInt(br.readLine());
			map = new int[n+1];
			cycle=0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<n+1; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			
			check = new boolean[n+1];
			for(int i=1; i<n+1; i++) {
				if(!check[i]) {
					dfs(i);
					cycle++;
				}
			}
			System.out.println(cycle);
		}
	}
	
	static void dfs(int start) {
		check[start]= true;
		
		int next= map[start];
		if(!check[next]) {
			dfs(map[start]);
		}
		
	}
}
