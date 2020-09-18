package baekjoon.greedy;

// #14916
import java.io.*;

public class Change2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int cnt = 0;
		if (n % 5 == 0) {
			System.out.println(n/5);
		} else {
			while (true) {

				if (n < 0) {
					System.out.println(-1);
					break;
				}

				if (n == 0) {
					System.out.println(cnt);
					break;
				}
				
				n -=2;
				cnt++;
				
				if(n % 5 ==0) {
					cnt += n/5;
					System.out.println(cnt);
					break;
				}
			}
		}
	}
}
