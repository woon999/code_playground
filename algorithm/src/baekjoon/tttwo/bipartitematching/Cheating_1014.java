package baekjoon.tttwo.bipartitematching;

// #1014 bipartitematching 컨닝 
import java.io.*;
import java.util.*;

public class Cheating_1014 {

	static int n, m;
	// default input 
	static char[][] room;
	static int[][] seatNum;
	static int[][] map;
	
	// bipartite 
	static int[] dp;
	static boolean[] check;
	// cheat move
	static int[] cheatx = {-1, -1, -1, 1, 1, 1};
	static int[] cheaty = {0, -1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			room = new char[n][m];
			seatNum = new int[n][m];
			map = new int[n*m+1][n*m+1];
			for(int i=0; i<n; i++) {
				String[] line = br.readLine().split("");
				for(int j=0; j<m; j++) {
					room[i][j] = line[j].charAt(0);
					seatNum[i][j] = i*m+j+1;
				}
			}
			
			int result = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(room[i][j] == '.') {
						result ++;
						for(int d=0; d<6; d++) {
							int nx = j + cheatx[d];
							int ny = i + cheaty[d];
							
							if(nx < 0 || ny < 0 || nx > m-1 || ny > n-1 || room[ny][nx] == 'x') continue;
							// (i,j) -> (ny, nx)  can cheat
							map[seatNum[i][j]][seatNum[ny][nx]] = 1;
						}
					}
				}
			}
			
//			print();
			
			// 이분 매칭 
			int cnt = 0;
			check = new boolean[n*m+1];
			dp = new int[n*m+1];
			// 1~n*m 좌석 차례대로 매칭 
			for(int i=1; i<=n*m; i++) {
				Arrays.fill(check, false);
				if(dfs(i)) cnt++; 
			}
//			System.out.println("total : " + result +", can cheat:" + cnt/2);
			
			// 전체 좌석 수 - cheat 가능한 좌석수(양방향이므로 나누기 2) 
			sb.append(result-(cnt/2)+"\n");
		}
		System.out.println(sb.toString());
		
	}
	
	static boolean dfs(int here) {
		for(int i=1; i<=n*m; i++) {
			int nxt = i;
			if(map[here][nxt] == 1) {
				if(!check[nxt]) {
					check[nxt] = true;
					// 비어있거나 점유 노드에 더 들어갈 공간이 있는 경우 
					if(dp[nxt] == 0 || dfs(dp[nxt])) {
						dp[nxt] = here;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(room[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("-------");
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(seatNum[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("------");
		for(int i=1; i<=n*m; i++	) {
			for(int j=1; j<=n*m; j++	) {
				if(map[i][j] == 1) {
					System.out.println(i +" -> " + j +" ::" + map[i][j]);
				}
			}
			
		}
	}
}
