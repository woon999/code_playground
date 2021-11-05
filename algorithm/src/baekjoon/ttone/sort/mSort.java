package baekjoon.ttone.sort;

import java.io.*;

public class mSort {
	
	static int[] arr, tmp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		tmp = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		mergeSort(0, n-1);
		for(int num : arr) {
			sb.append(num+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void mergeSort(int left, int right) {
		if(left < right) {
			int mid = (left+right)/2;
			mergeSort(left, mid);
			mergeSort(mid+1, right);
			
			int l = left;
			int r = mid+1;
			
			int idx=  l;
			
			while(l <= mid || r <= right) {
				if(r > right || (l <= mid && arr[l] < arr[r])) {
					tmp[idx++] = arr[l++];
				}else {
					tmp[idx++] = arr[r++];
				}
			}
			
			for(int i= left; i<=right; i++) {
				arr[i] = tmp[i];
			}
			
		}
	}

}
