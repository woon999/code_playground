package baekjoon.ttone.graph;

// #1199 graph 오일러 회로 
import java.io.*;
import java.util.*;

public class EulerCircuit {

	static int n;
	static int[][] arr;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			int vertex = 0;
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				vertex += arr[i][j];
			}
			if(vertex%2!=0) {
				System.out.println(-1);
				return;
			}
		}
		
		sb = new StringBuilder();
//		print();
		getEulerCircuit(0);
//		print();
		System.out.println(sb.toString());
	}
	
	static void getEulerCircuit(int cur) {
		for(int nxt=0; nxt<n; nxt++) {
			while(arr[cur][nxt] > 0) {
				System.out.println(cur + " - " + nxt);
				arr[cur][nxt]--;
				arr[nxt][cur]--;
				getEulerCircuit(nxt);
			}
		}
		sb.append((cur+1)+" ");
	}
	
	static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}


