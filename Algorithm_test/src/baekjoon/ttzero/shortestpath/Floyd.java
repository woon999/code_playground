package baekjoon.ttzero.shortestpath;

// #11404
import java.io.*;
import java.util.*;

public class Floyd {

	static int INF = 1000000000;
	static int n,m;
	static int[][] dis;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		dis = new int[n+1][n+1];
		StringTokenizer st;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(i==j) continue;
				dis[i][j] = INF;
			}
		}
		
		m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			dis[u][v] = Math.min(dis[u][v], cost);
		}
		
		floyd();
//		for(int i=1; i<=n; i++) {
//			for(int j=1; j<=n; j++) {
//				if(dis[i][j]>=INF)
//					System.out.print("0 ");
//				else
//					System.out.print(dis[i][j]+ " ");
//			}System.out.println();
//		}
		show();
	}
	
	
	static void floyd() {
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					dis[i][j] =Math.min(dis[i][j], dis[i][k] + dis[k][j]);
				}
			}
		}
	}
	
	static void show() {
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(dis[i][j] >= INF) sb.append("0 ");
				else sb.append(dis[i][j] +" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
//		sb를 호출하면 틀림
//		https://linuxism.ustd.ip.or.kr/834
	}
}
