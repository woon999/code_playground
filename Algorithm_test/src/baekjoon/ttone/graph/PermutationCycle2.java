package baekjoon.ttone.graph;

// #10451 순열 사이클 - union-set :반례가 뭘까? 
import java.io.*;
import java.util.*;

public class PermutationCycle2 {

	static int[] parents;
	static int[] ranks;
	static int cycle;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int test=0; test<t; test++) {
			int n = Integer.parseInt(br.readLine());
			parents = new int[n+1];
			ranks = new int[n+1];
			cycle=0;
			for(int i=1; i<n+1; i++) {
				parents[i] = i;
			}
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=1; i<n+1; i++) {
				int a = i;
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
				
//				for(int k=1; k<n+1; k++) {
//					System.out.print(parents[k]+" ");
//				}
//				System.out.println();
//			
				
			}
			
			for(int k=1; k<n+1; k++) {
				System.out.print(ranks[k]+" ");
				if(ranks[k]!=0) {
					cycle++;
				}
			}
			System.out.println();
			
			sb.append(cycle+"\n");
		}
		System.out.println(sb.toString());
	}
	
	
	static int find(int x) {
		if(parents[x] == x) return x;
		
		int parent= find(parents[x]);
		return parent;
		
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);

		// y가 부모 
		if(ranks[x] < ranks[y]) {   
			parents[x]= y;
			ranks[y]++;
			
		}else {
			parents[y] = x;  
			
			if(ranks[x]==ranks[y]) {
				ranks[x]++;
				
				// 길이가 1이상인 두 싸이클이 하나로 합쳐질 때 
				if(ranks[y]!=0 && x!=y) { 
					ranks[x]++;
					ranks[y]--;
				}
			}
		}
	}
}
