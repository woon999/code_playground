package baekjoon.ttone.dataStructure;

// #3190 queue 뱀 
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sneak {

	static int n, d=0;
	static int[][] map;
	static Map<Integer,String> moveInfo;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	
		n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		moveInfo = new HashMap<>();
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			map[a][b] = 1;
		}
		
		
		int l = Integer.parseInt(br.readLine());
		
		for(int i=0; i<l; i++) {
			st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken());
			String direction = st.nextToken();
			moveInfo.put(time, direction);
		}
		
		solve();
		
		
	}
	
	static void solve() {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(0);
		int time =0;
		int px =0;
		int py =0;
		
		while(true) {
			time++;
			
			int nx = px + dx[d];
			int ny = py + dy[d];
			System.out.println(ny+","+ nx + ", 몸통: "+q.size() +" ,  방향:" + d +":"+ dx[d]+","+dy[d]+ " :::"  + time);
			// 벽 부딪히면 종료 
			if(nx<0 || ny<0 || nx>n-1|| ny> n-1) {
				System.out.println("벽 부딪힘");
				break;
			}
			if(q.contains(ny*n +nx)){
				System.out.println("몸통 부딪힘 ");
				break;
			}
			
			if(map[ny][nx] ==1) {
				System.out.println("사과 발견 ");
				map[ny][nx] = 0;
				q.add(ny*n +nx);
			}else {
				q.add(ny*n +nx);
				q.poll();
			}

			if(moveInfo.containsKey(time)) {
				String data = moveInfo.get(time);
				if(data.equals("D")) {
					d +=1;
					if(d==4)  d=0;
				}else {
					d -=1;
					if(d==-1) d=3;
				}
				System.out.println("방향전환 :" +  d );
			}
			
			px = nx;
			py = ny;
		
		}
		
		System.out.println(time);
		
	}
}
