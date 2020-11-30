package baekjoon.dynamicplanning3;

// #17070

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MovingPipe {

	static int size;
	static int result;
	static int[][] map;
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result= 0;

		dfs(0,1,1);
		
		System.out.println(result);

	}

	public static void dfs(int y, int x, int type) {
		
		  
		if(y == size-1 && x == size-1) {
			result++;
			return;
		}
		
		//type
		// 0: 세로 , 1: 가로, 2: 대각선
		int[] dir = getDir(type);
		
		for(int i=0; i<dir.length; i++) {
			int ny = y + dy[dir[i]];
			int nx = x + dx[dir[i]];
			
			if(ny<0 || ny>size-1 || nx<0 || nx>size-1 || map[ny][nx] != 0) continue;
			
			if(dir[i] ==2 && (map[y][x+1] !=0 || map[y+1][x] !=0)) continue;
			
			
			dfs(ny, nx, dir[i]);
		
		}
	}

	public static int[] getDir(int type) {
		
		if(type ==0) {
			int[] arr = {0, 2};
			return arr;
		}
		else if(type ==1) {
			int[] arr = {1, 2};
			return arr;
		}
		else if(type ==2){
			int[] arr = {0, 1, 2};
			return arr;
		}
		
		return null;
	}

}
