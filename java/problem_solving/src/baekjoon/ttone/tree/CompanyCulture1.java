package baekjoon.ttone.tree;

// #14267 tree 회사 문화1 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CompanyCulture1 {

	static List<Integer>[] list;
	static int[] wv;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int a=1; a<n+1; a++) {
			int b = Integer.parseInt(st.nextToken());
			if(b!=-1) {
				list[b].add(a);
			}
		}
		
		wv = new int[n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int man = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			wv[man] += w;
			
		}
		for(int i=1; i<n+1; i++) {
			System.out.print(wv[i] +" ");
		}
		System.out.println();
		dfs(1);
		for(int i=1; i<n+1; i++) {
			System.out.print(wv[i]+" ");
		}
		
	}
	
	static void dfs(int idx) {
		for(int nxt : list[idx]) {
			wv[nxt] += wv[idx];
			dfs(nxt);
		}
		
	}
}
