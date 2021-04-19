package baekjoon.ttzero.greedy;

// #15904
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UCPC {

	static char[] ucpc = { 'U', 'C', 'P', 'C' };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		int len = 4;
		int idx = 0;
		for (int i = 0; i < s.length(); i++) {
			if (idx < len) {
				if (s.charAt(i) == ucpc[idx])
					idx++;
			}

		}

		if (len == idx)
			System.out.println("I love UCPC");
		else
			System.out.println("I hate UCPC");
	}
}
