package baekjoon.ttone.twoPointer;

// #2473 twoPointer 세 용액 
import java.io.*;
import java.util.*;

public class ThreePortion {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		for(long num : arr) {
			System.out.print(num+ " ");
		}
		System.out.println();
		twoPointerSolve(arr, n);
		
	}
	
	static void twoPointerSolve(int[] arr, int n)	{
		long min = Long.MAX_VALUE;
		int r1=0, r2=0, r3=0;
		out : for(int i=0; i<n-2; i++) {
			int left = i+1;
			int right = n-1;
			
			while(left<right) {
				long sum = (long)arr[i]+ arr[left] +arr[right];
//				System.out.println(i +" - " +left + " - " + right + " :"  +sum);
				if(min > Math.abs(sum)) {
					min = Math.abs(sum);
					r1 =i; r2=left; r3=right;
//					System.out.println("find " + min);
				}
				
				if(sum==0) {
					r1 =i; r2=left; r3=right;
					break out;
				}else if(sum > 0){
					right--;
				}else {
					left++;
				}
				
			}
		}
		
		System.out.println(arr[r1]+" " + arr[r2] + " " + arr[r3]);
	}
}

