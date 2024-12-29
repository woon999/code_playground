package baekjoon.ttzero.mathone;

// #1934
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LeastCommonMultiple {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		
		for(int i=0; i<t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long n1 = Long.parseLong(st.nextToken());
			long n2 = Long.parseLong(st.nextToken());

			long gcd = getGCD(Math.max(n1, n2), Math.min(n1, n2));
			System.out.println(n1*n2/gcd);
		}
		
		
	}	
	
	public static long getGCD(long a, long b) {
		while(b>0) {
			long tmp = a;
			a = b;
			b = tmp%b;
		}
		
		return a;
	}
}
