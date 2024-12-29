package baekjoon.ttzero.greedy;

// #1744
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TieNumber {
	
	static int n;
	static int arr[];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
			
		System.out.println(solve());
		
	}
	
	static long solve() {
		int left=0;
		int right=n-1;
		long result =0;
		
		
		
//		0, 음수일 떄 
		for(; left <right; left+=2 ) {
			if(arr[left]<1 && arr[left+1] <1) {
				result += arr[left]* arr[left+1];
			}else {
				break;
			}
			
			
		}
		
//		양수일 경우 
		for(; right>0; right-=2) {
//			(1*양수)는 제외 처리 
			if(arr[right]>1 && arr[right-1] >1 ) {
				result += arr[right] * arr[right-1]; 
			}else {
				break;
			}
		}
		
		System.out.println(right+" , " + left);
		
		for(; right>= left; right--) {
			result += arr[right];
		}
		
		return result;
	}
}
