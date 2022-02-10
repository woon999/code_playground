package baekjoon.tttwo.number;

// #2014 number 소수의 곱 
import java.io.*;
import java.util.*;

public class PrimeMul {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		long[] prime = new long[k];
		Queue<Long> q = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) {
			prime[i] = Integer.parseInt(st.nextToken());
			q.add(prime[i]);
		}
		
		for(int i=0; i<n-1; i++) {
			long num = q.poll();
			for(int j=0; j<k; j++) {
				long mul = num * prime[j];
				q.add(mul);
				
				if(num%prime[j] ==0)break;
			}
		}
		System.out.println(q.poll());
		
		
	}
}
