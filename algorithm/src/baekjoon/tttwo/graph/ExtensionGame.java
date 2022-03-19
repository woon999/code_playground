package baekjoon.tttwo.graph;

// #16920 graph 확장 게임 - bfs 

import java.io.*;
import java.util.*;

public class ExtensionGame {

	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n, m, s;
	static int[] dis, castles;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] map;
	static Queue<Node>[] q;
	public static void main(String[] args) throws  IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		castles = new int[s+1];
		q = new LinkedList[s+1];
		dis = new int[s+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=s; i++) {
			dis[i] = Integer.parseInt(st.nextToken());
			q[i] = new LinkedList<>();
		}
		
		map = new char[n][m];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if('1' <= map[i][j] && map[i][j] <= '9') {
					int userNo = map[i][j]-'0';
					q[userNo].add(new Node(j,i));
					castles[userNo]++;
				}
			}
		}
		
		bfs();
		for(int i=1; i<=s; i++) {
			System.out.print(castles[i]+" ");
		}
		System.out.println();
	}
	
	static void bfs() {
		while(true) {
			for(int u=1; u<=s; u++) {
				int s_dis = dis[u];
				for(int i=0; i<s_dis; i++) {
					int size = q[u].size();
					for(int j=0; j<size; j++) {
						Node cur = q[u].poll();
						int cx = cur.x;
						int cy = cur.y;
						for(int d=0; d<4; d++) {
							int nx = cx +dx[d];
							int ny = cy +dy[d];
							if(nx < 0 || ny < 0 || nx > m-1 || ny > n-1) continue;
							if(map[ny][nx] == '.') {
								map[ny][nx] = (char) (u+'0');
								q[u].add(new Node(nx, ny));
								castles[u]++;
							}
						}
					}
					if(q[u].isEmpty()) break;
				}
			}
			boolean f = true;
			for(int u=1; u<=s; u++) {
				if(!q[u].isEmpty()) {
					f = false;
					break;
				}
			}
			if(f) break;
		}		
	}
}
