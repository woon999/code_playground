package baekjoon.greedy;

// #4796
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Camping {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int idx=1;
		while (true) {
		
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if (l == 0 & p == 0 & v == 0)
				break;
			
			
			int result = (v/p) *l;
			result += Math.min(l, v%p);
			
			sb.append("Case "+ (idx++) + ": " + result );
			sb.append("\n");
			
		}
		
		System.out.println(sb.toString());

	}
}
