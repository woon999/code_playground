package baekjoon.ttone.graph;

// #11780 graph 플로이드 2 
import java.io.*;
import java.util.*;

public class Floyd2 {

	static int n, INF = 100_001;
	static int[][] map;
	static List<Integer>[][] list;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		map = new int[n+1][n+1];
		list = new ArrayList[n+1][n+1];
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				list[i][j] = new ArrayList<>();
				if(i!=j) {
					map[i][j] = INF;
				}
			}
		}
		
		StringTokenizer st;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			map[a][b] = Math.min(map[a][b],w);
		}
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(map[i][j] > map[i][k]+map[k][j]) {
						map[i][j] = map[i][k]+map[k][j];
						trackingRoute(i, k, j);
					}
				}
			}
		}
		
		printMap();
		printRoute();
		System.out.println(sb.toString());
		
	}

	private static void trackingRoute(int i, int k, int j) {
		list[i][j].clear();
		for(int route : list[i][k]) {
			list[i][j].add(route);
		}
		list[i][j].add(k);
		for(int route : list[k][j]) {
			list[i][j].add(route);
		}
	}
	
	static void printMap() {
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(map[i][j] == INF) {
					sb.append(0+" ");
					continue;
				}
				sb.append(map[i][j]+" ");
			}
			sb.append("\n");
		}
	}
	
	static void printRoute() {
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				int size = list[i][j].size();
				if(i==j || map[i][j] == INF) {
					sb.append(0+"\n");
					continue;
					
				}
				sb.append((size+2)+" "+ i+" ");
				for(int num : list[i][j]) {
					sb.append(num+" ");
				}
				sb.append(j+"\n");
			}
		}
	}
}
