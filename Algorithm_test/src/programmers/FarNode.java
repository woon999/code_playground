package programmers;

// 그래프 #1 가장 먼 노드 (BFS)
import java.util.LinkedList;
import java.util.Queue;

public class FarNode {

	public static void main(String[] args) {
		int n = 6;
		int[][] edge = { {3,6}, {4,3}, {3,2}, {1,3}, {1,2},
						{2,4}, {5,2}};
		
		System.out.println(solution(n, edge));
		
	}
	
	public static int solution(int n, int[][] edge) {
		int answer = 0;
		
		boolean[] visited = new boolean[n+1];
		boolean[][] node = new boolean[n+1][n+1];
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=0; i<edge.length; i++	) {
			node[edge[i][0]][edge[i][1]] = true;
			node[edge[i][1]][edge[i][0]] = true;
		}
		
		visited[1] = true;
		q.add(1);
		
		
		while(!q.isEmpty()) {
			int size = q.size();
			System.out.println("size :  " +size);
			
			for(int i=0; i<size; i++) {
				int pos =q.poll();
				System.out.println(pos);
				for(int j=1; j<n+1; j++) {
					if(!visited[j] && node[pos][j]) {
						q.add(j);
						visited[j] = true;
					}
				}
			}
		}
		return answer;
	 }
}
