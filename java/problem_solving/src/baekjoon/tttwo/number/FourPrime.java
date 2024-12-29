package baekjoon.tttwo.number;

// #1153 number 네 개의 소수 - 골드바흐의 추측 
import java.io.*;
import java.util.*;

public class FourPrime {

	static int n;
	static boolean[] isPrime;
	static int[] ans;
	static List<Integer> prime;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		prime = new ArrayList<>();
		artaos();
		
		ans = new int[4];
		
		System.out.println(prime.size());
		if(n<8) {
			System.out.println(-1);
			return;
		}else {
			if(n%2 ==0) {
				n -= 4;
				goldbach(n);
				ans[0] = ans[1] = 2;
			}else {
				n -= 5;
				goldbach(n);
				ans[0] = 2;
				ans[1] = 3;
			}
		}
		
		for(int i=0; i<4; i++) {
			System.out.print(ans[i]+" ");
		}
	}
	
	static void goldbach(int num) {
		for(int i=0; i<prime.size(); i++) {
			for(int j=0; j<prime.size(); j++) {
				int sum = prime.get(i) + prime.get(j);
//				System.out.println(prime.get(i)+" " + prime.get(j));
				if(sum == num) {
					ans[2] = prime.get(i);
					ans[3] = prime.get(j);
					return;
				}else if(sum > num) {
					break;
				}
			}
		}
	}
	
	static void artaos() {
		for(int i=2; i*i<=n; i++) {
			if(isPrime[i]) {
				for(int j=i*i; j<=n; j+=i) {
					isPrime[j] = false;
				}
			}
		}
		
		for(int i=2; i<=n; i++){
			if(isPrime[i]) prime.add(i);
		}
	}
}
