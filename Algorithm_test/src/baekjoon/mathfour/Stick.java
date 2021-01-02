package baekjoon.mathfour;


// #1094

import java.io.*;

public class Stick {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int split = 32;
		int comp = 0;
		int count = 0;
		
		if(n == 64) {
			count =1;
		}else {
			while(true) {
				if( n == comp + split) {
					count++;
					break;
				}else if(n>comp+split) {
					comp += split;
					count++;
					split /= 2;
				}else if(n < comp+split) {
					split /= 2;
				}
			}
		}
		
		System.out.println(String.valueOf(count));
		
	}
}
