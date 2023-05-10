package baekjoon.tttwo.dp;

// #2367 dp 장난감 조립 - 위상 정렬 
import java.io.*;
import java.util.*;

public class Toy_2637 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int m = Integer.parseInt(br.readLine());
		
		// 5 1-2개, 2-2개 
		// 6 3-3개, 4-4개, 5-2개
		// 7 4-5개, 5-2개, 6-3개
		
		StringTokenizer st;
		List<int[]>[] list = new ArrayList[101];
		int[] in = new int[101];
		for(int i=0; i<101; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int full = Integer.parseInt(st.nextToken());
			int mid = Integer.parseInt(st.nextToken());
			int ea = Integer.parseInt(st.nextToken());
			
			list[full].add(new int[] {mid, ea});
			in[mid]++;
		}
		
//		for(int i=0; i<101; i++) {
//			if(list[i].size() > 0 ) {
//				System.out.println(i +" - " );
//				for(int[] a : list[i]) {
//					System.out.println(Arrays.toString(a));
//				}
//			}
//		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		int[] dp = new int[101];
		dp[n]=1;
		Set<Integer> basic = new TreeSet<>();
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			// 기본 부품  
			if(list[cur].size() == 0) {
				basic.add(cur);
			}
			
			for(int[] nxt : list[cur]) {
				int nxtNode = nxt[0];
				int ea = nxt[1];
				dp[nxtNode] += dp[cur]*ea;
				in[nxtNode]--; // 진입차수 -1
				
				if(in[nxtNode] == 0) {
					q.add(nxtNode);
				}
			}
		}
		
		for(int num : basic) {
			System.out.println(num+" "+dp[num]);
		}
	}
}
