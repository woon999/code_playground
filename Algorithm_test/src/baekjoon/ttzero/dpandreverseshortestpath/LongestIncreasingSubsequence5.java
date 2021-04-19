package baekjoon.ttzero.dpandreverseshortestpath;

// #14003
// 이분탐색 풀이 O(nlogn) 14002문제에서 경로만 추가 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class LongestIncreasingSubsequence5 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n + 1];
		int[] dp = new int[n + 1];
		List<Integer> list = new ArrayList<>();


		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		list.add(Integer.MIN_VALUE);
		
		for(int i=1; i<n+1; i++) {
			int num = arr[i];
			
			
			if(num > list.get(list.size() -1 )) {
				list.add(num);
				dp[i]  =  list.size()-1;
			}else {
				int left = 1;
				int right = list.size()-1;
				int mid =0;
				while(left<right) {
					mid = (left+right) >>1;
					if(list.get(mid) < num) left = mid+1;
					else right = mid;
				}
				list.set(right, num);
				dp[i] = right;
				
			}
		}
		sb.append(list.size()-1 + " \n");

		Stack<Integer> s = new Stack<Integer>();

		int len = list.size()-1;
		for (int i = n; i > 0; i--) {
			if (dp[i] == len) {
				s.push(arr[i]);
				len--;
			}

		}
		while (!s.isEmpty()) {
			sb.append(s.pop() + " ");
		}

		System.out.println(sb.toString());

	}
}
