package baekjoon.ttone.graph;

// #2458 graph 키 순서 - 플로이드 와샬 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KeyOrder {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean[][] check = new boolean[n][n];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			check[a][b] = true;
		}
		
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(check[i][k] && check[k][j]) {
						check[i][j] = true;
					}
				}
			}
		}
		
		int[] cnt = new int[n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(check[i][j] || check[j][i]) {
					cnt[i] ++;
				}
			}
		}
		
		int res =0;
		for(int num : cnt) {
			if(num == n-1) res++;
		}
		System.out.println(res);
		
	}
}
