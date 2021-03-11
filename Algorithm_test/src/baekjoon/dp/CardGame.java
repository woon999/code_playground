package baekjoon.dp;

// #10835 dp 카드게임 
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CardGame {
	
	static int n;
	static int[] left;
	static int[] right;
	static int[][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		left = new int[n];
		right = new int[n];
		for(int i =0; i<n; i++) {
			left[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<n; i++) {
			right[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
 
		System.out.println(Topdown(0,0));
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}
	
	// 재귀 + 메모제이션
	static int Topdown(int l, int r) {
		if(l == n || r == n) return 0;
		
		if(dp[l][r] != -1) return dp[l][r];
		
		// l+1, r+1 : 양쪽 카드를 동시에 제거하는 경우
		// l+1, r : 왼쪽 카드만 제거하는 경우 
		dp[l][r] = Math.max(Topdown(l+1, r+1), Topdown(l+1, r));
		
		// score 점수 메모제이션
		// 해당 l로 얻을 수 있는 점수 다 합하기 (l, r+1) 
		if(left[l] > right[r]) {
			dp[l][r] = Math.max(dp[l][r], Topdown(l, r+1)+right[r]);
		}
		
//		System.out.println("l_idx : " + l +", r_idx : " + r + " -> dp : " +dp[l][r]);
		return dp[l][r];
	}
}
