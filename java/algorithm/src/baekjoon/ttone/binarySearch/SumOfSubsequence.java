package baekjoon.ttone.binarySearch;

// #1208 binarySearch 부분수열의 합2 - 투포인터, 이진탐색 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumOfSubsequence {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int size = n/2;
		int[] a = new int[1<<(n-size)];
		int[] b = new int[1<<(size)];
		for(int i=0; i<(1<<n-size); i++) {
			for(int j=0; j<n-size; j++) {
				if((i&(1<<j))==(1<<j)) {
					a[i] +=arr[j];
				}
			}
		}
		for(int i=0; i<(1<<size); i++) {
			for(int j=0; j<size; j++) {
				if((i&(1<<j))==(1<<j)) {
					b[i]+= arr[j+(n-size)];
				}
			}
		}
		
		Arrays.sort(a);
		Arrays.sort(b);
		for(int num : a) {
			System.out.print(num+" ");
		}
		System.out.println();
		
		for(int num : b) {
			System.out.print(num+" ");
		}
		System.out.println();
	
		binarySearchSolve(a,b,s);
		twoPointerSolve(a,b,s);
	}
	
	static void binarySearchSolve(int[] a, int[] b, int s) {
		long cnt=0;
		for(int i=0; i<a.length;) {
			int av = a[i];
			long aTerm = upper_bound(a, av) -lower_bound(a, av);
			long bTerm = upper_bound(b, s-av)-  lower_bound(b, s-av);
//			System.out.println(aTerm+" - " + bTerm);
			cnt+= aTerm*bTerm;
			i+=aTerm;
		}
		if(s==0) cnt--;
		System.out.println(cnt);
	}
	
	static int upper_bound(int[] arr, int t) {
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
	
	static int lower_bound(int[] arr, int t) {
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
	
	static void twoPointerSolve(int[] a, int[] b, int s) {
		int ap =0;
		int bp = b.length-1;
		long cnt = 0;
		while(ap<a.length && bp>-1){
			int av = a[ap], bv = b[bp];
			
			if(av+bv==s) {
				long ac=0, bc=0;
//				System.out.println("find " + ap +" - " + bp);
				while(ap<a.length && av == a[ap]) {
					ac++;
					ap++;
				}
				
				while(bp>-1&& bv == b[bp]) {
					bc++;
					bp--;
				}
//				System.out.println(ac +" * " + bc);
				cnt += ac*bc;
			}
			
			if(av+bv < s) {
				ap++;
			}else if(av+bv>s) {
				bp--;
			}
		}
		
		if(s==0) cnt--;
		System.out.println(cnt);
	}
	
	
}


