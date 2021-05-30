package baekjoon.ttone.sort;

import java.io.*;

public class qSort {
	
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		quickSort(0,n-1);
		
		for(int num : arr) {
			sb.append(num+"\n");
		}
		System.out.println(sb.toString());
		
	}
	
	static void quickSort(int left, int right) {
		if(left>= right) {
			return;
		}
		
		int pivot = partition(left, right);
		quickSort(left,pivot-1);
		quickSort(pivot+1,right);
	}
	
	
	static int partition(int left, int right) {
		int pivot = arr[left];
		int i = left;
		int j = right;
		
		while(i < j) {
			while(arr[j] > pivot&& i<j) {
				j--;
			}
			while(arr[i] <= pivot && i<j) {
				i++;
			}
			swap(i,j);
		}
		swap(left,i);
		
		return i;
		
	}
	
	static void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
