package baekjoon.tttwo.number;

// #2436 number 공약수 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CommonFactor {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int gcd = Integer.parseInt(st.nextToken());
		int lcm = Integer.parseInt(st.nextToken());
		
		int tmp = lcm/gcd;
		long resA = 0, resB =0;
		for(int i=1; i*i<=tmp; i++) {
			if(tmp%i==0) {
				int aa = i;
				int bb = tmp/i;
				if(getGCD(aa,bb)==1) {
					resA = aa*gcd;
					resB = bb*gcd;
				}
			}
		}
		System.out.println(resA+" "+resB);
	}
	
	static int getGCD(int a, int b) {
		return a%b == 0 ? b: getGCD(b, a%b);
	}
}
