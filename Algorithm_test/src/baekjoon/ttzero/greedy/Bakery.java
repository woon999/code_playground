package baekjoon.ttzero.greedy;

// #3109
import java.io.*;
import java.util.StringTokenizer;

public class Bakery {
	
	static int r,c,count;
	static boolean[][] map;
	static int[] dx = {-1, 0 ,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new boolean[r][c];
		
		for(int i=0; i<r; i++) {
			String str =br.readLine();
			for(int j=0; j<c; j++) {
				if(str.charAt(j) == 'x')
					map[i][j] = true;
			}
		}
		
		for(int i=0; i<r; i++) {
			dfs(i,0);
		}
		 
		System.out.println(count);
	}
	
	static boolean dfs(int x, int y) {
		int nx, ny;
	
		for(int i=0; i<3; i++) {
			nx = x + dx[i];
			ny = y + 1;
			
			if(nx>=0 && nx <r && map[nx][ny] ==false) {
				if(ny == c-1) {
					count ++;
					return true;
				}
				
				map[nx][ny] = true;
				if(dfs(nx,ny)) return true;
			}
			
		}
		
		return false;
	}
}
