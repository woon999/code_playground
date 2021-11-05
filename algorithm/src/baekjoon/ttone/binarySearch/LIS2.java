package baekjoon.ttone.binarySearch;

// #12015 이진탐색 가장 긴 증가하는 부분 수열 2 
import java.io.*;
import java.util.*;

public class LIS2 {
	
	static int[] memo;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		memo = new int[n+1];
		
		int len=0;
		int idx=0;
		for(int i=0; i<n; i++) {
			if(arr[i] > memo[len]) {
				len +=1;
				System.out.println("길이 : " + len +" -> " +arr[i]);
				memo[len] = arr[i];
			}else {
				idx = Arrays.binarySearch(memo, 0, len, arr[i]);
//				if(idx<0) {
//					idx = -idx-1;
//				}
				
				idx = binarySearch(0,len, arr[i]);
				System.out.println(arr[i] +" -> "+idx);
				memo[idx] = arr[i];
			}
			
		}
		
		for(int num : memo) {
			System.out.print(num+" ");
		}
		
		System.out.println();
		System.out.println(len);
	}
	
	static int binarySearch(int left, int right, int key) {
		
		int mid =0;
		
		while(left<right) {
			mid = (left+right)/2;
			if(memo[mid] < key) {
				left = mid+1;
			}else {
				right = mid;
			}
		}
		
		return right;
		
	}
}
	