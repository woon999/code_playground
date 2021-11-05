package baekjoon.ttone.tree;

// #15900 tree 나무 탈출 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeEscape {

	static List<Integer>[] list;
	static int cnt=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		traversal(1,-1,0);
		System.out.println(cnt%2==0 ? "No" : "Yes");
	}
	
	static void traversal(int idx, int pa, int depth){
		for(int nxt : list[idx]) {
			if(nxt != pa) {
				traversal(nxt, idx, depth+1);
			}
		}
		
		if(pa!=-1 && list[idx].size()==1) {
			cnt += depth;
		}
	}
}
