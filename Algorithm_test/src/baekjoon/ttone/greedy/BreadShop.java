package baekjoon.ttone.greedy;

// #3109 greedy 빵집 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BreadShop {

	static int r,c,cnt=0;
	static char[][] map;
	static int[] dr= {-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map =new char[r][c];
		
		for(int i=0; i<r; i++) {
			String line = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i=0; i<r; i++) {
			dfs(0,i);
		}
		
		System.out.println(cnt);
	}
	
	static boolean dfs(int x, int y) {
	
		for(int i=0; i<3; i++) {
			int nx = x + 1;
			int ny = y + dr[i];
			
			if(nx<0 || nx>c-1 || ny<0 || ny>r-1) continue;
			if(map[ny][nx] =='.') {
				if(nx == c-1) {
					cnt ++;
					return true;
				}
				
				map[ny][nx] = '-';
				if(dfs(nx,ny)) return true;
			}
			
		}
		return false;	
	}
}
