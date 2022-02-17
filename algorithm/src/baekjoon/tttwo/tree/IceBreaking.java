package baekjoon.tttwo.tree;

// #21738 tree 얼음깨기 펭귄 - bfs 
import java.io.*;
import java.util.*;

public class IceBreaking {

	static int n, s;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		System.out.println(n-bfs(p));
	}
	static int bfs(int start) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] check = new boolean[n+1];
		q.add(new int[] {start,0});
		check[start] = true;
		int cnt = 2;
		int needs = 1;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int pos = p[0];
			int dis = p[1];
			
			if(cnt ==0 ) break;
			if(pos <= s) {
				cnt--;
				needs += dis;
//				System.out.println("found : " + pos +" , needs :" + dis);
			}
			
			for(int nxt : list[pos]) {
				if(!check[nxt]) {
					check[nxt] = true;
					q.add(new int[] {nxt, dis+1});
				}
			}
		}
		return needs;
	
	}
}