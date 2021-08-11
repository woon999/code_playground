package baekjoon.ttone.graph;

// #11559 graph 뿌요뿌요 - bfs, 시뮬레이션 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PuyoPuyo {

	static char[][] map;
	static boolean[][] check;
	static List<int[]> list;
	static int[] dx = {-1, 1, 0, 0}, dy= {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[12][6];
		for(int i=0; i<12; i++) {
			String line = br.readLine();
			for(int j=0; j<6; j++) {
				char c = line.charAt(j);
				map[i][j] = c;
			}
		}
		
		int ans = 0;
		while(true) {
			boolean flag = false;
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(map[i][j] != '.') {
						char color = map[i][j];
						check = new boolean[12][6];
						list = new ArrayList<>();
						int connect = bfs(j,i,color);
						if(connect >= 4) {
							flag = true;
							for(int[] change : list) {
								int cx = change[0], cy = change[1];
								map[cy][cx]	= '.';
							}
						}
					}
				}
			}
			
			if(!flag) break;
			else ans++;
			
			mapUpdate();
			
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					System.out.print(map[i][j] +" ");
				}
				System.out.println();
			}
			System.out.println("---");
		}


		System.out.println(ans);
		
	}
	
	static int bfs(int x, int y, char color) {
		Queue<int[]> q = new LinkedList<>();
		
		check[y][x] = true;
		q.add(new int[] {x, y});
		list.add(new int[] {x,y});
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0];
			int py = pos[1];
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx<0 || nx>5 || ny<0 || ny>11) continue;
				if(check[ny][nx]) continue;
				
				if(map[ny][nx]==color) {
					list.add(new int[] {nx,ny});
					check[ny][nx] = true;
					q.add(new int[] {nx,ny});
				}
			}
		}
		return list.size();
	}
	
	static void mapUpdate() {
		Queue<Character> q = new LinkedList<>();
		for(int i=0; i<6; i++) {
			for(int j=11; j>=0; j--) {
				if(map[j][i] != '.') q.add(map[j][i]);
				map[j][i] = '.';
			}
			int h = 11;
			while(!q.isEmpty()) {
				map[h][i] = q.poll();
				h--;
			}
		}
	}
}
