package baekjoon.tttwo.twopointer;

// #3273 twopointer 두 수의 합 
import java.io.*;
import java.util.*;

public class SumOfTwoNumbers {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int k = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		int l = 0;
		int r = n-1;
		int cnt = 0;
		while(l<r) {
			int sum = arr[l] + arr[r];
			System.out.println(l+", " + r +" - " + sum);
			if(sum == k) {
				cnt++;
			}
			
			if(sum < k) {
				l++;
			} else {
				r--;
			}
		}
		System.out.println(cnt);
	}
}
