package baekjoon.greedy;

// #1449
import java.io.*;
import java.util.*;

public class Repairman {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());	
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<arr.length; i++) 
			arr[i] = Integer.parseInt(st.nextToken()); 

		

        Arrays.sort(arr);

        int count = 0;
		for(int i = 0; i < n; i++) {
			if(i == n - 1) {
				count++;
				continue;
			}
			
			int hole = arr[i++];
			while(n > i) {
				if(arr[i] - hole <= l - 1) {
					i++;
				}
				else {
					break;
				}
			}
			count++;
			i--;
		}

		System.out.println(count);


	}
}
