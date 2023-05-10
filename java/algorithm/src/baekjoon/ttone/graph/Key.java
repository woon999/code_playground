package baekjoon.ttone.graph;

// #9328 graph 열쇠 - BFS, 비트마스킹 
import java.io.*;
import java.util.*;

public class Key {

	static int r,c, answer;
	static int keyBit;
	static char[][] map;
	static boolean[][] check;
	static Map<Integer, Queue<int[]>> doorList;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1 ,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0; t<tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			List<int[]> entrance = new ArrayList<>();
			map = new char[r][c];
			keyBit=0;
			answer =0;
			doorList = new HashMap<>();
			for(int i=0; i<r; i++) {
				String line = br.readLine();
				for(int j=0; j<c; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] != '*') {
						if(i==0 || i==r-1 || j==0 || j == c-1) {
							if(map[i][j] == '$') {
								answer++;
								map[i][j] ='.';
							}else if('a'<=map[i][j] && map[i][j] <= 'z') {
								keyBit |= 1<<(map[i][j]-'a');
							}
							entrance.add(new int[] {j,i});
						}
					}
				}
			}
			
			char[] keyList = br.readLine().toCharArray();
			for(char key : keyList) {
				if(key=='0') break;
				keyBit |= (1<<(key-'a'));
			}
			check = new boolean[r][c];
			for(int[] pos : entrance) {
				int x = pos[0], y = pos[1];
				if('A' <= map[y][x] && map[y][x] <='Z') {
					int door = 1<<(map[y][x] - 65);
					if((keyBit & door) != door) {
						putDoorQueue(x,y,door);
						continue;
					}
				}
				bfs(x,y);
			}
			System.out.println(answer);
		}
		
		
	}
	
	static void bfs(int x, int y){
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		check[y][x] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int px= p[0];
			int py= p[1];
			
			for(int i=0; i<4; i++) {
				int nx  = px + dx[i];
				int ny  = py + dy[i];
				
				if(nx <0 || ny <0|| nx>c-1 || ny > r-1 || 
						check[ny][nx] || map[ny][nx]=='*') continue;
				
				if(map[ny][nx]=='$') {
					answer++;
				} else if('a' <= map[ny][nx] && map[ny][nx] <= 'z') {
					keyBit |= 1<<(map[ny][nx]-'a');
					for(int door : doorList.keySet()) {
						if((keyBit&door)==door) {
							watingQueueExtract(q, door);
						}
					}
				} else if('A' <= map[ny][nx] && map[ny][nx] <='Z') {
					int door = 1<<(map[ny][nx] - 65);
					if((keyBit & door) != door) {
						putDoorQueue(nx, ny, door);
						continue;
					}
				} 
				check[ny][nx] = true;
				q.add(new int[] {nx,ny});
			}
				
		}
		
	}

	private static void watingQueueExtract(Queue<int[]> q, int door) {
		Queue<int[]> waitQueue = doorList.get(door);
		while(!waitQueue.isEmpty()) {
			int[] wq= waitQueue.poll();
			q.add(new int[] {wq[0],wq[1]});
		}
	}

	private static void putDoorQueue(int x, int y, int door) {
		if(doorList.containsKey(door)) {
			doorList.get(door).add(new int[] {x,y});
		}else {
			Queue<int[]> dq = new LinkedList<>();
			dq.add(new int[] {x,y});
			doorList.put(door, dq);
		}
	}
	
	static void print()	{
		System.out.println("----");
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}



