package baekjoon.greedy;

// #1439
import java.io.*;

public class Flip {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split("");
		int zero = 0, one = 0;

		if (s[0].equals("0"))
			zero++;
		else
			one++;

		for (int i = 1; i < s.length; i++) {
			if (!s[i - 1].equals(s[i])) {
				if (s[i].equals("0")) {
					zero++;
				} else {
					one++;
				}
			}
		}
		
		System.out.println(Math.min(zero, one));

	}
}
