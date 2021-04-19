package baekjoon.ttzero.greedy;

// #1781
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CupRamen {

	static Ramen[] r;
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		r = new Ramen[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			r[i] = new Ramen(i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		solve();

	}

	static void solve() {
		Arrays.sort(r);

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (int i = 0; i < n; i++) {
			pq.add(r[i].ramenNum);
			while (pq.size() > r[i].deadline) {
				pq.poll();
			}

		}

		int result = 0;
		while (!pq.isEmpty()) {
			result += pq.poll();

		}

		System.out.println(result);
	}
}

class Ramen implements Comparable<Ramen> {
	int num;
	int deadline;
	int ramenNum;

	public Ramen(int num, int deadline, int ramenNum) {
		this.num = num;
		this.deadline = deadline;
		this.ramenNum = ramenNum;
	}

	@Override
	public int compareTo(Ramen o) {
		return Integer.compare(this.deadline, o.deadline);
	}
}
