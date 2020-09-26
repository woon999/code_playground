package baekjoon.greedy;

// #1758
import java.io.*;
import java.util.*;

public class Starbucks {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		
		Arrays.sort(arr);
		
		int j = arr.length;
		long total = 0;
		
		for(int i=0; i<n; i++) {
			int tmp = arr[j-1] - i;
			if(tmp <0) break;
			
			total += tmp;
			j--;
		}
		
		System.out.println(total);
		
	}
}
