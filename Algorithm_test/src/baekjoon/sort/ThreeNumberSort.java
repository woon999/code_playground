package baekjoon.sort;

// #2752
import java.io.*;
import java.util.*;

public class ThreeNumberSort {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[3];
		for(int i=0; i<3; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		
		for(int i : arr) {
			System.out.print(i+" ");
		}

	}
	
}