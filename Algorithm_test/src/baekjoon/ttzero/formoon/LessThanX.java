package baekjoon.ttzero.formoon;

import java.io.*;

public class LessThanX {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] arr = br.readLine().split(" ");
        int n = Integer.parseInt(arr[0]); 
        int x = Integer.parseInt(arr[1]); 
		
        String[] arr2 = br.readLine().split(" ");
        int[] num = new int[n];
        
		for(int i =0; i<n; i++) {
			num[i] = Integer.parseInt(arr2[i]);
		}
		
		for(int j =0; j<num.length; j++) {
			if(num[j]<x) {
				System.out.print(num[j]+" ");
			}
		}
	}
}
