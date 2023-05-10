package baekjoon.ttone.twoPointer;

// #1806 twoPointer 부분합 
import java.io.*;
import java.util.*;

public class SubSum {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int len = Integer.MAX_VALUE;
		int sum = 0;
		while(start <= end && end <= n) {
			if(sum < s) {
				sum += arr[end++];
			} else if(sum >= s) {
				len = Math.min(len, end-start);
				sum -= arr[start++];
				
			}  
		}
		
		System.out.println(len==Integer.MAX_VALUE ? 0 : len);
	}
}






