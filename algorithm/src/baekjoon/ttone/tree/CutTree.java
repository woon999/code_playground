package baekjoon.ttone.tree;

// #14675 tree 단절점과 단절선 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CutTree {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		List<Integer>[] list = new ArrayList[n+1];
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
		
		StringBuilder sb = new StringBuilder();
		int q = Integer.parseInt(br.readLine());
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if(op==2) {
				sb.append("yes\n");
			}else {
				int idx = Integer.parseInt(st.nextToken());
				if(list[idx].size() >=2) sb.append("yes\n");
				else sb.append("no\n");
			}
			
		}
		System.out.println(sb.toString());
				
	}
}
