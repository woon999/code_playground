package baekjoon.ttzero.mathfour;

// #2953
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ImACook {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] dp = new int[6];
		
		int num =0;
		int max = Integer.MIN_VALUE;
		
		
		for(int i=1; i<6; i++) {
			int score = 0;
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				score += Integer.parseInt(st.nextToken());
				
			}
			
			dp[i] = score;
			if(max < score) {
				num = i;
				max = score;
			}
		}
		System.out.println(num +" "+ max);
	}
}
