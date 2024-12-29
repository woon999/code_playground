package baekjoon.ttzero.mathfour;

// #2480
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dice {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] arr = {a, b, c};
		int res =0;
		int cnt=0;
		
		Arrays.sort(arr);
		
		for(int i=0; i<3; i++) {
			if(arr[0] == arr[i]) {
				cnt = arr[0];
			}else {
				cnt = arr[1];
			}
						
		}
		if(arr[0] == arr[1] && arr[0]==arr[2] && arr[1]==arr[2]) {
			res = 10000 + arr[0] *1000;
		}else {
			if(arr[0] != arr[1] && arr[0]!=arr[2] && arr[1]!=arr[2]) {
				res = arr[2]*100;
			}else {
				res = 1000+ cnt*100;
			}
		}
		System.out.println(res);
		
	}
}
