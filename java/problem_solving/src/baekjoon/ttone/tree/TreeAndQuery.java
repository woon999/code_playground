package baekjoon.ttone.tree;

// #15681 tree 트리와 쿼리 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class TreeAndQuery {

	static int[] numOfChild;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		numOfChild = new int[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		Arrays.fill(numOfChild, 1);
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			list[u].add(v);
			list[v].add(u);
		}
		
		traversal(r, -1);
		
		for(int i=0; i<q; i++) {
			int idx = Integer.parseInt(br.readLine());
			System.out.println(numOfChild[idx]);
		}
		
		
	}
	
	static void traversal(int idx, int pa) {
		for(int nxt : list[idx]) {
			if(pa != nxt) {
				traversal(nxt, idx);
			}
		}
		
		if(pa!=-1) {
			numOfChild[pa] += numOfChild[idx];
		}
	}
}
