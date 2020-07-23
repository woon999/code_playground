package baekjoon.shortestpath;

// #9370
import java.io.*;
import java.util.*;

public class UncertainDestination {

	static int INF = 1000000;
	static int n;
	static int[][] map;
	static int[] dis;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());

		for (int i = 0; i < test; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			map = new int[n + 1][n + 1];
			dis = new int[n + 1];

			for (int j = 0; j < n + 1; j++) {
				Arrays.fill(map[j], INF);
			}
			Arrays.fill(dis, INF);
			
			check = new boolean[n+1];
			
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			
			for(int j=0; j<m;j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				map[a][b] = map[b][a] =d *2;
			}
			
			map[g][h] = map[h][g] = map[g][h]-1;
			
			ArrayList<Integer> list = new ArrayList<>();
			for(int j=0; j<t; j++) {
				list.add(Integer.parseInt(br.readLine()));
			}
			
			Collections.sort(list);
			dijkstra(start);
			
//			for(int j=0; j<dis.length; j++) {
//				System.out.print(dis[j]+" ");
//			}
//			System.out.println();
			
			for(int n : list) {
				if(dis[n] %2 ==1) sb.append(n +" ");
			}
			sb.append("\n");
			
		}
		System.out.println(sb);
	}
	
	static void dijkstra(int start) {
		Queue<Node3> q = new PriorityQueue<Node3>();
		q.add(new Node3(start,0));
		dis[start]=0;
		
		while(!q.isEmpty()) {
			Node3 pos = q.poll();
			
			if(check[pos.end]) continue;
			check[pos.end] = true;
			
			for(int i=1; i<n+1; i++) {
				if(!check[i] && dis[i] > dis[pos.end] + map[pos.end][i]) {
					dis[i] = dis[pos.end] + map[pos.end][i];
					q.add(new Node3(i, dis[i]));
				}
				
			}
		}
	}
}

class Node3 implements Comparable<Node3> {
	int end;
	int weight;

	Node3(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node3 o) {
		return weight - o.weight;
	}
}