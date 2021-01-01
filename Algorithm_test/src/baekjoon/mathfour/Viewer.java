package baekjoon.mathfour;


// #13458
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Viewer {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		
		st = new StringTokenizer(br.readLine());
		
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		System.out.println(solve(arr,b,c));
		
	}
	
	static long solve(int[] arr, int b,int c) {
		long min =0;
		for(int i=0; i<arr.length; i++) {
			arr[i] -= b;
			min++;
			if(arr[i] > 0) {
				min += arr[i] /c;
				if(arr[i] % c != 0) min++;
			}
		}
		return min;
	}

}
