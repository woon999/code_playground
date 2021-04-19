package baekjoon.ttzero.binarysearch;

// #1654
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CropTheLANCable {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] cable = new int[k];
		for(int i=0; i<k; i++) {
			cable[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(cable);
		
		long max = cable[k-1];
		long min = 1;
		long mid = 0;
		
		while(max >= min) {
			mid = (max + min)/2;
			
			long cnt =0;
			for(int i=0; i<k; i++) {
				cnt += cable[i]/mid;
			}
			System.out.println("min :" + min +", max :" +max);
			System.out.println("mid :" +mid);
			System.out.println("cnt :"+ cnt);
			System.out.println("mmmmmmmmmmmmmmmmmmm");
			if(cnt >=n) {
				min = mid +1;
			}else if(cnt <n) {
				max = mid-1;
			}
		}
		
		System.out.println(max);
	}
}
