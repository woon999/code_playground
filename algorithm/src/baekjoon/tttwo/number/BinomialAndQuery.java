package baekjoon.tttwo.number;

// #13977 number 이항 계수와 쿼리 
import java.io.*;
import java.util.StringTokenizer;

public class BinomialAndQuery {

	static final long MOD = (long)(1e9 + 7);
	static final int SIZE = (int)(4*1e6 +1);
	static long[] fact;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		fact = new long[SIZE];
		fact[0] = fact[1] = 1;
		for(int i=2; i<SIZE; i++) {
			fact[i] = fact[i-1] * i % MOD;
		}
		
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(fact[a] * pow(fact[a-b]*fact[b]%MOD, MOD-2)%MOD).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static long pow(long a, long b) {
		if(b==1) {
			return a % MOD;
		}
		if((b&1) == 1) {
			return pow(a, b-1) * a % MOD;
		}
		
		return pow(a*a%MOD, b/2);
	}
	
}
