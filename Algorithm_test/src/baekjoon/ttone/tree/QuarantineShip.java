package baekjoon.ttone.tree;

// #13209 tree 검역소 - 중간 저장 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class QuarantineShip {

	static int MAX = 100_001;
	static long INF = Long.MAX_VALUE-987_654_321; 
	static long[] data;
	static long[][] dp;
	static boolean[] check;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for(int t=0; t<tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			data = new long[n+1];
			int sum = 0;
			for(int i=1; i<n+1; i++) {
				data[i] = Integer.parseInt(st.nextToken());
				sum += data[i];
			}
			
			list = new ArrayList[n+1];
			for(int i=1; i<n+1; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<n-1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				list[a].add(b);
				list[b].add(a);
			}
			
			long left = sum/(k+1);
			long right = sum;
			while(left+1 < right) {
				System.out.println(left+"," +right);
				long mid = (left+right)/2;
				check = new boolean[n+1];
				dp = new long[n+1][2];
				for(int i=0; i<n+1; i++) {
					Arrays.fill(dp[i], -1);
				}
				solve(10,0,mid);
				System.out.println(dp[10][0] +" <= " + k);
				if(dp[10][0] <= k) {
					right = mid;
				}else {
					left= mid;
				}
				System.out.println("----------------");
				
			}
			sb.append(right+"\n");
//			System.out.println(right);
			
//			for(int i=1; i<n+1; i++) {
//				for(int j=0; j<2; j++) {
//					System.out.print(dp[i][j] +" ");
//				}
//				System.out.println();
//			}
		}
		
		System.out.println(sb.toString());
		
		
	}
	
	static void solve(int pos, int prev, long w){
		
		check[pos] = true;
		boolean flag = true;
		for(int nxt : list[pos]) {
			if(nxt != prev) {
				flag = false;
				solve(nxt, pos, w);
			}
		}
		
		// 마지막 노드 
		if(flag) {
			if(data[pos] <= w) {
				dp[pos][0] = 0; 
				dp[pos][1] = data[pos];
			}else {
				dp[pos][0] = MAX;
				dp[pos][1] = INF;
			}
		}
		System.out.println();
		if(prev !=0) {
			if(dp[prev][0] == -1 && dp[prev][1] == -1) {
				if(data[prev] <= w) {
					dp[prev][0] = 0; 
					dp[prev][1] = data[prev];
				}else {
					dp[prev][0] = MAX;
					dp[prev][1] = INF;
				}
			}
			
			if(dp[prev][1] + dp[pos][1] <= w) {
				System.out.println("#1 "+ pos +" ----" + prev +" :" + w);
				dp[prev][0] += dp[pos][0];
				dp[prev][1] += dp[pos][1];
				System.out.println("#1 "+ dp[prev][1] +" + " + dp[pos][1]+ " <= " +w);
				System.out.println("#1  dp[prev][1] : "+ dp[prev][1]);
				System.out.println("#1 "+ dp[prev][0] +" , " + dp[pos][0] );
			}
			else if(data[prev] + dp[pos][1] <= dp[prev][1] && dp[prev][1] <=w) {
				System.out.println("#new "+ pos +" ----" + prev +" :" + w);
				dp[prev][0] += dp[pos][0];
				dp[prev][1] = data[prev] + dp[pos][1];
			}
			else if(dp[prev][1] <= w) {
				System.out.println("#2 바리게이트 "+ pos +" ----" + prev +" :" + w);
				dp[prev][0] += dp[pos][0] + 1;
				dp[prev][1] = data[prev];
				System.out.println("#1 "+ dp[prev][1] +" <= " +w);
				System.out.println("#2 "+ dp[prev][0] +" , " + dp[pos][0] );

			}else {
				System.out.println("#3");
				dp[prev][0] = MAX;
				dp[prev][1] = INF;
			}
		}
	}
}
//1
//12 2
//12 30 1 8 8 6 20 7 5 10 4 1
//10 8
//10 5
//8 7
//8 2
//7 4
//7 1
//5 9
//5 6
//9 12
//6 3
//6 11

//1
//4 0
//6 9 7 5
//4 3
//4 2
//3 1

//1
//4 1
//6 9 7 5
//4 3
//4 2
//3 1


//1
//4 3
//6 9 7 5
//4 3
//4 2
//3 1
