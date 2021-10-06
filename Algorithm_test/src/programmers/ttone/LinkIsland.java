package programmers.ttone;

// 그리디 #5 섬 연결하기 (union-find) 
import java.util.*;

public class LinkIsland {
	static int[] parent;
	static class Island implements Comparable<Island>{
		int node1;
		int node2;
		int value;
		
		Island(int node1, int node2, int value){
			this.node1 = node1;
			this.node2 = node2;
			this.value = value;
		}

		@Override
		public int compareTo(Island o) {
			return this.value - o.value;
		}
		
	}
	
	public static void main(String[] args) {
		int n = 4;
		int[][] costs = { {0,1,1}, {0,2,2}, {1,2,5},
				{1,3,1}, {2,3,8} };
		
		System.out.println(solution(n, costs));
	}
	
	public static int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        
       Queue<Island> q = new PriorityQueue<>(); 
       
       for(int i=0; i<costs.length; i++) {
    	   q.offer(new Island(costs[i][0], costs[i][1], costs[i][2]));
//    	   System.out.println(costs[i][0] + " , " + costs[i][1]+ " , " +costs[i][2]);
       }
       
       for(int i=0; i<n; i++) {
    	   parent[i] = i;
       }
       
       
       while(!q.isEmpty()) {
    	   Island is = q.poll();
    	   
    	   int from= is.node1;
    	   int to = is.node2;
    	   int v = is.value;
    	   System.out.println(from + " , " + to + " , " + v);
    	   
    	   if(find(from) == find(to)) continue;
    	   else {
    		   union(from, to);
    		   answer += v;
    	   }
    	   
       }

        return answer;
    }
	
	public static int find(int n) {
		if(parent[n] == n ) return n;
		return parent[n] = find(parent[n]);
	}
	
	public static void union(int a, int b ) {
		int ra =find(a);
		int rb = find(b);
		
		if(ra != rb) parent[rb] = ra;
	}
	
	
	
}



