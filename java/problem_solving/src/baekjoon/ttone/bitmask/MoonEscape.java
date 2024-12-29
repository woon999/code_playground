package baekjoon.ttone.bitmask;

// #1194 bitmask 달이 차오른다, 가자. - BFS 
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class MoonEscape {

	static int n,m;
	static int[][] map;
	static Map<Integer, Integer> key;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int answer = -1;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		key = new HashMap<>();
		int start_x=0, start_y=0;
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			// '#': 0, '.': 11, '0':13, '1':14, 
			// 'a' 62 ~ 'f' 67
			// 'A' 30 ~ 'F' 35
			for(int j=0; j<m; j++) {
				int num = line.charAt(j)-35;
				if(num == 13) {
					start_x = j;
					start_y = i;
				}
				else if(num>61 && num <68) { // key 저장 
					if(!key.containsKey(num)) {
						key.put(num, 1<<(num-61));
					}
				}
				map[i][j] = num;
			}
		}
		
		bfs(start_x, start_y);
		System.out.println(answer);
	}
	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[n][m][1<<7];
		q.add(new int[] {x,y,0,0});
		visited[y][x][0] = true;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0];
			int py = pos[1];
			int status = pos[2];
			int move = pos[3];
			
//			System.out.println((move) + " search... (" + px +","+ py+"), status : " + Integer.toBinaryString(status));
			if(map[py][px] == 14) {
//				System.out.println(move + "end :" + px+"," + py);
				answer = move;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx <0 || ny<0 || nx>m-1 || ny>n-1 ) continue;
				if(map[ny][nx] == 0 || visited[ny][nx][status]) continue; 
				int nStatus = status;
				
				// key 획득 
				if(map[ny][nx]>61 && map[ny][nx] <68) {
					nStatus = status|key.get(map[ny][nx]);
					if(!visited[ny][nx][nStatus]) {
						visited[ny][nx][nStatus] = true;
//						System.out.println("key get : " +(char)(map[ny][nx]+35));
						q.add(new int[] {nx,ny,nStatus, move+1});
					}
				}
				// door 만남  
				else if(map[ny][nx]>29 &&map[ny][nx] <36) {
					// key, 문 일치 
					if(!key.containsKey(map[ny][nx]+32)) continue;
					if((status & key.get(map[ny][nx]+32)) == key.get(map[ny][nx]+32)) {
						visited[ny][nx][nStatus] = true;
//						System.out.println("open door : " +(char)(map[ny][nx]+35));
						q.add(new int[] {nx,ny,nStatus, move+1});
					}
				}
				// 그냥 길 
				else {
					visited[ny][nx][nStatus] = true;
					q.add(new int[] {nx,ny,nStatus,move+1});
				}
			}
			
		}
	}
}
