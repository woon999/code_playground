package baekjoon.dp;


// #10211 dp 최대부분배열문제 (dp or 분할정복) 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaximumSubarray {
	
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int test =0; test<t; test++) {
			int n = Integer.parseInt(br.readLine());
			arr= new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(solve(0,n-1, arr));
			System.out.println(dp(arr));
		}
	}
	static int dp(int[] arr) {
		int max = Integer.MIN_VALUE;
		int pSum = 0;
		for(int i=0; i<arr.length; i++) {
			pSum = Math.max(pSum, 0) + arr[i];
			max = Math.max(max, pSum);
		}
		return max;
	}
	
	
//	----------------------분할정복----------------------------
	static int solve(int left, int right, int[] arr) {
		if(left == right) return arr[left];
		int mid = (left+right)/2;
		
		int mSide= divconq(left,right,mid,arr);
		int lSide = solve(left,mid, arr);
		int rSide =  solve(mid+1, right, arr);
		
		System.out.println("mid :" + mSide+ ",left :" + lSide
							+ ",right :" + rSide);
		
		if(mSide >= lSide && mSide>= rSide) return mSide;
		else if(lSide >= rSide && lSide >= mSide) return lSide;
		else return rSide;
		
	}
	
	static int divconq(int left, int right, int mid, int[] arr) {
		int lSum=0, rSum=0;
		int lMax = Integer.MIN_VALUE;
		int rMax = Integer.MIN_VALUE;
		for(int i=mid; i>=0; i--) {
			lSum += arr[i];
			lMax = lSum > lMax? lSum : lMax;
		}
		
		for(int i=mid+1; i<right+1; i++	) {
			rSum += arr[i];
			rMax = rSum > rMax? rSum : rMax;
		}
		
		System.out.println("left : " + left + ", mid : " +mid + " , right : " +right);
		System.out.println(lMax +" , " + rMax);
		return rMax + lMax;
		
	}
	
//	----------------------분할정복----------------------------
}
