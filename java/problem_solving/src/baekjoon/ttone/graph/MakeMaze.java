package baekjoon.ttone.graph;

// #2665 bfs 미로만들기 - priority queue 
import java.io.*;
import java.util.*;

public class MakeMaze {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n][n];
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<line.length(); j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
//		bfs(map,n);
		bfs2(map,n);
	}
	
	// bfs + 우선순위 큐 
	static void bfs2(int[][] map, int n) {
		Queue<int[]> q = new PriorityQueue<>((o1,o2) -> (o1[3]-o2[3]));
		boolean[][] check = new boolean[n][n];
		q.add(new int[] {0,0,0,0});
		check[0][0] = true;
		
		int answer =0;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0], py=p[1];
			int mv = p[2], blackToWhite = p[3];
			
			if(px==n-1 && py==n-1) {
				answer = blackToWhite;
				System.out.println(px+","+py +" :" +mv +" ,," + blackToWhite);	
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nx = px+dx[i];
				int ny = py+dy[i];
				
				if(nx<0 || ny<0 || nx>n-1 || ny>n-1 || check[ny][nx]) continue;
				check[ny][nx] = true;
				if(map[ny][nx] ==1) {
					q.add(new int[] {nx, ny, mv+1, blackToWhite});
				}
				else{
					q.add(new int[] {nx, ny, mv+1, blackToWhite+1});
				}
			}
			
		}
		System.out.println(answer);
	}
	
	// bfs
	static void bfs(int[][] map, int n) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] check = new boolean[n][n][n*n];
		q.add(new int[] {0,0,0,0});
		check[0][0][0] = true;
		
		int min = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0], py=p[1];
			int mv = p[2], blackToWhite = p[3];
			
			if(min < blackToWhite) continue;
			if(px==n-1 && py==n-1) {
				if(min > blackToWhite) {
					min = blackToWhite;
					System.out.println(px+","+py +" :" +mv +", " + blackToWhite);	
				}
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int nx = px+dx[i];
				int ny = py+dy[i];
				
				if(nx<0 || ny<0 || nx>n-1 || ny>n-1 || check[ny][nx][blackToWhite]) continue;
				check[ny][nx][blackToWhite] = true;
				if(map[ny][nx] ==1) {
					q.add(new int[] {nx, ny, mv+1, blackToWhite});
				}
				if(map[ny][nx] ==0) {
					q.add(new int[] {nx, ny, mv+1, blackToWhite+1});
				}
			}
			
		}
		System.out.println(min==Integer.MAX_VALUE? 0 : min);
	}
}
