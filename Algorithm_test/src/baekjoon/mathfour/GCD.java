package baekjoon.mathfour;

// #9613
import java.io.*;
import java.util.StringTokenizer;

public class GCD {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
		
			int num = Integer.parseInt(st.nextToken());
			
			int[] numbers = new int[num];
			
			for(int j=1; j<num+1; j++) {
				numbers[j-1] = Integer.parseInt(st.nextToken());
			}
			
			long res =0;
			for(int k=0; k<numbers.length; k++) {
				int idx = k+1;
				
				while(idx < numbers.length) {
					res += gcd(numbers[k], numbers[idx]);
					idx++;
				}
			}
			
			System.out.println(res);
		}
	}
	
	static int gcd(int a, int b) {
		if(b==0) {
			return a;
		}else{
			return gcd(b, a%b);
		}
	}
}
