package baekjoon.ttzero.maththree;

//#2609
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreatestCommonDivisorAndLeastCommonMultiple {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Integer.parseInt(st.nextToken());
		long m = Integer.parseInt(st.nextToken());

		System.out.println(gcd(Math.max(n, m),Math.min(n, m)));
		System.out.println(lcm(Math.max(n, m),Math.min(n, m)));
	}

	static long gcd(long n, long m) {

		if (n % m == 0) {
			return m;
		} else {
			return gcd(m, n % m);
		}
	}
	
	static long lcm(long n, long m) {
		return (n*m)/gcd(n,m);
	}
}
