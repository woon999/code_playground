package baekjoon.ttone.graph;

// #4179 graph 불 - bfs 
import java.io.*;
import java.util.*;

public class Fire2 {

	static int r,c;
	static char[][] map;
	static Queue<int[]> fireQ, personQ;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		
		String line;
		fireQ = new LinkedList<>();
		personQ = new LinkedList<>();
		for(int i=0; i<r; i++) {
			line = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'J') {
					personQ.add(new int[] {j,i,0});
				}else if(map[i][j] == 'F') {
					fireQ.add(new int[] {j,i});
				}
			}
		}
		
		int res = -1;
		out: while(true) {
			// 불번짐 
			int fSize = fireQ.size();
			for(int i=0; i<fSize; i++) {
				int[] p = fireQ.poll();
				fireMoving(p[0], p[1]);
			}
			
			int pSize = personQ.size();
			for(int i=0; i<pSize; i++) {
				int[] p = personQ.poll();
				res = escape(p[0], p[1], p[2]);
				if(res != -1) break out;
			}
			if(personQ.isEmpty()) break;
		}
		
		if(res == -1) {
			System.out.println("IMPOSSIBLE");
		}else {
			System.out.println(res);
		}
	
	}
	
	static void print() {
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("------");
	}
	
	static int escape(int x, int y, int time) {
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || ny<0 ||  nx>c-1 || ny>r-1) {
				return time+1;
			}
			
			if(map[ny][nx] == '.') {
				map[ny][nx] = 'J';
				personQ.add(new int[] {nx, ny, time+1});
			}
		}
		return -1;
	}
	
	static void fireMoving(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx > c-1 || ny > r-1) continue;
			
			if(map[ny][nx] == '.' || map[ny][nx] == 'J') {
				map[ny][nx] = 'F';
				fireQ.add(new int[] {nx, ny});
			}
		}
	}
}
