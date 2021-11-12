package baekjoon.ttone.binarySearch;

// #2467 binarySearch 용액 (이진탐색 또는 투포인터)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Liquid {

	static int n;
	static long[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		binarySearchSolve();
		twoPointerSolve();
	}
	
	static void binarySearchSolve() {
		long min = Long.MAX_VALUE;
		int ml =0, mr = 0;
		for(int i=0; i<n-1; i++) {
			int left =i+1;
			int right =n-1;
			while(left<=right) {
				int mid = (left+right)/2;
				long sum = Math.abs(arr[i]+arr[mid]);
				
				if(min > sum) {
					min = sum;
					ml = i; mr = mid;
//					System.out.println(i+" ~ " + mid + " :" + min);
				}
				if(arr[mid]>= -arr[i]) {
					right = mid-1;
				}else{
					left = mid+1;
				}
			}
		}
		System.out.println(arr[ml]+" "+arr[mr]);
	}
	static void twoPointerSolve(){
		int left =0;
		int right =n-1;
		int ml =0, mr = 0;
		long min = Long.MAX_VALUE;
		while(left<right) {
			long sum = Math.abs(arr[left]+arr[right]);
			if(min > sum) {
				min = sum;
				ml = left; mr = right;
//				System.out.println(left+" ~" + right +" :" + min);
			}
			
			if(sum>=0) {
				right--;	
			}else {
				left++;
			}
		}
//		System.out.println(left+" ~ " + right);
		System.out.println(arr[ml] +" "+arr[mr]);
	}
}
