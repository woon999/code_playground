package baekjoon.ttzero.mathfour;

// #2960
import java.io.*;
import java.util.StringTokenizer;

public class SieveOfEratosthenes {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		boolean[] primeNum = new boolean[n+1];
		
		int cnt = 0;
		
		for(int i=2; i<n+1; i++) {
			primeNum[i] = true;
		}
		
		for(int i=2; i<n+1; i++) {
			for(int j=i; j<n+1; j+= i) {
				if(!primeNum[j]) continue;
				
				primeNum[j] =false;
				cnt++;
				if(cnt == k) {
					System.out.println(j);
				}
			}
		}
		
	}
}
