package baekjoon.ttzero.mathone;

// #1977
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class PerfectSquareNumber {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 1; i <= 100; i++) {
			int s = i * i;
			if ((s >= M) && (s <= N)) {
				list.add(s);
			}
		}
		int sum = 0;

		for (int j = 0; j < list.size(); j++) {
			sum += list.get(j);
		}


		if (list.size() == 0) {
			System.out.println(-1);
		} else {
			System.out.println(sum); 
			System.out.println(list.get(0)); 
		}
	}
}