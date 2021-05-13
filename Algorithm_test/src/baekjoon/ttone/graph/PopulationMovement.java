package baekjoon.ttone.graph;

// #16234 bfs/dfs 인구이동 - 1차 풀이   
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class PopulationMovement {
	
	
	static int n,l,r;
	static int[][] map;
	static boolean[] check;
	static List<Integer>[] union;
	static int cnt, sum;
	static boolean flag;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());		
		
		map = new int[n][n];
		
		
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
		while(true) {
			flag = false;
			
			// 1. 국경선 열기
			union = new ArrayList[n*n];
			for(int i=0; i<n*n; i++) {
				union[i] = new ArrayList<>();
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					// i,j 나라 탐색 
					openDoor(i,j);
					
				}
			}
			
			// 연합 존재하지 않으면 이동 종료 
			if(!flag) break;
			System.out.println(move_num+ " 시작 ======================");
			
			// 2. 존재하는 연합 탐색 및 인구 이동  
			move_num ++;
			check = new boolean[n*n];
			List<Integer> visit = new ArrayList<>();
			for(int a=0; a<n*n; a++) {
				
				if(union[a].size() >0 && !check[a]) {
					cnt =0; sum =0;
//					System.out.println(a + " : 연합 탐색 시작");
					// 연합 탐색 시작 
					unionSearch(a);
//					System.out.println("총 인원 : " + sum +", 연합국 갯수 : " +cnt);
					
					//인구 이동 수 계산 
					int re_population = sum/cnt;
					
//					System.out.println("해당 연합 인구 이동 " +re_population);
					for(int nara=0; nara<n*n; nara++) {
						if(check[nara] && !visit.contains(nara)) {
							visit.add(nara); // 이미 방문 싸이클 체크 
//							System.out.println(nara + " 이동완료");
							int x = nara/n;
							int y = nara%n;
							map[x][y] = re_population;
						}
					}
//					System.out.println(a + " : 연합 탐색 및 인구이동 끝");
					
				}
			}
			
//			 이동 확인 
//			for(int[] num : map) {
//				System.out.println(Arrays.toString(num));
//			}
//			System.out.println();
		}
		
		// 결과 출력 
		System.out.println("총 이동횟수 : " + move_num);
		
	}
	
	static void unionSearch(int start) {
		
		int x = start/n;
		int y = start%n;
	
		cnt+=1;
		sum += map[x][y];
		check[start]= true;
		
		for(int i=0; i<union[start].size(); i++) {
			int next = union[start].get(i);
			int nx = next/n;
			int ny = next%n;
			
			if(nx <0 || ny<0 || nx>n-1 || ny>n-1) continue;
			
			if(!check[next]) {
				unionSearch(next);
			}
			
		}
	}

	static void openDoor(int x, int y) {
		
		int a_name = n*x +y;
		int a_num = map[x][y];
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<0 || ny<0 || nx>n-1 || ny>n-1) continue;
			
			int b_name= n*nx +ny;
			int b_num = map[nx][ny];
			
			int dif = Math.abs(a_num-b_num);
			if(union[a_name].contains(b_name) || union[b_name].contains(a_name)) continue;
				
			// a_name, b_name 국경 개방 
			if(l <= dif && dif <=r) {
				flag = true;
//				System.out.println(a_name +" ," + b_name+ " 국경 오픈 ");
				union[a_name].add(b_name);
				union[b_name].add(a_name);
			}
			
		}
	}

}


