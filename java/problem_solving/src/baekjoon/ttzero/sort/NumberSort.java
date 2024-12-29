package baekjoon.ttzero.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NumberSort {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n];
		
		for(int i =0; i<n; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		fun(num);
		
		for(int i =0; i<n;i++)
		System.out.println(num[i]);
		
		
	}
	
	public static void fun(int[] num) {
		
		int tmp = 0;
		for(int i =0; i<num.length; i++) {
			for(int j = 1; j<num.length; j++ ) {
				if(num[j-1]>num[j]) {
					tmp = num[j];
					num[j] =num[j-1];
					num[j-1] = tmp;
				}
			}
		}
	}
}
