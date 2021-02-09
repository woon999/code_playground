package baekjoon.mathfour;

// #10972
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NextPermutation {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if(solve(arr)) {
			for(int i=0; i<n; i++) {
				System.out.print(arr[i] + " ");
			}
		}else {
			System.out.println("-1");
		}
		
		
		
	}
	
	static boolean solve(int[] arr) {
		int i = arr.length-1;
		
		// a[i-1] < a[i]를 만족하는 첫 번째 수 찾기 
		while(i >0 && arr[i-1] >= arr[i]) {
			i -= 1;
		}
		
		if(i<=0) {
			return false;
		}
		
		int j = arr.length-1;
		while(arr[j] <= arr[i-1]) {
			j -=1;
		}
		
		swap(arr, i-1, j);
		
		j = arr.length-1;
		while(i<j) {
			swap(arr, i, j);
			i += 1;
			j -= 1;
		}
		return true;
		
	}
	
	static void swap(int[] arr, int i, int j ) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
