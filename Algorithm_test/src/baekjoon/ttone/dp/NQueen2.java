package baekjoon.ttone.dp;

// #3344 N-Queen - 중간 저장 
import java.io.*;


public class NQueen2 {

	static int n;
	static int[] map;
	static StringBuilder sb = new StringBuilder();
 	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n+1];
		
		for(int i=1; i<n+1; i++) {
			map[1]=i;
			dfs(2);
		}
		System.out.println(sb.toString());
		
	}
static void dfs(int depth) {
		if(sb.length() == n*2) return;
		
		if(depth == n+1) {
//			System.out.println("coin!");
			for(int i=1; i<n+1; i++) {
				sb.append(map[i]+ "\n");
			}
			return;
		}
		
		
		for(int i=1; i<n+1; i++) {
			map[depth] = i;
			if(condition(depth)) {
				dfs(depth+1);
			}
		}
		
		
	}
	
	static boolean condition(int depth) {
		
		for(int i=1; i<depth; i++) {
			// 세로방향 
			if(map[i] == map[depth]) return false;

			// 대각선 방향 
			if(Math.abs(i-depth) == Math.abs(map[i]-map[depth])) return false;
		}
		
		return true;
	}
}
