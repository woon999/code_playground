package baekjoon.tttwo.graph;

// #3184 graph 양 - dfs 
import java.io.*;
import java.util.*;

public class Sheep {

	static int n,m, sheep, wolf;
	static char[][] map;
	static boolean[][] check;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
//		print();
		
		int totalSheep =0, totalWolf=0;
		check = new boolean[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!check[i][j] && map[i][j] != '#') {
//					System.out.print("(" + j +"," + i +")");
					check[i][j] = true;
					sheep =0; wolf = 0;
					dfs(j,i);
//					System.out.println("---- area: " + sheep +", " + wolf);
					
					if(sheep <= wolf) {
						sheep = 0;
					}else {
						wolf = 0;
					}
					totalSheep += sheep;
					totalWolf += wolf;
				}
			}
		}
		
		System.out.println(totalSheep +" " + totalWolf);
		
	}
	
	static void dfs(int x, int y) {
		if(map[y][x] == 'o') {
			sheep += 1;
		}else if(map[y][x] == 'v') {
			wolf += 1;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx > m-1 || ny > n-1 || check[ny][nx]) continue;
			if(map[ny][nx] != '#') {
				check[ny][nx] = true;
				
				dfs(nx, ny);
			}
		}
	}
	
	static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
