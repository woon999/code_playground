package baekjoon.onedimensional;

import java.io.*;
import java.util.*;

public class Remainder {
	
	private static int count = 1;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[10];
		
		for (int i = 0; i < 10; i++) {
			int a = Integer.parseInt(br.readLine());
			arr[i] = a%42;
		}
		
		Arrays.sort(arr);
		for (int i = 0; i < 9; i++){
			if(arr[i] != arr[i+1])
				count++;
		}
		
//		HashSet<Integer> set = new HashSet<Integer>();
//		for(int i=0; i < 10; i++) {
//			set.add(arr[i]);
//		}
//		System.out.println(set);
//		
//		System.out.println(set.size());
		
		System.out.println(count);
		
	}
}
