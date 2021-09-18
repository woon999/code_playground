package baekjoon.ttone.tree;

// #4308 tree 트리 - union find
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class TreeSeeking {

	static int[] parents;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int idx=1;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			if(n==0 && m==0) break;
			
			parents = new int[n+1];
			for(int i=1; i<n+1; i++) {
				parents[i] = i;
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a,b);
			}
			
			Set<Integer> treeSet = new HashSet<>();
			for(int i=1; i<n+1; i++) {
				int ra = find(i);
				if(ra >0) {
					treeSet.add(ra);	
				}
			}
			
			int treeNum= treeSet.size();
			if(treeNum ==0) {
				sb.append("Case " + idx +": ").append("No trees.\n");
			}else if(treeNum ==1) {
				sb.append("Case " + idx +": ").append("There is one tree.\n");
			}else if(treeNum >1){
				sb.append("Case " + idx +": ").append("A forest of "+ treeNum+" trees.\n");
			}
			
			idx++;
		}
		
		System.out.println(sb.toString());
	}
	
	static int find(int x) {
		if(parents[x] ==x) return x;
		return find(parents[x]);
	}
	
	static void union(int x, int y) {
		int rx= find(x);
		int ry= find(y);
		if(ry < rx) {
			int tmp = rx;
			rx = ry;
			ry = tmp;
		}
		
		// cycle
		if(rx==ry) {
			parents[rx] = 0;
		}
		else {
			parents[ry] = rx;
		}
	}
}

