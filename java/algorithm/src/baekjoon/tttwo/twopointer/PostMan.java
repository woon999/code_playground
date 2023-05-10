package baekjoon.tttwo.twopointer;

// #2842 twopointer 집배원 한상덕 
import java.io.*;
import java.util.*;

public class PostMan {

	static int n;
	static char[][] map;
	static int[][] mapNum;
	static int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
	static int[] dy = {0, 0, -1, 1, 1, -1, 1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		int sx=0, sy=0, target=0;
		map = new char[n][n];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<n; j++) {
				if(map[i][j] == 'P') {
					sx = j;
					sy = i;
				}else if(map[i][j] == 'K') {
					target++;
				}
			}
			
		}
		
		int max = -1, min = Integer.MAX_VALUE;
		List<Integer> height = new ArrayList<>();
		mapNum = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				mapNum[i][j] = Integer.parseInt(st.nextToken());
				height.add(mapNum[i][j]);
				if(map[i][j] == 'P' || map[i][j] =='K'){
					max = Math.max(max, mapNum[i][j]);
					min = Math.min(min, mapNum[i][j]);
				}
			}
		}
//		print();
		
		Collections.sort(height);
		int bottom = 0;
		int up = height.indexOf(max);
		int minIdx = height.indexOf(min);
		int maxIdx = height.size();
		int res = Integer.MAX_VALUE;
		while(bottom <= minIdx && bottom <= up && up < maxIdx) {
			int l = height.get(bottom);
			int r = height.get(up);
			if(bfs(sx, sy, l, r, target) == 0) {
				res = Math.min(res, (r-l));
				bottom++;
			}else {
				up++;
			}
		}
		System.out.println(res);
	}
	
	static int bfs(int x, int y, int bottom, int up, int target) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] check = new boolean[n][n];
		q.add(new int[]{x,y});
		check[y][x] = true;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0], py = p[1];
		
			if(target == 0) return target;
			
			for(int d=0; d<8; d++) {
				int nx = px+dx[d];
				int ny = py+dy[d];
				
				if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1) continue;
				if(check[ny][nx] || mapNum[ny][nx] < bottom || mapNum[ny][nx] > up) continue;
				
				check[ny][nx] = true;
				if(map[ny][nx] == 'K') {
					target--;
				}
				q.add(new int[] {nx,ny});
			}
		}
		return target;
	}
	
	static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(mapNum[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
