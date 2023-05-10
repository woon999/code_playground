package baekjoon.tttwo.number;

// #11689 number GCD(n,k)=1 오얼러 피 함수 
import java.io.*;

public class GCD {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		long n = Long.parseLong(br.readLine());
		long pi = n;
		for(long i=2; i*i<=n; i++) {
			System.out.println(pi +" - " + n +" : "+ i);
			if(n%i==0) {
				pi = pi/i*(i-1);
			}
			System.out.println(pi +" - " + n +" : "+ i);
			while(n%i==0) {
				n/=i;
			}
			
		}
		System.out.println(pi +", " + n);
		if(n!=1) {
			pi = pi/n*(n-1);
		}
		
		System.out.println(pi);
		
	
	}
}
