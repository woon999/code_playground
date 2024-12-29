package baekjoon.tttwo.number;

// #14476 number 최대공약수 하나 빼기 
import java.io.*;
import java.util.StringTokenizer;

public class MinusOneGCD {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n+2];
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] lgcd = new int[n+2];
		for(int i=1; i<=n; i++) {
			lgcd[i] = gcd(arr[i], lgcd[i-1]);
		}
		
		int[] rgcd = new int[n+2];
		for(int i=n; i>0; i--) {
			rgcd[i] = gcd(arr[i], rgcd[i+1]);
		}
		
		int ans = -1;
		int max = -1;
		for(int i=1; i<=n; i++) {
			int res = gcd(lgcd[i-1], rgcd[i+1]);
			if(res > max) {
				if(arr[i]%res !=0) {
					max = res;
					ans = arr[i];
				}
			}
		}
		
		if(ans == -1) {
			System.out.println(-1);
		}else {
			System.out.println(max+" "+ans);
		}
		
	}
	
	static int gcd(int a, int b) {
		if(b == 0) {
			return a;
		}else {
			return gcd(b, a%b);
		}
	}
}
