package kakao.bline2022;

//#5 kakao2022blind 양과 늑대 
import java.util.*;

public class WolfAndGoat {

	public static void main(String[] args) {
//		int[] info = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
//		int[][] edges = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
//				{ 4, 6 }, { 8, 9 } };

		int[] info = { 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 };
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, 
				{ 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, 
				{ 4, 8 }, { 6, 9 }, { 9, 10 } };

		System.out.println(solution(info, edges));
	}

	static int size;
	static List<Integer>[] list;
	static int[][] memo;
	public static int solution(int[] info, int[][] edges) {
		size = info.length;
		list = new ArrayList[size];
		for (int i = 0; i < size; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < edges.length; i++) {
			list[edges[i][0]].add(edges[i][1]);
			list[edges[i][1]].add(edges[i][0]);
		}

		memo = new int[size][1 << size];
		for (int i = 0; i < size; i++) {
			Arrays.fill(memo[i], -1);
		}
		
		return dfs(0, 1, info);
	}

	static int dfs(int here, int status, int[] info) {
		if (memo[here][status] != -1) {
			return memo[here][status];
		}

		int total = 0;
		int wolf = 0;
		for (int i = 0; i < size; i++) {
			if ((status & (1 << i)) != 0) {
				wolf += info[i];
				total++;
			}
		}

		if (total <= 2*wolf) return memo[here][status] = 0;

		memo[here][status] = total-wolf;
		for (int i = 0; i < size; i++) {
			if(list[here].contains(i)) {
				memo[here][status] = Math.max(memo[here][status], dfs(i, status | (1 << i), info));
			}
		}

		return memo[here][status];
	}

}
