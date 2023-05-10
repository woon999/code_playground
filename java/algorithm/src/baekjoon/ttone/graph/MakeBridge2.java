package baekjoon.ttone.graph;

// #17472 graph 다리만들기2
import java.io.*;
import java.util.*;

public class MakeBridge2 {
	
	static class Node implements Comparable<Node>{
		int to;
		int from;
		int value;
		
		public Node(int to, int from, int value) {
			this.to = to;
			this.from = from;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return this.value - o.value;
		}
		
	}

	static int n,m, islandCnt;
	static int[][] map;
	static int[] parents;
	static Queue<int[]> q;
	static PriorityQueue<Node> pq = new PriorityQueue<>(); ;
	static boolean[][] check;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		islandCnt =2;
		check = new boolean[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]==1 && !check[i][j]) {
					islandIndexing(j, i, islandCnt);
					islandCnt++;
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]!=0) {
					makeBridge(j, i, map[i][j]);
				}
			}
		}
		
		
		islandCnt--;
		parents = new int[islandCnt];
		for(int i=1; i<islandCnt; i++) {
			parents[i] = i;
		} 
		int answer = shortestPath();
		System.out.println(answer == 0 ? -1 : answer);
		
	}
	
	// 1번 로직 (그래프 색칠) 
	static void islandIndexing(int x, int y, int idx) {
		q = new LinkedList<>();
		
		
		q.add(new int[] {x,y});
		map[y][x] = idx;
		check[y][x] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0];
			int py = p[1];
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx<0 || ny <0 || nx > m-1 || ny > n-1 || check[ny][nx]) continue;
				
				if(map[ny][nx]==1) {
					map[ny][nx] = idx;
					check[ny][nx] = true;
					q.add(new int[] {nx,ny});
				}
			}
		}
	}
	// 2번 로직 (그래프 연결) 
	static void makeBridge(int x, int y, int idx) {
		q = new LinkedList<>();	
		check = new boolean[n][m];
		for(int d=0; d<4; d++) {
			q.add(new int[] {x,y,0});
			check[y][x] = true;
			
			while(!q.isEmpty()) {
				int[] p = q.poll();
				int px = p[0];
				int py = p[1];
				int move = p[2];
				
				int nx = px + dx[d];
				int ny = py + dy[d];
				
				if(nx<0 || ny <0 || nx > m-1 || ny > n-1 || check[ny][nx]) continue;
				
				if(map[ny][nx]!=idx) {
					if(map[ny][nx] !=0) {
						int from = idx-1;
						int to = map[ny][nx]-1;
						int bridgeLen = move;
						if(bridgeLen>1) {		
							System.out.println((from+1) +" --- " + (to+1)  +" > 다리 길이 : " + bridgeLen);
							pq.add(new Node(from, to, bridgeLen));
							break;
						}
					}else {
						check[ny][nx] = true;
						q.add(new int[] {nx, ny, move+1});
					}
				}
			}
			q.clear();
		}
	}

	// 3번 로직 (최소 신장트리 -크루스칼) 
	static int shortestPath() {
		int sum =0;
		int size = pq.size();
		for(int i=0; i< size; i++) {
			Node node = pq.poll();
			int x = node.from;
			int y = node.to;
			
			if(find(x) != find(y)) {
				System.out.println(x+"," +y + " 연결 " + node.value);
				sum += node.value;
				union(x,y);
			}
		}
		
		int rx = parents[1];
		for(int i=2; i<islandCnt; i++) {
			if(rx != find(parents[i])) {
				System.out.println("연결 x ");
				return 0;
			}
		}
		
		return sum;
	}
	
	
	
	static int find(int x) {
		if(parents[x] == x) return x;
		int rx = find(parents[x]);
		return rx;
		
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x<y) {
			parents[y]=x;
		}
		else {
			parents[x] =y;
		}
	}
	
	
	
	
	
	
}

//6 5
//1 1 0 0 1
//1 1 0 0 1
//0 0 0 0 1
//0 0 0 0 1
//0 0 0 0 1
//1 1 1 1 1
// 2 

//5 6
//1 1 0 0 0 1
//1 1 0 0 0 1
//0 0 0 0 0 1
//0 0 0 0 0 1
//1 1 1 1 1 1
// 2

//8 8
//0 0 0 1 1 1 1 0
//0 1 1 1 1 0 1 0
//0 1 0 1 1 1 0 0
//0 1 0 0 0 1 0 0
//0 0 0 1 0 0 1 0
//0 0 0 0 0 1 0 0
//0 1 1 1 0 0 0 0
//0 1 0 0 0 1 0 0



