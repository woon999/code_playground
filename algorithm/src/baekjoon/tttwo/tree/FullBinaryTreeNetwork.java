package baekjoon.tttwo.tree;

// #12888 tree 완전 이진 트리 도로 네트워크 
import java.io.*;

public class FullBinaryTreeNetwork {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int h = Integer.parseInt(br.readLine());
		
		long[] dp = new long[h+1];
		dp[1] =1;
		for(int i=2; i<=h; i++) {
			if((i&1) == 1) { 
				dp[i] = dp[i-1]*2 -1;
			}else {
				dp[i] = dp[i-1]*2 +1;
			}
		}
		
		System.out.println(dp[h]);
	}
}
	
	
