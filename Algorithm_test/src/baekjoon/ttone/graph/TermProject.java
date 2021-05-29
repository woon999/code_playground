package baekjoon.ttone.graph;

// #9466 graph 텀 프로젝트 - dfs 
import java.io.*;
import java.util.*;

public class TermProject {

	static int n, cnt;
	static int[] link;
	static boolean[] check,cycle;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = Integer.parseInt(br.readLine());
		
		for(int test=0; test<t; test++) {
			n = Integer.parseInt(br.readLine());
			link = new int[n+1];
			check = new boolean[n+1];
			cycle = new boolean[n+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<n+1; i++) {
				int from = i;
				int to = Integer.parseInt(st.nextToken());
				
				link[from]=to;
			}
			cnt =0;
			for(int i=1; i<n+1; i++) {
				dfs(i);
			}
			System.out.println(n-cnt);
		}
		
	}
	static void dfs(int pos) {
		check[pos] = true;
		int next = link[pos];
		if(!check[next]) {
//			System.out.println(pos+"->" +next);
			dfs(next);
		}else {
			if(!cycle[next]) {
//				System.out.println("cycle"+ next);
				cnt++;
				while(next != pos) {
					cnt++;
					next = link[next];
					
				}
			}
		}
		cycle[pos] = true; 
	}
}

