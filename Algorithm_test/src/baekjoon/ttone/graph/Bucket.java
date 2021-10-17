package baekjoon.ttone.graph;

// #2251 graph 물통 - dfs 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Bucket {

	static int[] limit;
	static boolean[][] check;
	static Set<Integer> answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		limit = new int[3];
		check = new boolean[201][201];
		for(int i=0; i<3; i++) {
			limit[i] = Integer.parseInt(st.nextToken());
		}
		
		answer = new TreeSet<>();
		dfs(0,0, limit[2]);
		
		for(int num : answer) {
			System.out.print(num+" ");
		}
	}
	
	static void dfs(int a, int b, int c){
		if(check[a][b]) return;
		
		if(a==0) {
			answer.add(c);
		}
		check[a][b] = true;
		// 0 -> 1
		if(a+b > limit[1]) {
			dfs((a+b)-limit[1], limit[1], c);
		}else {
			dfs(0, a+b, c);
		}
		
		// 1 -> 0
		if(a+b > limit[0]) {
			dfs(limit[0], a+b-limit[0], c);
		}else {
			dfs(a+b, 0, c);
		}
		
		// 2 -> 0
		if(a+c > limit[0]) {
			dfs(limit[0], b, a+c-limit[0]);
		}else {
			dfs(a+c, b, 0);
		}
		
		// 2 -> 1
		if(b+c > limit[1]) 	{
			dfs(a, limit[1], b+c-limit[1]);
		}else {
			dfs(a, b+c, 0);
		}
		
		// 0 -> 2
		dfs(a, 0, b+c);
		// 1 -> 2
		dfs(0, b, a+c);
	}
}

// 200, 0 100 100, 0 156 44, 0 44 156  