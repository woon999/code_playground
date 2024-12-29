package baekjoon.ttone.dp;

// #1912 dp 연속합 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ConsecutiveSum {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cur =Integer.parseInt(st.nextToken());
		int max =cur;
		for(int i=1; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			cur = Math.max(0, cur) + num;
			System.out.println(cur);
			max = Math.max(max, cur);
		}
		
		System.out.println(max);
	}
}
