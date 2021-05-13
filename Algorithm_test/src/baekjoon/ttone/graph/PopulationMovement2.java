package baekjoon.ttone.graph;

// #16234 bfs/dfs 인구이동 - 최종    
import java.io.*;
import java.util.*;

public class PopulationMovement2 {
	
	static class Union{
		int x;
		int y;
		
		public Union(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n,l,r;
	static int[][] map;
	static int[] check;
	static Queue<Union> q;
	static int cnt, sum;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());		
		
		map = new int[n][n];
		check = new int[n*n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int[] num : map) {
//			System.out.println(Arrays.toString(num));
//		}


		solve();
		
		
	}
	
	static void solve() {
		
		// 열 수있는 국경이 존재할 때 까지 반복 
		int move_num =0;
		
			
		// 1. 국경선 열기
		boolean flag = true;
			
		while(flag) {
			flag = false;
			System.out.println(move_num +"시작 =============");
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					cnt =0; sum=0;
					// i,j 나라 탐색 
					if(check[n*i +j] ==0) {
						if(condition(i,j)) { //인접나라에 국경 열 곳이 있는지 확인
							openDoor(i,j);
								
							// 인구 이동 수 계산 
							int re_population = sum/cnt;
							
							// 인구 이동 
							populationMove(i,j, re_population);
							flag = true;
						}
					}
				}
			}
			// 이동 확인 
			for(int[] num : map) {
				System.out.println(Arrays.toString(num));
			}
			System.out.println();
			if(flag) move_num++;
			else break;
			check = new int[n*n];
		}
		
		// 결과 출력 
		System.out.println("총 이동횟수 : " + move_num);
		
	}
	static boolean condition(int i, int j) {
		for(int k = 0; k < 4; k++) {
			int nx = i + dy[k];
			int ny = j + dx[k];
			if(nx<0 || ny<0 || nx>n-1 || ny>n-1) continue;
			int dif = Math.abs(map[nx][ny] - map[i][j]);
			if(l <= dif && dif <=r) {
				return true;
			}
		}
		return false;
	}
	
	static void populationMove(int x, int y, int moveNum) {
		int a_city = n*x +y;
		q = new LinkedList<>();
		q.add(new Union(x,y));
		
		check[a_city] = -1; 
		map[x][y] = moveNum;
		System.out.println("이동 인구 수 : " + moveNum);
		
		while(!q.isEmpty()) {
			Union pos = q.poll();
			int px = pos.x;
			int py = pos.y;
			for(int i=0; i<4; i++) {
				int nx = px+dx[i];
				int ny = py+dy[i];
				
				if(nx<0 || ny<0 || nx>n-1 || ny>n-1) continue;
				int b_city = n*nx + ny;
				if(check[b_city] !=1) continue;
				
				check[b_city] = -1;
				map[nx][ny] = moveNum;
				q.add(new Union(nx,ny));
			}
				
		}

	}
	

	static void openDoor(int x, int y) {
		int a_city = n*x +y;
		q= new LinkedList<>();
		q.add(new Union(x,y));
		
		check[a_city] = 1;
		sum = map[x][y];
		cnt=1;
		
		while(!q.isEmpty()) {
			Union pos = q.poll();
			int px = pos.x;
			int py = pos.y;
		
			int a_pop = map[px][py];
			for(int i=0; i<4; i++) {
				int nx = px+dx[i];
				int ny = py+dy[i];
				
				if(nx<0 || ny<0 || nx>n-1 || ny>n-1) continue;
				int b_city = n*nx + ny;
				int b_pop = map[nx][ny];
				
				if(check[b_city] !=0) continue;				
				int dif = Math.abs(a_pop-b_pop);
				
				// a_name, b_name 국경 개방 
				if(l <= dif && dif <=r) {
//					System.out.println(a_city +" ," + b_city+ " 국경 오픈 ");
					check[b_city] =1;
					q.add(new Union(nx,ny));
					cnt++;
					sum += b_pop;
				}
				
			}
		}
		
	}

}


