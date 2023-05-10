package baekjoon.ttone.graph;

// #1613 graph 역사 - 플로이드 와샬 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class History {

	static int n, a, b;
	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int kk = Integer.parseInt(st.nextToken());
	
		int[][] map = new int[n+1][n+1];
		for(int i=0; i<kk; i++) {
			input();
			map[a][b] = -1;
			map[b][a] = 1;
		}
		
		for(int k=1; k<n+1; k++) {
			for(int i=1; i<n+1; i++) {
				for(int j=1; j<n+1; j++) {
					if(map[i][k] == 1 && map[k][j] == 1) {
						map[i][j] = 1;
					} else if(map[i][k] == -1 && map[k][j] == -1) {
						map[i][j] = -1;
					}
				}
			}
		}
		
		int s = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<s; i++) {
			input();
			sb.append(map[a][b]+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
	}
}
