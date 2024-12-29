package baekjoon.ttzero.onedimensional;

import java.io.*;
import java.util.*;

public class MinMax {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.parallelSort(arr);

		System.out.println(arr[0] + " " + arr[n - 1]);

//		Scanner s = new Scanner(System.in);
//		
//		int n = s.nextInt();
//		
//		int[] arr = new int[n];
//		
//		for(int i = 0; i< n ; i++) {
//			arr[i] = s.nextInt();
//		}
//		
//		Arrays.parallelSort(arr);
//		System.out.println(arr[0]+" "+arr[n-1]);

	}

}
