package baekjoon.ttone.tree;

// #13244 tree Tree - union-find
import java.io.*;
import java.util.*;

public class Tree_13244 {

	static int[] parents;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while(t-->0) {
			int n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			
			parents = new int[n+1];
			for(int i=1; i<n+1; i++) {
				parents[i] = i;
			}
			
			boolean flag = false;
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(find(a)!=find(b)) {
					union(a,b);
				}else {
					flag = true;
				}
			}
			
			Set<Integer> set = new HashSet<Integer>();
			for(int i=1; i<n+1; i++) {
				set.add(find(parents[i]));
			}
			
			if(flag || set.size()>1) {
				System.out.println("graph");
			} else {
				System.out.println("tree");
			}
		}
		
		
	}
	static int find(int x) {
		if(parents[x] == x) {
			return x;
		}
		return parents[x]= find(parents[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x > y) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		
		parents[y] = x;
	}
}
