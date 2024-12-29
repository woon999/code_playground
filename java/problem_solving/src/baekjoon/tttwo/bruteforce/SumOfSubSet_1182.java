package baekjoon.tttwo.bruteforce;

// #1182 bruteforce 부분 수열의 합

import java.io.*;
import java.util.*;

public class SumOfSubSet_1182 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dfs(arr, 0, 0, s, arr.length);
		System.out.println("ans = " + ans);
	}

	static int ans = 0;
	static void dfs(int[] nums, int idx, int sum, int target, int n){
		if(idx == n){
			return;
		}

		if(nums[idx] +  sum == target){
			ans++;
		}
		dfs(nums, idx+1, sum, target, n);
		dfs(nums, idx+1, sum + nums[idx], target, n);
	}
}
