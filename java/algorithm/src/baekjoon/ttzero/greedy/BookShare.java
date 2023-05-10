package baekjoon.ttzero.greedy;

// #9576
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BookShare {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test = Integer.parseInt(br.readLine());

		StringTokenizer st;

		for (int i = 0; i < test; i++) {

			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			ArrayList<Book> book = new ArrayList<>();
			boolean[] check = new boolean[n + 1];

			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				book.add(new Book(a, b));

			}

			Collections.sort(book);
			
			int count = 0;
				for (Book list : book) {
					for (int j = list.a; j <= list.b; j++) {
						if (!check[j]) {
							check[j] = true;
							count++;
							break;
						}
					}
				}

			System.out.println(count);
		}

	}
}

class Book implements Comparable<Book>{
	int a;
	int b;
	int len;

	public Book(int a, int b) {
		this.a = a;
		this.b = b;
		this.len = b - a + 1;
	}

	@Override
	public int compareTo(Book o) {

		if (this.b < o.b)
			return -1;

		else if (this.b == o.b) {
			return this.len < o.len ? -1 : 1;
		}

		else
			return 1;
	}
}
