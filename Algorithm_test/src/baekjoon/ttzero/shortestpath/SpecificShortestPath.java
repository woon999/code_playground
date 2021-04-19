package baekjoon.ttzero.shortestpath;

// #1504
import java.io.*;
import java.util.*;

public class SpecificShortestPath {
	
	static final int INF = 100000000;
//  INF값을 Integer.MAX_VALUE으로 설정하면 틀렸다고 뜸 
//	혹시나 Integer.MAX_VALUE +1을 갖게 되면 범위값 초과 . 그러므로 임의의 큰 값을 설정 

	static int n, e;
	static int[][] map = new int[801][801];
	static int[][] path = new int[3][801];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());


        for (int i = 0; i < 3; i++) {

            Arrays.fill(path[i], INF);
        }

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());

			map[start][end] = dis;
			map[end][start] = dis;
		}

		st = new StringTokenizer(br.readLine());
		int p1 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());

		dijkstra(1, 0);
		dijkstra(p1, 1);
		dijkstra(p2, 2);
		for(int i=0; i <3; i++) {
			for(int j=0; j<e+1; j++) {
				System.out.print(path[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-----------------------");

		int minCost = Math.min(path[0][p1] + path[1][p2] + path[2][n],
				path[0][p2] + path[2][p1] + path[1][n]);

		 
		System.out.println(minCost >= INF ? -1 : minCost);
		 
	}

	static void dijkstra(int start, int idx) {
		PriorityQueue<Node2> q = new PriorityQueue<Node2>();

		q.add(new Node2(start, 0));
		path[idx][start] = 0;

		while (!q.isEmpty()) {
			Node2 pos = q.poll();
			
			if (path[idx][pos.node] < pos.dis)
				continue;

			for (int i = 1; i < n + 1; i++) {
				if (map[pos.node][i] != 0) {
					if (path[idx][i] > path[idx][pos.node] + map[pos.node][i]) {
						path[idx][i] = path[idx][pos.node] + map[pos.node][i];
						q.add(new Node2(i, path[idx][i]));
					}
				}
			}
		}

	}
}

class Node2 implements Comparable<Node2> {
	int node;
	int dis;

	Node2(int node, int dis) {
		this.node = node;
		this.dis = dis;
	}

	@Override
	public int compareTo(Node2 o) {
		return this.dis < o.dis ? -1 : 1;
	}

}
