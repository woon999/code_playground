package baekjoon.tttwo.graph;

// #2210 graph 숫자판 점프 - dfs 
import java.io.*;
import java.util.*;

public class NumberJump {

	static final int SIZE = 5;
	static Set<String> set;
	static boolean[][] check;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[SIZE][SIZE];
		for(int i=0; i<SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<SIZE; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		set = new HashSet<>();
		
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				stack.add(map[i][j]);
				dfs(j, i, stack);
				stack.pop();
			}
		}
		
		System.out.println(set.size());
	}
	
	static void dfs(int x, int y, Stack<Integer> stack) {
		if(stack.size() == 6) {
			StringBuilder sb = new StringBuilder();
			for(int s : stack) {
				sb.append(s);
			}
			set.add(sb.toString());
			return;
		}
			
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx > SIZE-1 || ny > SIZE-1) continue;
		
			stack.add(map[ny][nx]);
			dfs(nx, ny, stack);
			stack.pop();
		}
	}
}
