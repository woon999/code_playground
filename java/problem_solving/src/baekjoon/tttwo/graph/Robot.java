package baekjoon.tttwo.graph;

// #1726 graph 로봇 
import java.io.*;
import java.util.*;

public class Robot {
	static class Node{
		int x;
		int y;
		int d;
		int cnt;
		
		public Node(int x, int y, int d, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + d;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (d != other.d)
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
		
	}
	static BufferedReader br;
	static StringTokenizer st;
	static int n,m;
	static int[][] map;
	static Node start, end;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		inputData();
		bfs();
	}
	
	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] check = new boolean[n][m][4];
		q.add(start);
		check[start.y][start.x][start.d] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int cy = cur.y;
			int cx = cur.x;
			int cd = cur.d;
			int cnt = cur.cnt;
			
			if(isEnd(cur)) {
				System.out.println(cnt);
				return;
			}
			
			// 현재 방향으로 앞으로 3칸
			for(int f=1; f<=3; f++) {
				int nx = cx+dx[cd]*f;
				int ny = cy+dy[cd]*f;
				
				if(nx < 0 || ny < 0 || nx > m-1 || ny > n-1) continue;
				if(check[ny][nx][cd] ) continue;
				if(map[ny][nx] == 0) {
					check[ny][nx][cd] = true;
					q.add(new Node(nx,ny,cd, cnt+1));	
				}else break;
				
			}
			
			// 방향 변환 
			for(int d=0; d<4; d++) {
				if(d == cd || check[cy][cx][d]) continue;
				
				check[cy][cx][d] = true;
				// 동 서 남 북 (0 1 2 3)
				if(isReverse(cd, d)) {
					q.add(new Node(cx,cy,d, cnt+2));
				}else {
					q.add(new Node(cx,cy,d, cnt+1));
				}
			}
		}
	}
	
	static boolean isReverse(int cd, int nd) {
		if((cd == 0 && nd ==1) || (cd ==1 && nd ==0)
				|| ((cd ==2 && nd ==3)) || (cd ==3 && nd ==2)) return true;
		return false;
	}
	
	static boolean isEnd(Node cur) {
		return end.equals(cur);
	}
	
	static void inputData() throws IOException {
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken())-1;
		int x = Integer.parseInt(st.nextToken())-1;
		int d = Integer.parseInt(st.nextToken())-1;
		start = new Node(x,y,d,0);
		
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken())-1;
		x = Integer.parseInt(st.nextToken())-1;
		d = Integer.parseInt(st.nextToken())-1;
		end = new Node(x,y,d,0);
		
	}
}
