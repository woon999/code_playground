package baekjoon.ttzero.greedy;

// #2810
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CupHolder {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		String s = br.readLine();

		boolean[] check = new boolean[n + 1];
		int count = 0;
		boolean visited = false;

		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);

			if (c == 'L') {
				if(!visited) {
					visited = true;
					check[i+1] = true;
				}
				else {
					visited = false;
				}
			
			}
			
			
			if(!check[i]) {
				check[i] = true;
				count++;
			}
			else if(!check[i+1]){
				check[i+1] = true;
				count++;
			}
		}
		
		
		System.out.println(count);

	}
}
