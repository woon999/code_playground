package baekjoon.ttone.graph;

// #17142 graph 연구소 3 
import java.io.*;
import java.util.*;


public class Lab3 {

	static int n,m, space, result = Integer.MAX_VALUE;
	static int[][] map;
	static List<Integer> vList;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		
		vList = new ArrayList<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int num =Integer.parseInt(st.nextToken());
				if(num==2) {
					vList.add(i*n+j);
				}else if(num==0) {
					space ++;
				}
				map[i][j]= num; 
				
			}
		}
		if(space ==0) {
			System.out.println(0);
		}else {
			Stack<Integer> vs = new Stack<>();
			virusCombination(vs, 0,0);
			System.out.println(result == Integer.MAX_VALUE? -1 : result);
		}
	}
	
	static void virusCombination(Stack<Integer> comb, int pos, int len) {
		if(pos > vList.size()) return ;
		
		if(len == m	) {
			List<Integer> vComb = new ArrayList<>();
			for(int num : comb) {
				vComb.add(num);
			}
			
//			for(int i : vComb) {
//				System.out.print(i+" ");
//			}
//			System.out.println();
			
			int takeTime = bfs(vComb);
//			System.out.println(takeTime);
			if(takeTime != -1) {
				result = Math.min(result, takeTime);
			}
			return ;
		}
		
		for(int i=pos; i<vList.size(); i++) {
			comb.push(vList.get(i));
			virusCombination(comb, i+1, len+1);
			comb.pop();
			
		}
		
		return ;
		
	}
	
	static int bfs(List<Integer> vComb) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] check = new boolean[n][n];
		for(int start : vComb) {
			int sx = start%n;
			int sy	= start/n;
			q.add(new int[] {sx,sy});
			check[sy][sx] = true;
		}
		
		int restCnt = space;
		int time =0;
		while(!q.isEmpty()) {
			
			int size = q.size();
			for(int turn=0; turn<size; turn++) {
				int[] pos = q.poll();
				int px = pos[0];
				int py = pos[1];
				
				for(int i=0; i<4; i++) {
					int nx = px + dx[i];
					int ny = py + dy[i];
					
					if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1	|| check[ny][nx]) continue;
					
					if(map[ny][nx] == 0 || map[ny][nx] ==2) {
						check[ny][nx] = true;
						q.add(new int[] {nx,ny});
						if(map[ny][nx]==0) restCnt--;
						
					}
				}
			}
			
			// 한 싸이클 카운트 
			time ++;
			
			// 바이러스 모두 퍼졌으면 리턴 
			if(restCnt ==0) {
				return time;
			}
			
		}
		return -1;
		
	}
	
}

//11 2
//1 1 0 1 1 1 1 1 0 1 1
//1 1 2 1 1 1 1 1 2 1 1
//0 1 2 1 1 1 0 1 2 1 1
//0 1 0 1 1 1 0 1 0 1 1
//0 0 2 0 0 1 0 0 2 0 0
//1 1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1 1
