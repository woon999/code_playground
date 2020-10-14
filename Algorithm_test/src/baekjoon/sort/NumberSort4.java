package baekjoon.sort;

// #11931
import java.io.*;

public class NumberSort4 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
		}
		
		sort(arr,0,n-1);
		StringBuilder sb = new StringBuilder();
		for(int i =n-1; i>=0; i--) {
			sb.append(arr[i]+"\n");
			
		}
		System.out.println(sb.toString());
	}
	
    static void sort(int[] data, int l, int r){
        int left = l;
        int right = r;
        int pivot = data[(l+r)/2];
        
        do{
            while(data[left] < pivot) left++;
            while(data[right] > pivot) right--;
            if(left <= right){    
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
                left++;
                right--;
            }
        }while (left <= right);
        
        if(l < right) sort(data, l, right);
        if(r > left) sort(data, left, r);
    }



}
