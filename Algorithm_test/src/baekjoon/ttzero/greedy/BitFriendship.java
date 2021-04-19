package baekjoon.ttzero.greedy;

// #12782

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BitFriendship {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for(int c=0; c<t; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] s1 = st.nextToken().split("");
			String[] s2 = st.nextToken().split("");

			int zero = 0, one = 0;

			for (int i = 0; i < s1.length; i++) {
				if (s1[i].charAt(0) != s2[i].charAt(0)) {
					if (s1[i].charAt(0) == '0') {
						zero++;
					} else {
						one++;
					}
				}
			}

			int sum = (zero + one) - Math.min(zero, one);

			System.out.println(sum);

		}
	}
}


