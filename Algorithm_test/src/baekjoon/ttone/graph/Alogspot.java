package baekjoon.ttone.graph;

// #1261 graph 알고스팟 (bfs, dijkstra) 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Alogspot {
	
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int wall;
		
		public Node(int x, int y, int wall) {
			this.x = x;
			this.y = y;
			this.wall = wall;
		}

		@Override 
		public int compareTo(Node o) {
			// 오름차순 
			return this.wall - o.wall;
		}
	}
	

	static int n,m;
	static int[][] map;
	static int res = Integer.MAX_VALUE;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1 ,1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[m][n];
		
		for(int i=0; i<m; i++) {
			String[] line = br.readLine().split("");
			for(int j=0; j<n; j++) {
				int num = Integer.parseInt(line[j]);
				map[i][j] = num;
			}
		}
		
		System.out.println(bfs(0,0));
	}
	
	
	
	static int bfs(int x, int y) {
		Queue<Node> q = new PriorityQueue<>();
		boolean[][] check = new boolean[m][n];
		
		check[y][x] = true;
		q.add(new Node(x,y,0));
		
		while(!q.isEmpty()) {
			Node pos = q.poll();
			
			int px = pos.x;
			int py = pos.y;
			int pwall = pos.wall;
//			System.out.println(px+","+py + " ->" + pwall);
			if(px == n-1 && py == m-1) {
//				System.out.println(pwall);
				return pwall;
			}
			
			for(int i=0; i<4 ; i++) {
				int nx = px+dx[i];
				int ny = py+dy[i];
				
				if(nx <0 || ny <0 || nx>n-1 || ny >m-1) continue;
				
				if(check[ny][nx]) continue;
				
				check[ny][nx] =true;
				if(map[ny][nx]==0) {
					q.add(new Node(nx,ny, pwall));
				}else {
					q.add(new Node(nx,ny, pwall+1));
				}
			}
			
		}
		return 0;
		
	}
}
