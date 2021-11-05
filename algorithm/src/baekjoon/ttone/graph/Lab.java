package baekjoon.ttone.graph;

// #14502 연구소 (백트래킹, bfs)
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Lab {
	
	static int n,m;
	static int[][] map;
	static int[][] testMap;
	static List<Pos> virus;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int max =-1;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		testMap = new int[n][m];
		virus = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num ==2) {
					virus.add(new Pos(i,j));
				}
			}
		}
		
		makeWall(0,0);
		System.out.println(max);
		
	}
	
	// Backtracking : 벽3개 세우기 
	static void makeWall(int start, int wallNum) {
	
		if(wallNum == 3) {
			// map 복사
			for (int i = 0; i < n; i++) {
	            for (int j = 0; j < m; j++) {
	            	testMap[i][j] = map[i][j];
	            }
	        }
			
			for(Pos pos : virus) {
				bfs(pos.x, pos.y);
			}
			
			max = Math.max(max, getSafeSize());
			return;
		}
		
		for(int i=start; i< n*m; i++) {
			int x = i/m;
			int y = i%m;
			
			if(map[x][y] ==0) {
				map[x][y] =1;
				makeWall(i+1, wallNum+1);
				map[x][y] =0;
			}
		}
	}
	
	
	// bfs: virus 퍼트리기
	static void bfs(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx <0 || ny <0 || nx>n-1 || ny>m-1) continue;
			
			if(testMap[nx][ny] ==0) {
				testMap[nx][ny] =2;
				bfs(nx,ny);
			}
		}
	}
	
	static int getSafeSize() {
		int size =0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(testMap[i][j] ==0) {
					size++;
				}
			}
		}
		return size;
	}
}
class Pos {
	int x;
	int y;
	public Pos(int x, int y) {
		this.x =x;
		this.y =y;
	}
}
