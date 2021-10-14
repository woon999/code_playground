package baekjoon.ttone.graph;

// #13023 graph ABCDE - dfs, backtracking 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ABCDE {

	static boolean status;
	static List<Integer>[] list;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n];
		for(int i=0; i<n; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		status = false;
		for(int i=0; i<n; i++) {
			System.out.println(" -- " + i);
			check = new boolean[n];
			dfs(i,1);
			if(status) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}
	
	static void dfs(int idx, int depth) {
		if(depth==5) {
			status=true;
			return;
		}
		
		check[idx]= true;
		for(int nxt : list[idx]) {
			if(!check[nxt]) {
				dfs(nxt, depth+1);
			}
		}
		check[idx]= false;
	}
}





