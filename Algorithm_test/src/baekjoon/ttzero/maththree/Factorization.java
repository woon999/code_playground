package baekjoon.ttzero.maththree;

// #11653
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Factorization {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int p =2;
		while(n!=1) {
			
			if(n%p ==0) {
				n /= p;
				System.out.println(p);
			}else {
				p++;
			}
			
		}
	}
}
