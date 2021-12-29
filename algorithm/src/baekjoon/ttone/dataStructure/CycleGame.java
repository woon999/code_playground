package baekjoon.ttone.dataStructure;

// #20040 dataStructure 사이클 게임 - unionFind
import java.io.*;
import java.util.*;

public class CycleGame {

	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(find(x)==find(y)) {
				System.out.println(i+1);
				return;
			}else {
				union(x,y);
			}
		}
		
		System.out.println(0);
	}
	
	static int find(int x) {
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) {
			parent[y] = x;
		}else {
			parent[x] = y;
		}
	}
}
