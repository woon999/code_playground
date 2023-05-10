package baekjoon.ttone.binarySearch;

// #13711 이진탐색 LCS4
import java.io.*;
import java.util.StringTokenizer;

public class LCS4 {

	static int n;
	static int[] memo;
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		int[] comp = new int[n];
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for(int i=0; i<n; i++) {
			comp[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			int num =  Integer.parseInt(st.nextToken());
			arr[num-1] = i;
		}
		
		int[] res = new int[n];
		for(int i=0; i<n ;i++) {
			res[i] = arr[comp[i]-1]+1;
		}
		

		for(int num : res) {
			System.out.print(num+" ");
		}
		System.out.println();
	
		
		// res의 LIS구하기 
		memo = new int[n+1];
		
		int answer = LIS(res);
		for(int num : memo) {
			System.out.print(num+" ");
		}
		System.out.println();
		
		System.out.println(answer);
	}
	
	
	static int LIS(int[] arr) {
		int len=0;
		int idx=0;
		for(int i=0; i<n; i++) {
			if(arr[i] > memo[len]) {
				len +=1;
				System.out.println("길이 : " + len +" -> " +arr[i]);
				memo[len] = arr[i];
			}else {
//				idx = Arrays.binarySearch(memo, 0, len, arr[i]);
//				if(idx<0) {
//					idx = -idx-1;
//				}
				idx = binarySearch(0, len, arr[i]);				
//				System.out.println(arr[i] +" -> "+idx);
				memo[idx] = arr[i];
				
			}
			
		}
		
		return len;
	}
	
	static int binarySearch(int left, int right, int key) {
		
		int mid =0;
		
		while(left<right) {
			mid = (left+right)/2;
			if(memo[mid] < key) {
				left = mid+1;
			}else {
				right = mid;
			}
		}
		
		return right;
		
	}

}

//8
//1 4 6 7 5 2 3 8
//1 2 3 6 7 8 4 5
