package baekjoon.tttwo.graph;

// #2234 graph 성곽 - dfs 

import java.io.*;
import java.util.*;

public class Castle {

	static int n, m, size = 0;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i=0; i<n; i++) { // y
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Integer> list = new ArrayList<>();
		check = new boolean[n][m];
		for(int i=0; i<n; i++) { // y
			for(int j=0; j<m; j++) {
				if(!check[i][j]) {
					size = 0;
					check[i][j] = true;
					dfs(j, i);
					list.add(size);
				}
				
			}
		}

		Collections.sort(list, (a,b) -> (b-a));
		System.out.println(list.size());
		System.out.println(list.get(0));
		
		int max = -1;
		for(int i=0; i<n; i++) { // y
			for(int j=0; j<m; j++) {
				for(int bit =1; bit<16; bit*=2) {
					check = new boolean[n][m];
					if((map[i][j] & bit) != 0) { // 벽이 있을 경우
//						System.out.println(i+","+j + "----" + map[i][j] + "&"+bit);
						map[i][j] -= bit;
						size = 0;
						check[i][j] = true;
						dfs(j, i);
						map[i][j] += bit;
//						System.out.println("size :" + size);
						max = Math.max(max, size);
					}
				}
			}
		}
		System.out.println(max);
		
		
	}
	
	static void dfs(int x, int y) {
		size ++;
		int status = map[y][x];
		for(int i=0; i<4; i++) {
			int bit = status >> i;
			if(bit%2 == 0) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx < 0 || ny < 0 || nx > m-1 || ny > n-1 || check[ny][nx]) continue;
				check[ny][nx] = true;
				dfs(nx, ny);
			}
		}
	}
}

