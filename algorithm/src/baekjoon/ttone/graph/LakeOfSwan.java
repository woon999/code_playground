package baekjoon.ttone.graph;


// #3197 graph 백조의 호수 - BFS 
import java.io.*;
import java.util.*;

public class LakeOfSwan {

	static int r,c,ex,ey;
	static char[][] map;
	static boolean[][] check;
	static Queue<int[]> wq,sq;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		wq = new LinkedList<>();
		sq = new LinkedList<>();
		int sx = -1, sy = -1;
		int idx=0;
		for(int i=0; i<r; i++) {
			String line = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'L') {
					if(sx==-1 && sy==-1) {
						sx = j;
						sy = i;
					}else {
						ex = j;
						ey = i;
					}
					map[i][j] ='.';
				}
				
				if(map[i][j] == '.') {
					wq.add(new int[] {j,i});
				}
			}
		}
		
//		print();
		
		check = new boolean[r][c];
		sq.add(new int[] {sx,sy});
		check[sy][sx] = true;
		
		int time=0;
		while(true) {
			if(move()) break;
			melting();
			
//			System.out.println("-----" + time);
//			print();
			time++;
			
		}
		System.out.println(time);
	}
	
	static boolean move() {
		Queue<int[]> q = new LinkedList<>();
		
		while(!sq.isEmpty()) {
			int[] p = sq.poll();
			int px = p[0], py = p[1];
			if(px == ex && py == ey) {
				return true;
			}
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx <0 || ny<0 || nx>c-1 || ny>r-1 || check[ny][nx]) continue;
				
				check[ny][nx] = true;
				if(map[ny][nx] == '.') {
					sq.add(new int[] {nx,ny}); 
				}else if(map[ny][nx] == 'X') { // 다음 탐색지역 
					q.add(new int[] {nx,ny});
				}
			}
		}
		
		sq = q;
		return false;
	}
	
	static void melting() {
		int size = wq.size();
		for(int i=0; i<size; i++) {
			int[] p = wq.poll();
			
			for(int d=0; d<4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				
				if(nx <0 || ny<0 || nx>c-1 || ny>r-1) continue;
				if(map[ny][nx] == 'X') {
					map[ny][nx] = '.';
					wq.add(new int[] {nx,ny});
				}
			}
		}
	}
	
	static void print()	{
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
