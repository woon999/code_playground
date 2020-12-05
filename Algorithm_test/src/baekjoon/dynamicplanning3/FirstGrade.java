package baekjoon.dynamicplanning3;

// #5557
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FirstGrade {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long[] num = null;
		for(int i=0; i<n-2; i++) {
			if(i==0) {
				num = new long[21];
				num[arr[0]] =1;
			}
			
			num = solve(arr, num ,i+1);
		}
		
		System.out.println(num[arr[n-1]]);
		
		
	}
	
	private static long[] solve(int[] arr, long[] tmp, int x) {
		
		long[] num = new long[21];
		for(int i=0; i<21; i++) {
			if(tmp[i] ==0)continue;
			else {
				if(i-arr[x] >= 0 && i -arr[x] <21) {
					num[i-arr[x]] += tmp[i];
				}
				if(i+arr[x] >=0 && i+arr[x] <21) {
					num[i+arr[x]] += tmp[i];
				}
			}
		}
		return num;
	}
}
