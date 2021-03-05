package programmers;

// 그래프 #2 순위 (플로이드 워셜 알고리즘)
public class Rank {

	public static void main(String[] args) {
		int[][] a = {{4,3}, {4,2},{3,2},{1,2},{2,5}};
		int n =5;
		
		System.out.println(solution(n,a));
		
	}
	
	static int solution(int n, int[][] results) {
		int answer =0;
		int[][] floyd = new int[n][n];
		
		int MAX = 1_000_000_000;
		
		// 결과 그래프 초기화 
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++){
				if(i==j) {
					floyd[i][j]	 =0;
				}else floyd[i][j] = MAX;
			}
		}
		
		// 결과 그래프 입력 
		for(int i=0; i<results.length; i++) {
//			System.out.println(results[i][0] + ", " + results[i][1]);
			floyd[results[i][0]-1][results[i][1]-1] = 1;
			
		}
		
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				System.out.print(floyd[i][j]+ " ");
//			}
//			System.out.println();
//		}
		
		
		// k : 거쳐가는 노드 (기준) 
		for(int k=0; k<n; k++) {
		
			// i : 출발 노드  
			for(int i=0; i<n; i++) {
				
				// j : 도착 노드 
				for(int j=0; j<n; j++) {
					// i에서 j로 가는 최소 비용 VS 
					//         i에서 노드 k로 가는 비용 + 노드 k에서 jY로 가는 비용
					if(floyd[i][k] + floyd[k][j] < floyd[i][j]) {
						floyd[i][j] = floyd[i][k] + floyd[k][j];
					}
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(floyd[i][j]+ " ");
			}
			System.out.println();
		}
		
		for(int i=0; i<n; i++) {
			boolean flag = true;
			for(int j=0; j<n; j++) {
				if(i==j) continue;
				if(floyd[i][j] == MAX && floyd[j][i] == MAX) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				System.out.println(i);
				answer++;
			}
		}
		
		
		return answer;
	}
}
