package baekjoon.sort;

// #1026
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Treasure {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] a = new int[n];
		int[] b = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(a);
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(b);
		
		int sum=0;
		
		for(int i=0; i<n; i++) {
			sum += a[i]*b[n-i-1];
		}
		
		System.out.println(sum);
		
		
	}
}
