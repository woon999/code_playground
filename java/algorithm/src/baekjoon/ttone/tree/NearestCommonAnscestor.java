package baekjoon.ttone.tree;

// #3584 트리 가장 가까운 공통 조상 - LCA 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class NearestCommonAnscestor{

	static List<Integer>[] list;
	static int[] parent, depth;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t=0; t<tc; t++) {
			int n = Integer.parseInt(br.readLine());
			
			parent = new int[n+1];
			depth = new int[n+1];
			list = new ArrayList[n+1];
			for(int i=1; i<n+1; i++) {
				list[i] = new ArrayList<>();
			}
			
			boolean[] rootCheck = new boolean[n+1];
			Arrays.fill(rootCheck, true);
			StringTokenizer st;
			for(int i=0; i<n-1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				rootCheck[b] = false;
			}
			int rootIdx=0;
			for(int i=1; i<n+1; i++) {
				if(rootCheck[i]) {
					rootIdx =i; 
					break;
				}
			}
			
			init(rootIdx,1,0);
			
			print();
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(LCA(a,b));
		}
		
	}
	
	static void print() {
		for(int num : depth) {
			System.out.print(num+" ");
		}
		System.out.println();
		
		for(int num : parent) {
			System.out.print(num+" ");
		}
		System.out.println();
	}

	static void init(int cur, int h, int pa) {
		depth[cur] = h;
		parent[cur] = pa;
		for(int nxt : list[cur]) {
			if(nxt != pa) {
				init(nxt, h+1, cur);
			}
		}
	}
	
	static int LCA(int a, int b) {
		int ah = depth[a];
		int bh = depth[b];
		while(ah > bh) {
			a = parent[a];
			ah--;
		}
		
		while(bh > ah) {
			b = parent[b];
			bh--;
		}
		
		while(a!=b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}
}
