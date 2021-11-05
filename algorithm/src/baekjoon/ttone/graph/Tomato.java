package baekjoon.ttone.graph;

// #7576 graph 토마토 - bfs 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato {

	static int n,m, status;
	static int[][] map;
	static boolean[][] check;
	static Queue<int[]> q;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken()); // x
		n = Integer.parseInt(st.nextToken()); // y
		map = new int[n][m];
		check = new boolean[n][m];
		q = new LinkedList<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) {
					status++;
				}else if(map[i][j] == 1) {
					check[i][j] = true;
					q.add(new int[] {j,i,0});
				}
			}
		}
		System.out.println(bfs());
	}
	
	static int bfs() {
		int res =0;
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i=0; i<size; i++) {
				int[] pos = q.poll();
				for(int d=0; d<4; d++) {
					int nx = pos[0] + dx[d];
					int ny = pos[1] + dy[d];
					
					if(nx <0 || nx > m-1 || ny<0 || ny > n-1)continue;
					if(!check[ny][nx] && map[ny][nx] ==0) {
						check[ny][nx] = true;
						map[ny][nx] = 1;
						status--;
						q.add(new int[] {nx,ny, pos[2]+1});
					}
					
				}
			}
			
			if(q.isEmpty() && status==0) {
				return res;
			}
		}
		return -1;
	}
}
