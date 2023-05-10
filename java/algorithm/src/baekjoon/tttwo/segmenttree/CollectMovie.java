package baekjoon.tttwo.segmenttree;

// #3653 segmenttree 영화수집 - 펜윅 트리 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CollectMovie {

	static int n, m;
	static int[] tree;
	
	static void update(int idx, int val)	{
		while(idx <=n+m) {
			tree[idx] += val;
			idx += (idx & -idx);
		}
	}
	
	static int sum(int idx) {
		int result = 0;
		while(idx > 0) {
			result += tree[idx];
			idx -= (idx& -idx);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(tc-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int[] movieIdx = new int[n+1];
			tree = new int[n+m+1];
			
			for(int i=1; i<=n; i++) {
				movieIdx[i] = m+i;
				update(movieIdx[i], 1);
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) {
//				print();
				int select = Integer.parseInt(st.nextToken());
				int selectIdx = movieIdx[select];
				
//				System.out.println(movieIdx[select]+ " #"+sum(selectIdx-1));
				sb.append(sum(selectIdx-1)+" ");
				
				update(selectIdx, -1);
				movieIdx[select] = m-i;
				update(movieIdx[select], 1);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void print() {
		for(int i=1; i<=n+m; i++) {
			System.out.print(tree[i]+" ");
		}
		System.out.println();
	}
}
