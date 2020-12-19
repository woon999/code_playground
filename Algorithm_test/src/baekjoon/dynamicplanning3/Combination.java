package baekjoon.dynamicplanning3;

// #2407
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Combination {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		BigInteger num1 = BigInteger.ONE, num2 = BigInteger.ONE;
		
		for(int i=0; i<m; i++) {
			num1 = num1.multiply(new BigInteger(String.valueOf(n-i)));
			num2 = num2.multiply(new BigInteger(String.valueOf(i+1)));
		}
		
		System.out.println(num1.divide(num2));
		
	}
}
