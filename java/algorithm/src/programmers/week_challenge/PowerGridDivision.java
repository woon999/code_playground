package programmers.week_challenge;

import java.util.ArrayList;
import java.util.List;

//week9 전력망 둘로 나누기 - dfs 
public class PowerGridDivision {

	static List<Integer>[] list;
	static int numOfNodes;
	public static void main(String[] args) {
		int n = 9;
		int[][] wires = {
				{1,3}, {2,3}, {3,4}, {4,5},
				{4,6}, {4,7}, {7,8}, {7,9}
		};
		
//		int n = 4;
//		int[][] wires = {
//				{1,2}, {2,3}, {3,4}
//		};
		
//		int n = 7;
//		int[][] wires = {
//				{1,2}, {2,7}, {3,7}, {3,4},
//				{4,5}, {6,7}
//		};
		System.out.println(solution(n, wires));
	}
	
	public static int solution(int n, int[][] wires) {
        int answer = 100;
        int idx=0;
        list = new ArrayList[n+1];
        
        while(idx<n-1) {
	        for(int i=1; i<n+1; i++) {
	        	list[i] = new ArrayList<>();
	        }
	        
	        for(int i=0; i<wires.length; i++) {
	        	if(idx == i) continue;
	        	int a = wires[i][0];
	        	int b = wires[i][1];
	        	
	        	list[a].add(b);
	        	list[b].add(a);
	        }
	        
	        numOfNodes=0;
	        dfs(1, -1);
	        int res = Math.abs(n - 2*numOfNodes);
	        answer = Math.min(res, answer);
	        idx++;
        }
        
        
        return answer;
    }
	
	static void dfs(int idx, int pa) {
		numOfNodes++;
		for(int nxt : list[idx]) {
			if(pa != nxt) {
				dfs(nxt, idx);
			}
		}
	}
	
}
