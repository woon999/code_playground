package baekjoon.shortestpath;

import java.io.*;
import java.util.StringTokenizer;

public class TimeMachine {
	
	static int[][] bus;

	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 
		 int n = Integer.parseInt(st.nextToken());
		 int m = Integer.parseInt(st.nextToken());
		 
		 
		 bus = new int[n+1][3];
	}
}
