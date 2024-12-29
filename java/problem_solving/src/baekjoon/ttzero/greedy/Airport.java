package baekjoon.ttzero.greedy;

// #10775
import java.io.*;

public class Airport {

	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int g = Integer.parseInt(br.readLine());
		int p = Integer.parseInt(br.readLine());

		parent = new int[g+1];
		int result = 0;
		for(int i=0; i< g+1; i++) {
			parent[i] = i;
		}
		
		while(p-- >0) {
			int num = Integer.parseInt(br.readLine());
			
			if(find(num) ==0 )break;
			
			result++;
			union(find(num), find(num) -1);
		}
		System.out.println(result);

	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		parent[a] = b;
		
	}
	
	static int find(int x) {
		if(x == parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}
}
