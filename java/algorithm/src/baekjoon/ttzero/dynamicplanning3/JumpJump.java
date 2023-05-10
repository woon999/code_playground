package baekjoon.ttzero.dynamicplanning3;

// #11060
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JumpJump {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int[] map = new int[n];
		for(int i=0; i<n; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		
		for(int i=0; i<n; i++) {
			if(dp[i] == -1) {
				continue;
			}
	
			for(int j=1; j<map[i]+1; j++) {
				if(i+j <n) {
					if(dp[i+j] == -1) {
						dp[i+j] = dp[i]+1;
					}else {
						dp[i+j] = Math.min(dp[i+j], dp[i] +1);
					}
				}
			}
			
		}
		
		System.out.println(dp[n-1]);
	}
}
