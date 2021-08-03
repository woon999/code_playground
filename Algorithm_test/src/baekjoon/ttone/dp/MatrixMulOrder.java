package baekjoon.ttone.dp;

// #11049 dp 행렬 곱셈 순서 - bottom-up, top-down  

//testcase
//4
//5 3
//3 2
//2 6
//6 4
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MatrixMulOrder {

	static int n, INF = Integer.MAX_VALUE;
	static int[] data;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		data = new int[n+1];
		StringTokenizer st = null;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			data[i] = a; data[i+1] = b;
		}
		
		dp = new int[n][n];
		for(int i=0; i<n; i++) {
			Arrays.fill(dp[i], INF);
		}
		System.out.println(solve(0,n-1));
		
//      반복문 풀이 	
//		for(int i=2; i<n+1; i++) { // 구간 간격 i 
//			for(int j=0; j<n-i+1; j++) { // 구간 시작점 j (0~n-i))
//				System.out.println(i+" : "+j + " ~ " + (j+i-1));
//				dp[j][j+i-1] = INF;
//				for(int k=j; k<j+i-1; k++) { // 중간 지점 k (j~ j+i-1)) 
//					System.out.println(j+"-"+k + " + " + (k+1)+"-"+(j+i-1));
//					int value = dp[j][k]  + dp[k+1][j+i-1] + (data[j]*data[k+1]*data[j+i]);
//					dp[j][j+i-1] = Math.min(dp[j][j+i-1], value);
//				}
//				System.out.println();
//			}
//		}
//		
//		System.out.println(dp[0][n-1]);
		
	}
	
	// 재귀 풀이 
	static int solve(int pos, int cur) {
		if(pos == cur) return 0;
		if(dp[pos][cur] != INF) return dp[pos][cur];
		
		for(int i=pos; i<cur; i++) {
			int value = solve(pos,i) + solve(i+1, cur) + (data[pos] *data[i+1]*data[cur+1]);
			
			if(dp[pos][cur] > value) {
//				System.out.println(pos+"~"+cur);
			}
			dp[pos][cur] = Math.min(dp[pos][cur], value);
		}
		
		return dp[pos][cur];
	}
}

