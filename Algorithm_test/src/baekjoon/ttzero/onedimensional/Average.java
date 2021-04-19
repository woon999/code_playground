package baekjoon.ttzero.onedimensional;

import java.io.*;
import java.util.*;

public class Average {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		double[] arr = new double[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i =0; i<n; i++) {
			arr[i]  = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		double average =0;
		for(int i = 0; i<n; i++) {
			arr[i] = (arr[i]/arr[n-1])*100;
			average += arr[i];
		}
		
		System.out.println(average/n);
		
	}
}
