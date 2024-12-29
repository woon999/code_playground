package baekjoon.ttone.graph;

// #16236 graph 아기상어 (BFS)  
import java.io.*;
import java.util.*;

public class BabyShark {

	static int n, pos_x, pos_y, time=0, size=2;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		

		StringTokenizer st = null;
		
		int start_x =0, start_y=0;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num == 9) { // 아기 상어 위치 
					start_x =i;
					start_y= j;
				}
						
			}
		}
		bfs(start_x, start_y);
		
		
		System.out.println(time);
		
	}
	
	static void bfs(int x, int y) {
		
		int eating =0;
		pos_x= x; pos_y= y;
		
		while(true) {
			Queue<Fish> q = new LinkedList<>();
			List<Fish> preyList = new ArrayList<>();
			check = new boolean[n][n];
			
//			System.out.println("("+pos_x +","+pos_y+")" + "시작");
			
			check[pos_x][pos_y] = true;
			q.add(new Fish(pos_x,pos_y,0));
			
			// 먹을 물고기 BFS 탐색 
			while(!q.isEmpty()) {
				Fish nxt = q.poll();
				int nMove = nxt.move;
				
				for(int i=0; i<4; i++) {
					int nx = nxt.x + dx[i];
					int ny = nxt.y + dy[i];
					
					if(nx <0 || ny <0 || nx>n-1 || ny>n-1) continue;
					if(check[nx][ny]) continue;
					check[nx][ny] = true;
						
					if(map[nx][ny] <= size) {
						if(map[nx][ny] !=0 && map[nx][ny] < size){
//							System.out.println("찾았다 : (" + nx +"," +ny+")");
							preyList.add(new Fish(nx,ny,nMove+1));
						}
						q.add(new Fish(nx, ny, nMove+1));							
					}
				}
			}
				
//			System.out.println("먹이 :" + preyList.size());
			// 먹이없으면 엄마 찾기 
			if(preyList.size()==0) {
//				System.out.println("mom!!");
				break;
			}else {
				// 가장 가까운 먹이 찾기 
				if(preyList.size()>1) {					
					preySort(preyList);
				}
				
				
				// 걸린 시간, 아기 상어 위치, 아기 상어 크기를 재설정 한 다음 위의 과정 다시 반복
				Fish eat = preyList.get(0);
				time += eat.move;
				eating++;
				
				// 아기 상어 위치 다시 세팅  
				map[pos_x][pos_y] =0;
				pos_x = eat.x;
				pos_y = eat.y;
				map[pos_x][pos_y] = 9;

				// 아기 상어 크기 == 먹은 물고기 수 -> 사이즈+1
				if(size == eating) {
					size ++;
					eating =0;
				}
				
//				System.out.println("냠냠 맛있다! ("+eat.x+","+eat.y+") , 걸린 시간 : " + time + ", 아기 상어 크기:"+size);
//			
//				for(int i=0; i<n; i++) {
//					for(int j=0; j<n; j++) {
//						System.out.print(map[i][j]+" ");
//					}
//					System.out.println();
//				}
				
//				System.out.println();
			}
		}

	}
	
	
	
	static void preySort(List<Fish> list) {
		Collections.sort(list, new Comparator<Fish>() {
			@Override
			public int compare(Fish o1, Fish o2) {
				// 1. 가장 가까운(move) 2. 가장 위 (x) , 3.가장 왼쪽(y)
				if(o1.move == o2.move) {
					if(o1.x == o2.x) {
						// 가장 왼쪽
						return o1.y-o2.y;
					}else {
						// 가장 위  
						return o1.x-o2.x;
					}
				}else {
					// 가장 가까운 곳 
					return o1.move- o2.move;
				}
			}
		});
	}
}

class Fish{
	int x;
	int y;
	int move;
	
	public Fish(int x, int y, int move) {
		this.x = x;
		this.y = y;
		this.move = move;
	}
}
