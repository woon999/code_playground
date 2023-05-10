package baekjoon.tttwo.prefixsum;

// #10986 prefixSum 나머지 합  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RemainderSum {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int pSum = 0;
		int[] div = new int[m];
		for(int i=0; i<n; i++) {
			pSum += Integer.parseInt(st.nextToken());
			pSum %= m;
			div[pSum]++;
		}
		
		long ans = div[0];
		for(int i=0; i<m; i++) {
			ans += div[i]*(div[i]-1)/2;
		}
		System.out.println(ans);
	}
}

