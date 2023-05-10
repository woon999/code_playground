package baekjoon.ttzero.maththree;

// #2004
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CombinationZero {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Integer.parseInt(st.nextToken());
		long m = Integer.parseInt(st.nextToken());
		long five = 0;
		long two = 0;
	
		five =factCount(n, 5);
		two = factCount(n, 2);

		five -= factCount(m, 5);
		two -= factCount(m, 2);

		five -=factCount(n - m, 5);
		two -= factCount(n - m, 2);

		System.out.println(Math.min(two, five));

	}

	static long factCount(long x, long num) {

		long result = 0;
		long count = num;
		for (long i = count; i <= x; i *= count) {
			result += x/i;
		}
		return result;
	}
}
