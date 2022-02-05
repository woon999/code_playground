package baekjoon.tttwo.binarysearch;

// #3745 binarysearch 오름세 - LIS 
import java.io.*;
import java.util.*;

public class Increase {

	static int[] arr, dp;
	public static void main(String[] args){
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
			String line = null;
			while((line = br.readLine()) != null) {
				int n = Integer.parseInt(line.trim());
				arr = new int[n];
				dp = new int[n+1];
				
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < n; i++) {
					arr[i] = Integer.parseInt(st.nextToken());
				}
			
				int len = 0;
				int idx = 0;
				for (int i = 0; i < n; i++) {
					if(arr[i] > dp[len]) {
						dp[++len] = arr[i];
					}else {
						idx = lowerbound(0, len, arr[i]);
						dp[idx] = arr[i];
					}
				}
				sb.append(len+"\n");
			}
			
		} catch(Exception e) {
			
		} finally {
			System.out.println(sb.toString());
		}
		
	}
	
	static void print() {
		for(int i=0; i<dp.length; i++) {
			System.out.print(dp[i]+" ");
		}
		System.out.println();
	}
	
	static int lowerbound(int s, int e, int key) {
		while(s < e) {
			int mid = (s+e)/2;
			
			if(dp[mid] >= key) {
				e = mid;
			}else {
				s = mid+1;
			}
		}
		return e;
	}
}
