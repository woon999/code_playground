package baekjoon.dynamicplanning3;

// #2011
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SecurityCode {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String code = br.readLine();
		
		int codeLen = code.length();
		long[] dp = new long[codeLen+1]; 
		dp[0] = dp[1] = 1;

		if(code.charAt(0) == '0') {
			System.out.print(0);
		}
		else if(code.charAt(codeLen-1) == '0' &&
				code.charAt(codeLen-2) != '1' &&
				code.charAt(codeLen-2) != '2') {
			System.out.print(0);
		}
		else {
			for(int i = 2; i <= codeLen; i++) {
				int tmp = Integer.parseInt(code.charAt(i-1) + "");
				if(tmp > 0) {
					dp[i] = dp[i-1] % 1000000;
				}
				
				tmp += Integer.parseInt(code.charAt(i-2) + "")*10;
				if(10 <= tmp && tmp <= 26) {
					dp[i] = (dp[i] + dp[i-2]) % 1000000;
				}
			}
			System.out.print(dp[codeLen]);
		}
	}
}
