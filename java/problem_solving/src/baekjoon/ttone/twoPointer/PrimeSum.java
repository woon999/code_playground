package baekjoon.ttone.twoPointer;

// #1644 twoPointer 소수의 연속합 
import java.io.*;
import java.util.*;

public class PrimeSum {

	static int n;
	static boolean[] isPrime;
	static List<Integer> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		makePrime();
		
		list.add(0);
		
		int start = 0;
		int end = 0;
		int sum = 0;
		int cnt = 0;
		while(start<=end && end<list.size()){
			if(sum < n) {
				sum += list.get(end++);
			} else {
				if(sum == n) {
					cnt++;
				}
				sum -= list.get(start++);
			}
		}
		
		System.out.println(cnt);
	}
	
	static void makePrime() {
		isPrime = new boolean[n+1];
		Arrays.fill(isPrime , true);
        
		isPrime [0] = isPrime [1] = false;
		for(int i=2; i*i<=n; i++){
			if(isPrime[i]){
				for(int j=i*i; j<=n; j+=i) {
					isPrime[j] = false;                
				}
			}        
		}
		
		for(int i=1; i<=n; i++) {
			if(isPrime[i]) {
				list.add(i);
			}
		}
	}
	
}
