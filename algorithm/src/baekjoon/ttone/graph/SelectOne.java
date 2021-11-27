package baekjoon.ttone.graph;

// #2660 graph 회장 뽑기 - 플로이드 와샬 
import java.io.*;
import java.util.*;

public class SelectOne {

	static final int INF = 987654321; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] map = new int[n+1][n+1];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i!=j) map[i][j] = INF;
			}
		}
		
		String line;
		while(!(line=br.readLine()).equals("-1 -1")) {
			StringTokenizer st = new StringTokenizer(line);
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			map[a][b] =1;
			map[b][a] =1;
			
		}
		
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(map[i][k] + map[k][j] < map[i][j]){
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		int min = INF;
		int[] answer = new int[n];
		for(int i=0; i<n; i++) {
			int status =0;
			for(int j=0; j<n; j++) {
				status = Math.max(status, map[i][j]);
			}
			answer[i] = status;
			min = Math.min(min, status);
		}
		
		
		int cnt =0;
		StringBuilder sb= new StringBuilder();
		for(int i=0; i<n; i++) {
			if(answer[i] == min) {
				cnt++;
				sb.append((i+1) +" ");
			}
		}
		
		System.out.println(min +" " + cnt);
		System.out.println(sb.toString());
		
	}
}
