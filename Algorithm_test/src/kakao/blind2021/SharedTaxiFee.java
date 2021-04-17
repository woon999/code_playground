package kakao.blind2021;

//blind #4 합승 택시요금 (dp, 최단경로 dijkstra)
import java.util.*;

public class SharedTaxiFee {
	static List<Node>[] list;
	static int[][] dp;
	static int MAX = Integer.MAX_VALUE;
	static boolean[] checked;
	public static void main(String[] args) {
		int n =6;
		int s =4;
		int a =6;
		int b =2;
		int[][] fares = {{4,1,10}, {3,5,24}, {5,6,2}, {3,1,41}, {5,1,24}, {4,6,50}, {2,3,22},
							{1,6,25}};
		
		System.out.println(solution(n,s,a,b,fares));
	}
	
	 public static int solution(int n, int s, int a, int b, int[][] fares) {
	        int answer = 0;
	        
	        list = new ArrayList[n+1];
	        dp = new int[n+1][3];
	        checked = new boolean[n+1];
	        for(int i=0; i<list.length; i++) {
	        	list[i] = new ArrayList<>();
	        }
	        for(int i=0; i<n+1; i++	) {
	        	Arrays.fill(dp[i], MAX);
	        }
	        
	        for(int i =0; i<fares.length; i++) {
	        	int start = fares[i][0];
	        	int end = fares[i][1];
	        	int value = fares[i][2];
	        	
	        	
	        	list[start].add(new Node(end, value));
	        	list[end].add(new Node(start, value));
	        }
	        
	        
//	        for(int i=0; i<list.length; i++) {
//	        	System.out.print(i+" : ");
//	        	for(int j=0; j<list[i].size(); j++){
//	        		System.out.print(list[i].get(j).end +"-"+list[i].get(j).value +" ");
//	        	}
//	        	System.out.println();
//	        }
	        
	        // s의 최단경로 (a+b)동승 dp[route][0] 
	        dijkstra(s,0);
	        
	        // a집에서의 최단 경로 dp[route][1]
	        dijkstra(a,1);
	        
	        // b집에서의 최단 경로 dp[route][2]
	        dijkstra(b,2);
	        
//	        for(int i=1; i<n+1; i++) {
//	        	System.out.print(dp[i][0]+" ");
//	        }
//	        System.out.println();
//	        
//	        for(int i=1; i<n+1; i++) {
//	        	System.out.print(dp[i][1]+" ");
//	        }
//	        System.out.println();
//	        
//	        for(int i=1; i<n+1; i++) {
//	        	System.out.print(dp[i][2]+" ");
//	        }
	        System.out.println();
	        int noShare = dp[s][1] + dp[s][2];
	        
	        // 동행 최단 경로 구하기
	        // dp[route][0] + dp[route][1] +dp[route][2]
	        // -> route까지 동행 후 각자 집으로 가는 비용
	        int minCost = noShare;
//	        System.out.println(minCost);
	        for(int i=1; i<n+1; i++) {
	        	if(dp[i][0] ==MAX || dp[i][1] ==MAX  || dp[i][2] ==MAX ) continue;
	        	
	        	minCost = Math.min(minCost, dp[i][0] + dp[i][1] + dp[i][2]);
	        }
	        System.out.println("min: " + minCost);
	        
	        return answer;
	    }
	 
	 // 특정경로 조회 : dijkstra -> s,a,b 각각 
	 static void dijkstra(int start, int flag) {
		 Queue<Node> q = new PriorityQueue<>();
		 Arrays.fill(checked, false);
		 
		 q.add(new Node(start,0));
		 
		 dp[start][flag] =0;
		 
		 while(!q.isEmpty()) {
			 Node node = q.poll();
			 int to = node.to;
			 
			 if(checked[to]) continue;
			 else checked[to] = true;
			 
			 for(Node node2 : list[to]) {
				 int next = node2.to;
				 int value = node2.value;
				 if(dp[next][flag] >= dp[to][flag] + value) {
//					 System.out.println(next);
					 dp[next][flag] = dp[to][flag] + value;
					 q.add(new Node(next, dp[next][flag]));
				 }
			 }
		 }
		 
	 }
	
}

class Node implements Comparable<Node>{
	int to;
	int value;
	
	public Node(int to, int value) {
		this.to = to;
		this.value = value;
	}

	@Override
	public int compareTo(Node o) {
		return this.value - o.value;
	}
}
