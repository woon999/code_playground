package baekjoon.ttone.graph;

//  #5427 graph 불 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fire {
	static int w,h;
	static char[][] map;
	static Queue<int[]> person;
	static Queue<int[]> fire;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(tc-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			map = new char[h][w];
			fire = new LinkedList<>();
			person = new LinkedList<>();
			for(int i=0; i<h; i++) {
				String line = br.readLine();
				for(int j=0; j<w; j++) {
					char c = line.charAt(j);
					if(c=='@') {
						person.add(new int[] {j,i,0});
					}else if(c=='*') {
						fire.add(new int[] {j,i});
					}
					map[i][j] = c;
				}
			}
			int res= -1;
			out : while(true) {
				// 1. 불 번짐
				int fSize = fire.size();
				for(int i=0; i<fSize; i++) {
					int[] pos = fire.poll();
					int px = pos[0], py = pos[1];
					fireMarking(px, py);
				}
				
				// 2. 상근 이동 (1번 불번짐 영향 x, 그러나 번질 곳으로 이동은 x)
				
				int pSize = person.size();
				for(int i=0; i<pSize; i++) {
					int[] pos = person.poll();
					int px = pos[0], py = pos[1], time =pos[2];
					res = escape(px, py, time);
					if(res != -1) break out;
					
				}
//				print();
//				System.out.println(person.isEmpty());
				if(person.isEmpty()) break;
			}
			
			if(res !=-1) sb.append(res+"\n");
			else sb.append("IMPOSSIBLE\n");
		}
		System.out.println(sb.toString());
	
	}
	
	static int escape(int x, int y, int time) {
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>w-1 || ny<0 || ny>h-1) {
				return time+1;
			}
			
			if(map[ny][nx] == '.') {
				map[ny][nx] = '@';
				person.add(new int[] {nx, ny, time+1});
			}
		}
		return -1;
		
	}
	
	static void fireMarking(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<0 || nx>w-1 || ny<0 || ny>h-1) continue;
			
			// 빈공간 , 상근 위치 
			if(map[ny][nx] == '.' || map[ny][nx] == '@') {
				map[ny][nx] = '*';
				fire.add(new int[] {nx,ny});
			}
		}
	}
	

	static void print() {
		System.out.println("------------");
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}


