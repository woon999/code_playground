package baekjoon.ttzero.divideandconquer;

// #1629
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Multiply {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		long c = Integer.parseInt(st.nextToken());
		
		System.out.println(sol(a,b,c));
	}
	
	static long sol(long a,long b, long c) {
		if(b==0) {
			return 1;
		}
		
		if(b%2==1) {
			return sol(a,b-1,c)*a %c;
		}else {
			long div = sol(a, b/2, c)%c;
			return div*div %c;
		}
	}
}
