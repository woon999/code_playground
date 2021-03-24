package baekjoon.dp;


// #1102 dp (최소 dfs/ 비트마스킹) 발전소 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PowerPlant {
	
	static int[][] dp;
	static int[][] cost;
	static int n;
	static int p;
	static int init = 1_023_525_232;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		cost = new int[n][n];
		dp = new int[n+1][1<<16];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<n+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		String[] status = br.readLine().split("");	
		p = Integer.parseInt(br.readLine());
		
		int pos =0;
		int cnt = 0;
		for(int i=0; i<status.length; i++) {
			if(status[i].equals("Y")) {
				pos = pos | (1<<i);
				cnt++;
			}
		}
		int res = dfs(cnt,pos);
		
		System.out.println(cnt+","+ pos);
		System.out.println(12&(1<<2));
		
//		for(int i=0; i<4; i++) {
//			for(int j=0; j< (1<<4); j++) {
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println();
//		}
		
//		System.out.println(dfs(cnt,pos));
//		System.out.println(dp[cnt][pos]);
		// dfs(cnt,pos) != dp[cnt][pos]
		// 비용이 0일 때,
		// ex) NYN  p=1 -> 비용은 0인데, dp[cnt][pos] = -1 (맨 첨 초기화 값)이므로 같지 않다.
		// 그래서 if(cnt >= p) return 0; 이 필요한 
		
		System.out.println(res == init? -1 : res);		
	}
	
	static int dfs(int cnt, int pNum) {
		if(cnt >= p ) return 0;
		if(dp[cnt][pNum] != -1) return dp[cnt][pNum];
		
		dp[cnt][pNum] = init;
		
		for(int i=0; i<n; i++) {
			// pNum의 발전소가 켜져있을 때
			if((pNum &(1<<i)) == (1<<i)) {
//				System.out.println( (pNum & (1<<i)) +"," + (1<<i));
				for(int j=0; j<n; j++) {
					// 같은 번호의 발전소인 경우 || j도 켜져있는 경우 스킵 
					if((i==j) || (pNum&(1<<j)) == (1<<j)) continue;
					
					
					//	최소값 구하기 dfs
					dp[cnt][pNum] = Math.min(dp[cnt][pNum], dfs(cnt+1, pNum|(1<<j)) + cost[i][j]);
				}
				
			}
		}
		
		return dp[cnt][pNum];
	}
}
