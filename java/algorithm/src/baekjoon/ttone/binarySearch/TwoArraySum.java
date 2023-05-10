package baekjoon.ttone.binarySearch;

// #2143 binarySearch 두 배열의 합 - 투 포인터, 이진탐색 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoArraySum {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long t = Long.parseLong(br.readLine());
		
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		int[] b = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		int aSize = n*(n+1)/2;
		int bSize = m*(m+1)/2;
		long[] aSum = new long[aSize];
		int idx=0;
		for(int i=0; i<n; i++) {
			int av=0;
			for(int j=i; j<n; j++) {
				av+=a[j];
				aSum[idx++] = av;
			}
		}
		
		long[] bSum = new long[bSize];
		idx=0;
		for(int i=0; i<m; i++) {
			int bv = 0;
			for(int j=i; j<m; j++) {
				bv += b[j];
				bSum[idx++] = bv;
			}
		}
		Arrays.sort(aSum);
		Arrays.sort(bSum);

		for(long num : aSum) {
			System.out.print(num+" ");
		}
		System.out.println();
		for(long num : bSum) {
			System.out.print(num+" ");
		}
		System.out.println();
		
//		twoPointerSolve(aSize,bSize,t, aSum,bSum);
		binarySearchSolve(aSize,bSize,t, aSum,bSum);
	}
	
	static void binarySearchSolve(int aSize, int bSize, long t, long[] aSum, long[] bSum) {
		long cnt=0;
		for(int i=0; i<aSize;) {
			long av = aSum[i];
			long aTerm = upper_bound(aSum, av) -lower_bound(aSum, av);
			long bTerm = upper_bound(bSum, t-av)-  lower_bound(bSum, t-av);
//			System.out.println(aTerm+" - " + bTerm);
			cnt+= aTerm*bTerm;
			i+=aTerm;
		}
		System.out.println(cnt);
	}
	
	static int upper_bound(long[] arr, long t) {
		int left = 0, right =arr.length; 
		while(left<right) {
			int mid = (left+right)/2;
			
			if(t >= arr[mid]) {
				left = mid+1;
			}else {
				right =mid;
			}
		}
		return right;
	}
	
	static int lower_bound(long[] arr, long t) {
		int left = 0, right =arr.length; 
		while(left<right) {
			int mid = (left+right)/2;
			
			if(t <= arr[mid]) {
				right =mid;
			}else {
				left = mid+1;
			}
		}
		return right;
	}
	
	static void twoPointerSolve(int aSize, int bSize, long t, long[] aSum, long[] bSum) {
		int left =0;
		int right = bSize-1;
		long cnt=0;
		while(left<aSize&& right>-1) {
			long asv = aSum[left], bsv = bSum[right];
			long sum = asv + bsv;
//			System.out.println(left +" - " + right + " :"  + sum);
			if(sum ==t) {
//				System.out.println("find " + left + " ~ " + right);
				long ac =0, bc =0;
				while(left<aSize && asv == aSum[left]) {
					left++;
					ac++;
				}
				while(right>-1 && bsv == bSum[right]) {
					right--;
					bc++;
				}
				cnt += ac*bc;
//				System.out.println(ac+"*"+bc);
			}
			if(sum>t) {
				right--;
			}else if(sum<t) {
				left++;
			}
		}
		System.out.println(cnt);
	}
}
