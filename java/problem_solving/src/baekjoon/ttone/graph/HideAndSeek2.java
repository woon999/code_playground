package baekjoon.ttone.graph;

// #12851 graph 숨바꼭질2  
import java.io.*;
import java.util.*;

public class HideAndSeek2 {
	
	static int[] dx = {-1, 1, 2};
	static int cnt=0, res = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		bfs(n, k);
		System.out.println(res);
		System.out.println(cnt);
	}
	
	static void bfs(int start, int destination) {
		Queue<Integer> q = new LinkedList<>();
		int[] move = new int[100_001];
		q.add(start);
		
		while(!q.isEmpty()) {
			int pos = q.poll();
			
			if(res < move[pos]) return;
			if(move[pos]<=res && pos == destination) {
				res = move[pos];
				cnt++;
			}

			for(int i=0; i<3; i++) {
				int next = pos;
				if(i==2) {
					next = pos*dx[i];
				}else {
					next = pos + dx[i];	
				}
				
				if (next>=0 && next <100001) {
					// next 지점에 첫 방문이거나 현재 move보다 더 큰 값일 경우 갱신 
					if(move[next] ==0 || move[next] >= move[pos] + 1) {
						move[next] = move[pos]+1;
						q.add(next);
					}
				}
			}
		}
	}
}
