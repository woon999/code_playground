package baekjoon.ttone.graph;

// #3055 graph 탈출 (BFS) 
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Escape {
	
	static int r,c, res = -1;
	static int[][] map;
	static boolean[][] check, wcheck;
	static int[] dx = { -1, 1 ,0 ,0 };
	static int[] dy = { 0 ,0 , -1, 1};
	static Queue<Node> q = new LinkedList<>();
	static Queue<Node> wq = new LinkedList<>();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		check = new boolean[r][c];
		wcheck = new boolean[r][c];
		
		for(int i=0; i<r; i++) {
			String[] line = br.readLine().split("");
		
			// D: 26, S: 41, *: 0, .: 4, X: 46  
			for(int j=0; j<c; j++) {
				map[i][j] = line[j].charAt(0)-42;
				
				if(!line[j].equals(".")) {
					if(line[j].equals("S")) {
						q.add(new Node(i,j,0));
					}else if(line[j].equals("*")) {
						wq.add(new Node(i,j,0));
					}
				}
			}
		}
	
		
		solve();
		for(int k=0; k<r; k++) {
			for(int j=0; j<c; j++) {
				System.out.print(map[k][j]+" ");
			}
			System.out.println();
		}
		System.out.println(res == -1? "KAKTUS" : res);
	}
	
	static void solve() {

		while(true) {
			waterMove();
			
			int size = q.size();
			if(size ==0) break;
			for(int t=0; t<size; t++) {
				Node nxt = q.poll();
				int nmove = nxt.move;
				
				// 고슴도치 이동
				for(int i=0; i<4; i++) {
					int nx = nxt.x +dx[i];
					int ny = nxt.y +dy[i];
					
					if(nx<0 || ny<0 || nx>r-1 || ny>c-1) continue; 
					if(check[nx][ny]) continue;
					
					// 도착 
					if(map[nx][ny] == 26) {
//						System.out.println("goal! "+ (nmove+1));
						res = nmove+1;
						return;
					}
					
					if(map[nx][ny] == 4 && !wcheck[nx][ny]) {
						check[nx][ny] = true;
//						System.out.println("gosum :" +nmove+ "::"+nx+","+ny);
						q.add(new Node(nx,ny,nmove+1));
					}
					
				}
			}
		}
		
		
	}
	
	// 물 이동 
	static void waterMove() {
		int size = wq.size();
		for(int t=0; t<size; t++) {
			Node nxt = wq.poll();
	
			for(int i=0; i<4; i++) {
				int nx = nxt.x +dx[i];
				int ny = nxt.y +dy[i];
				
				if(nx<0 || ny<0 || nx>r-1 || ny>c-1) continue; 
				if(wcheck[nx][ny]) continue;
				
				//물 이동 
				if(map[nx][ny] == 4) {
					wcheck[nx][ny] = true;
	//				System.out.println("water :" +nmove+ "::"+nx+","+ny);
					wq.add(new Node(nx,ny,0));
				}
				
			}
		}
		
	}
	
	static class Node{
		int x;
		int y;
		int move;
		
		public Node(int x, int y, int move) {
			this.x =x;
			this.y =y;
			this.move =move;
		}
	}
}

