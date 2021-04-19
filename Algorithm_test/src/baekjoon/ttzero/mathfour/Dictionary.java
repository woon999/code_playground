package baekjoon.ttzero.mathfour;

// #1256
import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Dictionary {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		BigInteger k = new BigInteger(st.nextToken());
		
		BigInteger comb = combination(n, m);
		System.out.println("comb : " +comb);
		if(comb.compareTo(k) == -1) {
			System.out.println("-1");
		}else {
			String res = "";
			comb = BigInteger.ZERO;
			while(comb.compareTo(k) != 0) {
				BigInteger tmp = comb;
				comb = comb.add(combination(n-1, m));
				if(comb.compareTo(k) == -1) {
					m--;
					res += "z";
				}else {
					n--;
					res += "a";
					comb = tmp;
				}
				if(n==0 || m==0) break;
			}
			for(int i=1; i<m+1; i++) {
				res += "z";
			}
			
			for(int i=1; i<n+1; i++) {
				res += "a";
			}
			System.out.println(res);
		}
		
		
	}
	
	public static BigInteger combination(int n, int m) {
		BigInteger up = new BigInteger("1");
		BigInteger down = new BigInteger("1");
		
		for(int i=1; i<n+1; i++) {
			up = up.multiply(new BigInteger(String.valueOf((n+m) - (i-1))));
			down = down.multiply(new BigInteger(String.valueOf(i)));
			System.out.println("up :" + up + ", down :" + down);
		}
		
		return up.divide(down);
	}
}
