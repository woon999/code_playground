package baekjoon.ttzero.greedy;

// #2457
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Garden {

	static int a = 301;
	static int z = 1201;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int count = 0;
		HashMap<Integer, Integer> flo = new HashMap<>();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = 100*Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
			int end = 100*Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
			
			
			if(flo.get(start) == null || flo.get(start) < end) {
				flo.put(start, end);
			}
		}
		
		boolean check = false;
		int cur = a;
		while(cur < z) {
			int max = cur;
			for(int key : flo.keySet()) {
				if(key <= cur && max <flo.get(key)) {
					max = flo.get(key);
					check = true;
				}
			}
			
			if(check) {
				cur = max;
				check = false;
				count++;
			} else {
				count =0;
				break;
			}
		}
		
		System.out.println(count);
		

	}
}
