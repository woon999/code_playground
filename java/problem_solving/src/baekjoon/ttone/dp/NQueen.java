package baekjoon.ttone.dp;

// #9663 N-Queen (dfs, 백트래킹) 
import java.io.*;

public class NQueen {

	static int n;
	static int[][] map;
	static int count=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
//		map = new int[n][n];
		
		for(int i=1; i<n+1; i++) {
			map = new int[n+1][n+1];
			map[1][i] =1;
			dfs(map, 2);
		}
		System.out.println(count);
	}
	
	
	static void dfs(int[][] map, int depth) {
		
		if(depth == n+1) {
			count++;
			System.out.println("coin!");
			return;
		}
		
		for(int i=1; i<n+1; i++) {
			if(condition(map, i, depth)) {
				map[depth][i] = 1;
				dfs(map, depth+1);
				map[depth][i] = 0;
			}
		}
		
		
	}
	
	static boolean condition(int[][] map, int x, int depth) {
		
		// 세로방향 
		for(int i=1; i<depth; i++) {
			if(map[i][x] == 1) return false;
		}
		
		// \방향 
		int px = x - 1;
	    int py = depth - 1;
	    while(px > 0 && py > 0) {
	    	if(map[py--][px--] == 1)
	    		return false;
	    }
	    
	    
	    // /방향 
	    px = x+1;
	    py = depth-1;
	    while(px < n+1 && py > 0) {
	    	if(map[py--][px++] == 1)
	    		return false;
	    }
	        
		
		return true;
	}
}
