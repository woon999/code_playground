package baekjoon.tttwo.graph;

// #16928 graph 뱀과 사다리 게임 - bfs 
import java.io.*;
import java.util.*;

public class SneakAndLadderGame {

	static int[] sneak, ladder;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		sneak = new int[101];
		ladder = new int[101];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			sneak[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			ladder[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Integer> q = new LinkedList<>();
		int[] dp = new int[101];
		Arrays.fill(dp, -1);
		q.add(1);
		dp[1] = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == 100) {
				return dp[cur];
			}
			
			// 주사위 굴리기 
			for(int i=1; i<=6; i++) {
				int nxt = cur+i;
				
				if(nxt > 100) continue;
				// sneak 이동
				if(sneak[nxt] != 0){
					nxt = sneak[nxt];
				}
				// ladder 이동
				if(ladder[nxt] != 0){
					nxt = ladder[nxt];
				}
				if(dp[nxt] == -1 || dp[nxt] > dp[cur]+1) {
					dp[nxt] = dp[cur]+1;
					q.add(nxt);
				}
			}
		}
		
		return -1;
	}
}
