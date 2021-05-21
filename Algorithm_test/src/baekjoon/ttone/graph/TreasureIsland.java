package baekjoon.ttone.graph;

// #2589 graph 보물섬 - bfs 
import java.io.*;
import java.util.*;

public class TreasureIsland {

	static int n,m;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			String[] line = br.readLine().split("");
			for(int j=0; j<m; j++) {
				// W: 87, L: 76
				int num = line[j].charAt(0)-0;
				if(num == 87)map[i][j] = -1;
				else map[i][j] = 1;
			}
		}
		
		
		int max = -1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] ==1) {
					check = new boolean[n][m];
					int res =  bfs(j,i);
					if(res > max) {
						max =res;
						System.out.println(i+","+j +" :" + max);
					}
					
				}
				
			}
		}
		System.out.println(max);
		
		
	}
	
	static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		
		int maxMove =-1;
		check[y][x] =true;
		q.add(new int[] {x,y,0});
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0];
			int py = pos[1];
			int move = pos[2];
			
			if(move > maxMove) {
				maxMove = move;
			}
			
//			System.out.println(px+","+py);
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx <0 || nx >m-1 || ny<0 || ny >n-1) continue;
				
				if(!check[ny][nx] && map[ny][nx]==1) {
					check[ny][nx] =true;
					q.add(new int[]{nx,ny, move+1});
					
				}
			}
		}
		
		return maxMove;
		
	}
}
