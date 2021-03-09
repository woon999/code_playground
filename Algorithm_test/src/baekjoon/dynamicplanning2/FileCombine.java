package baekjoon.dynamicplanning2;

// #11066
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FileCombine {

	 public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t  = Integer.parseInt(br.readLine());
		for(int i=0; i<t;i++) {
			int n = Integer.parseInt(br.readLine());
			int[] arr =new int[n];
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println(comb(arr));
		}
	}             
	 
	  static int allSum(int[] sum, int start, int end) {
	        if (start == 0) {
	            return sum[end];
	        } else {
	            return sum[end] - sum[start - 1];
	        }
	    }
	 static int comb(int[] arr) {
		 int len = arr.length;
		 int[][] dp = new int[len][len];
		 int[] sum = new int[len];
		 
		 sum[0] = arr[0];
		 for(int i=1; i<len; i++) {
			 sum[i] = sum[i-1] + arr[i];
		 }
		 
		 for(int i=0; i<len-1; i++) {
			 dp[i][i+1] = arr[i] + arr[i+1];
		 }
		 System.out.println(Arrays.deepToString(dp));
		 
		 for(int j=2; j<len; j++) { //��
			 for(int i=0; i+j <len; i++) { //��
				 for(int k=i; k<i+j; k++) {
					 if(dp[i][i+j]==0) {
						 dp[i][i+j] = dp[i][k] + dp[k+1][i+j] + allSum(sum,i,i+j);
					 }else {
						 dp[i][i+j] = Math.min(dp[i][i+j], dp[i][k] + dp[k+1][i+j] + allSum(sum,i,i+j));
					 }
				 }
			 }
		 }
//		 System.out.println(Arrays.deepToString(dp));
		 return dp[0][len-1];
	 }
}
