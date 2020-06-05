package baekjoon.maththree;

// #9375
import java.io.*;
import java.util.*;

public class FashionWang {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {

			int m = Integer.parseInt(br.readLine());
			HashMap<String, Integer> Clothes = new HashMap<String, Integer>();
			StringTokenizer st;

			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());

				String cloth = st.nextToken();
				String type = st.nextToken();

				if (Clothes.containsKey(type)) {
					Clothes.put(type, Clothes.get(type) + 1);
				} else {
					Clothes.put(type, 1);
				}
			}

			int result = 1;
			for (String key : Clothes.keySet()) {
				int value = Clothes.get(key);

				result *= (value+1);
//				System.out.println(key + " " + value);

			}
			
			System.out.println(result-1);

		}

	}
}
