package baekjoon.ttzero.binarysearch;

// #2110
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RouterInstallation {
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] house = new int[n];
		for(int i=0; i<n; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		
		int left = 1;
		int right = house[n-1] - house[0];
		int d =0;
		
		while(left<=right) {
			int mid =(left+right)/2;
			int start = house[0];
			int cnt =1;
			
			for(int i=1; i<n; i++) {
				d = house[i] - start;
				if(mid <=d) {
					++cnt;
					start = house[i];
				}
			}
//			System.out.print("left : "+left);
//			System.out.println(", right : "+right);
//			System.out.println("cnt :"+cnt);
//			System.out.println("======================="+ 9/2);
			if(cnt >=c) {
				left =mid+1;
			}
			else {
				right = mid-1;
			}
		}
		System.out.println(right);
		
		
		
	}
}
