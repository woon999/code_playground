package baekjoon.greedy;

// #2812
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakeBigger {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		String s = br.readLine();
		char[] num = new char[n-k];
		int max = 0;
		
		for(int i=0; i<n; i++) {

			if(max == n-k) break;
			
			while(max >0 && k>0 && num[max-1] < s.charAt(i)) {
				max--;
				k--;
			}
			
			num[max++] = s.charAt(i);
			
		}
		
		for(int i=0; i<num.length; i++) {
			sb.append(num[i]);
		}
		
		
		
		
		System.out.println(sb.toString());
	}
}
