package baekjoon.ttone.graph;

// #1600 graph 말이 되고픈 원숭이 - bfs 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MonkeyHorse {

	static int k,w,h;
	static int[][] map;
	static int[] dx= {1,-1,0,0}, dy= {0,0,1,-1};
	static int[] hx = {1,2,2,1,-1,-2,-2,-1}, hy = {2,1,-1,-2,-2,-1,1,2};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
	
		map = new int[h][w];
		
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(0,0));
	}
	
	static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] check = new boolean[h][w][k+1];
		
		check[y][x][0] = true;
		q.add(new int[] {x,y,0,0});
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0], py = pos[1];
			int chance = pos[2], move = pos[3];
			
			if(px==w-1 && py==h-1) {
				 return move;
			}
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx<0 || nx>w-1 || ny<0 || ny>h-1) continue;
				if(check[ny][nx][chance])  continue;
				
				if(map[ny][nx] != 1) {
					check[ny][nx][chance] = true;
					q.add(new int[] {nx,ny,chance, move+1});
					
				}
			}

			if(chance<k){
				for(int i=0; i<8; i++) {
					int nx = px + hx[i];
					int ny = py + hy[i];
					
					if(nx<0 || nx>w-1 || ny<0 || ny>h-1) continue;
					if(check[ny][nx][chance+1])  continue;
					
					if(map[ny][nx] != 1) {
						check[ny][nx][chance+1] = true;
						q.add(new int[] {nx,ny,chance+1, move+1});
					}
				}
			}
		}
		return -1;
	}
}
