package baekjoon.tttwo.binarysearch;

// #1450 binarysearch 배낭문제 
import java.io.*;
import java.util.*;

public class Knapsack {
	static int c;
	static int[] arr;
	static List<Integer> left, right;
	public static void main(String[] args) throws  IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		left = new ArrayList<>();
		right = new ArrayList<>();
		comb(left, 0, n/2, 0);
		comb(right, n/2, n, 0);
		
		right.sort((a,b) -> (a-b));
		
		int cnt = 0;
		int idx = 0;
		for(int i=0; i<left.size(); i++) {
			idx = upperbound(0, right.size()-1, left.get(i));
			cnt += idx+1;
		}
		System.out.println(cnt);
	}
	
	static int upperbound(int s, int e, int val) {
		while(s <= e) {
			int mid = (s+e)/2;
			if(right.get(mid) <= c-val) {
				s = mid+1;
			}else {
				e = mid-1;
			}
		}
		return e;
	}
	
	static void comb(List<Integer> list, int start, int end, int sum) {
		if(sum > c) return;
		if(start == end) {
			list.add(sum);
			return;
		}
		comb(list, start+1, end, sum);
		comb(list, start+1, end, sum + arr[start]);
	}

}
