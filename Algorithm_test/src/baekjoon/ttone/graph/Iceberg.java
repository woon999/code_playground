package baekjoon.ttone.graph;

// #2573 graph 빙산 (bfs)
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Iceberg {

	static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x =x;
			this.y =y;
		}
	}
	static int n,m;
	static int[][] ice;
	static boolean[][] check;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		ice = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve();
	}
	
	static void solve() {
		int year =0;
		int islandNum=0;
		int iceNum=-1;
		while(true) {
			
			// 빙하가 모두 녹았으면 탐색 종료 
			if(iceNum ==0) {
				System.out.println(0);
				break;
			}
			else {
				// 1. 현재 섬 개수 찾기  
				islandNum = countOfIsland();
				System.out.println(year+"년 ---- 현재 섬의 개수 :" +islandNum);
				
				if(islandNum >= 2) {
					System.out.println(year);
					break;
				}
			}
			
			
			// 빙하 녹음 
			iceNum =melting();

			System.out.println("현재 빙하 개수 :" + iceNum);
			
			year++;
			islandNum=0;
		}
		
	}
	
	static int countOfIsland(){
		int cnt=0;
		check = new boolean[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(ice[i][j] !=0 && !check[i][j]) {
					bfs(j,i);
					cnt ++;
				}
			}
		}
		
		return cnt;
	}
	
	static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		
		check[y][x] = true;
		q.add(new Node(x,y));
		
		int cnt=0;
		while(!q.isEmpty()) {
			Node pos = q.poll();
			int px = pos.x;
			int py = pos.y;
			
			cnt++;
			
			for(int i=0; i<4; i++) {
				int nx = px +dx[i];
				int ny = py +dy[i];
				
				if(nx<0 || ny<0 || nx>m-1 || ny>n-1 || check[ny][nx]) continue;
				
				if(ice[ny][nx]!=0) {
					check[ny][nx] = true;
					q.add(new Node(nx,ny));
				}
			}
			
		}
		return cnt;
	}
	
	static int melting() {
		int cnt=0;
		boolean[][] visit = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(ice[i][j] !=0) {
					visit[i][j] =true;
					for(int k=0; k<4; k++) {
						int nx = j +dx[k];
						int ny = i +dy[k];
						
						if(nx<0 || ny<0 || nx>m-1 || ny>n-1 | visit[ny][nx]) continue;
						
						if(ice[ny][nx]==0 && ice[i][j]>0) {
							ice[i][j]--;
						}
					}
					
					if(ice[i][j] > 0)cnt++;
					
				}
			}
		}
		return cnt;
	}
}
