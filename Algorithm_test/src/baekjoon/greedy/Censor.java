package baekjoon.greedy;

// #2212
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Censor {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int[] pos = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			pos[i] = Integer.parseInt(st.nextToken());
		}
				
		Arrays.sort(pos);
		
		int[] dif = new int[n-1];
		for(int i=0; i<n-1; i++) {
			dif[i] = pos[i+1] - pos[i];
		}
		Arrays.sort(dif);
		
		int result=0;
		for(int i=0; i<n-k; i++) {
			result += dif[i];
		}
		
		System.out.println(result);
		
		
		
		
		
				
	}
}
