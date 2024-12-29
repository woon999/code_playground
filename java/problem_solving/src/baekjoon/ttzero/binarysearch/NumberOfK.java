package baekjoon.ttzero.binarysearch;

// #1300
import java.io.*;

public class NumberOfK {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
	
		int left = 1;
		int right = k;
		
		while(left<=right) {
			int mid = (left+right)/2;
			int cnt =0;
			for(int i=1; i<n+1;i++) {
				cnt += Math.min(mid/i, n);
			}
//			System.out.println("left :" + left +", right :" +right);
//			System.out.println("mid :" +mid);
//			System.out.println("cnt :"+ cnt);
//			System.out.println("mmmmmmmmmmmmmmmmmmm");
			if(cnt >= k) {
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		System.out.println(left);
	}
	
	
}
