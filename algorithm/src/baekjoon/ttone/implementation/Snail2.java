package baekjoon.ttone.implementation;

// #1952 implementation 달팽이2  
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Snail2 {

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int x=0, y=0, d=0, cnt=0;;
		int[][] map = new int[m][n];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0});
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0], py = p[1];
			
			if(map[py][px] == 1)break;
			map[py][px] = 1;
//			print(n,m,map);
			int nx = px + dx[d];
			int ny = py + dy[d];
			if(nx <0 || ny<0 || nx>n-1 || ny>m-1 || map[ny][nx]==1) {
				d++;
				if(d==4) d=0;
				cnt++;
				q.add(new int[] {px + dx[d],py + dy[d]});
			}else {
				q.add(new int[] {nx,ny});	
			}
		}
		System.out.println(cnt-1);
		
	}
	
	static void print(int n, int m, int[][] map) {
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}
