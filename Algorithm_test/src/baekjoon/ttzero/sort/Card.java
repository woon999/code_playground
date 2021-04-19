package baekjoon.ttzero.sort;

// #11652
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Card {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long[] card = new long[n];
		
		for(int i=0; i<n; i++) {
			card[i] = Long.parseLong(br.readLine());
		}
		
		if(n==1) {
			System.out.println(card[0]);
			return;
		}
		Arrays.sort(card);
		
		int cnt =1;
		int max=0;
		
		long ans =0;
		
		for(int i=0; i<n-1; i++) {
			if(i==n-2) {
				if(card[i] == card[i+1]) cnt++;
				
				if(max < cnt) {
					max = cnt;
					ans = card[i];
					cnt =1;
				}else if(max == cnt) {
					ans = Math.min(ans, card[i]);
				}
			}
			else if(card[i] == card[i+1]) cnt++;
			
			else {
				if(max < cnt) {
					max = cnt;
					ans = card[i];
					cnt =1;
				}else if(max == cnt) {
					ans = Math.min(ans, card[i]);
					max = cnt;
					cnt =1;
				}
				else {
					cnt=1;
				}
			}
		}
		
		System.out.println(ans);
		
	}
}
