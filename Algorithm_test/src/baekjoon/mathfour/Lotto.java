package baekjoon.mathfour;


// #6603
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Lotto {
	
	static int[] arr;
	static int[] res;
	static int n;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			arr = new int[n];
			res = new int[n];
			
			if(n==0) break;
			
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0);
			System.out.println();
		}
		
	}
	
	static void dfs(int start, int depth) {
		if(depth == 6) {
			for(int i=0; i<n; i++) {
				if(res[i] == 1) {
					System.out.print(arr[i]+ " ");
				}
			}
			System.out.println();
		}
		
		for(int i=start; i<n; i++) {
			res[i] = 1;
			dfs(i+1, depth+1);
			res[i] =0;
		}
	}
}
