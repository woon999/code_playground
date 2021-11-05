package programmers.ttone;


// DFS/BFS #2 네트워크  
public class Network {

	static boolean[] visited;
	public static void main(String[] args) {
		int n =3;
		int[][] computers = {{1,1,0}, {1,1,1}, {0,1,1}};
		
		System.out.println(solution(n, computers));
	}
	
	public static int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for(int i=0; i<n; i++) {
        	if(!visited[i]) {
        		answer++;
        		dfs(i, computers, n);
        	}
        }
        return answer;
    }
	
	static void dfs(int idx, int[][] com, int n) {
		visited[idx] = true;
		System.out.println(idx);
		for(int i=0; i<n; i++) {
			if(!visited[i] && com[idx][i] == 1 ) {
				System.out.println(idx+" ," + i);
				dfs(i, com, n);
			}
		}
	}
}
