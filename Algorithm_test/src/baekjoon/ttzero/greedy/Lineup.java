package baekjoon.ttzero.greedy;

// #7570
import java.io.*;
import java.util.StringTokenizer;

public class Lineup {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
        int[] dp = new int[n + 1];
        int max = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	
            int k = Integer.parseInt(st.nextToken());
            
            dp[k] = dp[k - 1] + 1;
            if(dp[k]>max) max = dp[k];
        }
        System.out.println(n - max);


	}
}
