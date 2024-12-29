package baekjoon.ttzero.tree;

// #1167
import java.io.*;
import java.util.*;

public class DiameterOfTree {

	static List<Node>[] list;
	static int[] distance;
	static boolean[] check;
	static int start,max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		list = new ArrayList[n + 1];
	

		for (int i = 1; i < n + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());

			list[a] = new ArrayList<Node>();

			while (true) {
				int b = Integer.parseInt(st.nextToken());
				if (b == -1)
					break;

				int dis = Integer.parseInt(st.nextToken());
				list[a].add(new Node(b, dis));
			}
		}

		bfs(1,n);
//		for(int i=0; i< distance.length; i++) {
//			System.out.print(distance[i] +" ");
//		}
//		System.out.println();
		
		
		int s = 1;
		for(int i=2; i<n+1; i++) {
			if(distance[s] < distance[i]) {
				s=i;
				
			}
		}
		
//		제일 긴 정점을 구해서 다시 bfs를 안하면 틀리는 반례
//		6
//		5 4 6 -1
//		1 3 2 -1
//		2 4 2 6 4 -1
//		3 1 2 4 3 -1
//		4 2 2 3 3 5 6 -1
//		6 2 4 -1
//		System.out.println(s);
		bfs(s,n);
		
//		for(int i=0; i< distance.length; i++) {
//			System.out.print(distance[i] +" ");
//		}
//		System.out.println();
		
		Arrays.sort(distance);
		System.out.println(distance[n]);

	}

	static void bfs(int i, int n) {
		distance = new int[n + 1];
		check = new boolean[n + 1];
		
		Queue<Integer> q = new LinkedList<>();
		q.add(i);
		check[i] = true;

		while (!q.isEmpty()) {
			int num = q.poll();

			for (Node node : list[num]) {
				int nPos = node.end;
				int nDis = node.dis;

				if (!check[nPos]) {

					check[nPos] = true;
					q.add(nPos);
					distance[nPos] = nDis + distance[num];
				}

			}

		}
	}
}


class Node {
	int end;
	int dis;

	public Node(int end, int dis) {
		this.end = end;
		this.dis = dis;
	}
}
