package baekjoon.ttone.graph;

// #2636 graph 치즈 
import java.io.*;
import java.util.*;

public class Cheese {
	
	static int x,y,cheeseNum;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		map = new int[y][x];
		for(int i=0; i<y; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<x; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) cheeseNum++;
				map[i][j] = num;
			}
		}

		int res=0;
		int cnt=0;
		while(cheeseNum>0) {
			if(cheeseNum>0) {
				res = cheeseNum;
			}
			cnt += melt();
			if(cheeseNum ==0)break;
		}
		System.out.println(cnt);
		System.out.println(res);
		
	}
	
	static int melt() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] check = new boolean[y][x];
		
		q.add(new int[] {0,0});
		check[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0]; int py = pos[1];
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx<0 || ny <0 || nx > x-1 || ny > y-1 || check[ny][nx]) continue;
				if(map[ny][nx] != -1) {
					check[ny][nx] = true;
					
					if(map[ny][nx] == 1) {
						map[ny][nx] -= 1;
						cheeseNum--;
						continue;
					}
					q.add(new int[] {nx,ny});
				}
			}
		}
		return 1;
	}

}
