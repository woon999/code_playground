package baekjoon.tree;

// #1967
import java.io.*;
import java.util.*;

public class DiameterOfTree2 {

	static List<Node2>[] list;
	static int[] distance;
	static boolean[] check;
	static int start, max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		list = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			list[i] = new ArrayList<Node2>();
		}

		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Node2(b, c));
			list[b].add(new Node2(a, c));
		}


		bfs(1,n);
		
		
		int s = 1;
		for(int i=2; i<n+1; i++) {
			if(distance[s] < distance[i]) {
				s=i;
				
			}
		}
		bfs(s,n);
		
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
//			System.out.println(num);
			for (Node2 node : list[num]) {
				int nPos = node.end;
				int nDis = node.weight;

				if (!check[nPos]) {

					check[nPos] = true;
					q.add(nPos);
					distance[nPos] = nDis + distance[num];
				}

			}
		}

	}

}

class Node2 {
	int end;
	int weight;

	public Node2(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}
