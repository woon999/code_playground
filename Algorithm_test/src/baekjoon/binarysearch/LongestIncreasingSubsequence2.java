package baekjoon.binarysearch;

// #12015
// #12738 과 동일 

import java.io.*;
import java.util.*;

public class LongestIncreasingSubsequence2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] result = new int[n];
		result[0] = arr[0];
		int size=1;
		for(int i =1; i<n; i++) {
			int standard = arr[i];
			if(result[size-1] < standard) {
				result[size++] = standard;
			}
			else {
				int left = 0;
				int right = size;
				int mid =0;
				
				while(left < right) {
					mid =(left + right)/2;
					if(result[mid]<standard) {
						left = mid +1;
					}
					else {
						right = mid;
					}
				}
				result[right] = standard;
			}

//			System.out.println(Arrays.toString(result));
//			System.out.println("===============");
		}
		
		System.out.println(size);
	}
}
