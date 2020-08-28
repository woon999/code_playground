package baekjoon.phasealignment;

// #2252
import java.io.*;
import java.util.*;

public class LineUp {
	
	static int n,m;
	static int[] num;
	static ArrayList<Integer>[] list;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		num = new int[n+1];
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			list[x].add(y);
			num[y]++;
		}
		
		
		solve();
		
		System.out.println(sb.toString());
		
	}
	

	static void solve() {
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i=1; i<n+1; i++) {
			if(num[i]==0) {
				q.add(i);
			}
		}
		
		
		while(!q.isEmpty()) {
			sb.append(q.peek()+" ");
			
			int pos = q.poll();
			
			for(int i=0; i<list[pos].size(); i++) {
				int nxt = list[pos].get(i);
				num[nxt]--;
				
				if(num[nxt]==0) {
					q.add(nxt);
				}
			}
		}
	}

}
