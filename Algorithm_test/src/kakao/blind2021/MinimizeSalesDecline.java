package kakao.blind2021;

// blind #7 매출하락최소화 - 트리 순회, DP  
import java.util.*;

public class MinimizeSalesDecline {
	
	static int[][] cost;
	static int[] extra;
	static List<Integer>[] sales_info;
	static int[] g_sales;
	static int INF = Integer.MAX_VALUE;
	

	public static void main(String[] args) {
//		int[] sales = {14, 17, 15, 18, 19, 14, 13, 16, 28, 17};
//		int[][] links = { {10, 8}, {1, 9}, {9, 7}, 
//						{5, 4}, {1, 5}, {5, 10}, {10, 6},
//						{1, 3}, {10, 2} };
		
		int[] sales = {5,6,5,3,4};
		int[][] links = { {2,3}, {1,4}, {2,5}, {1,2} };
		
		System.out.println(solution(sales, links));
	}

	
	public static int solution(int[] sales, int[][] links) {
        int answer = INF;
        
        int n1 = sales.length;
        int n2 = links.length;
        cost = new int[n1+1][2];
        extra = new int[n1+1];
        sales_info = new ArrayList[n1+1];
        g_sales = new int[n1];

        
        for(int i=0; i<n1; i++	) {
        	g_sales[i] = sales[i];
        }
        
        for(int i=0; i<n1+1; i++) {
        	sales_info[i] = new ArrayList<>();
        }
        
        for(int i=0; i<n2; i++) {
        	sales_info[links[i][0]].add(links[i][1]);
        }
        
        for(int i=1; i<n1+1; i++) {
        	if(sales_info[i].size() >0) {
        		System.out.println(i+" : " +sales_info[i]);
        	}
        }
        
        trevarsal(1); // 순회
        
        for(int i=1; i<n1+1; i++) {
        	for(int j=0; j<2; j++) {
        		System.out.print(cost[i][j]+" ");
        	}
        	System.out.println();
        }
        
        
//        System.out.println(cost[1][0] +" vs" + cost[1][1]);
        
        // 1번 팀장이 참여한 경우 vs 참여하지않은 경우 
        return Math.min(cost[1][0], cost[1][1]);
    }
	
	// 노드 순회  
	static void trevarsal(int pos) {
		int child_num = sales_info[pos].size();
		
//		System.out.println(pos+" : " +sales_info[pos]);
		cost[pos][0] = 0; // 참석 x 
		cost[pos][1] = g_sales[pos-1]; // 참석 o 
		
		// leaf 노드 도착하면 return (only 팀원 not 팀장)
		if(child_num ==0 )return;
		
		int extra_cost = INF;
		// 부모 -> 자식 탐색 
		for(int child : sales_info[pos]) {
			trevarsal(child); // child안에 값 집어넣기
			
			// ex) parent1 -> (4,2)	탐색 후 아래 로직 실행 
				
			// 팀원x : 해당 팀원(child)이 참여 안하는게 더 적은 비용이 드는 경우 
			if(cost[child][0] < cost[child][1]) {
				cost[pos][0] += cost[child][0]; // 팀장 x 팀원 x -> 예외처리 필요(다른 자식노드 참여) 
				cost[pos][1] += cost[child][0]; // 팀장 o 팀원 x 
					
				// 팀장x 팀원x인 경우 한 팀에 최소 한명이니깐 팀원 중 최소비용 추가해놓기 
				extra_cost = Math.min(extra_cost, cost[child][1] - cost[child][0]);
			}
			// 팀원 o : 해당 팀원(child)이 참여하는게 더 적은 비용이 드는 경우  
			else {
				cost[pos][0] += cost[child][1]; // 팀장 x 팀원 o
				cost[pos][1] += cost[child][1]; // 팀장 o 팀원 o
					
				// 팀장x 팀원o 이면 다른 자식이 참여헀으니깐 0으로 초기화 
				extra_cost = 0;
			}
		}
//		System.out.println(pos +" 팀장 참여안할 때 최소비용드는 팀원 cost: " + extra_cost);
		cost[pos][0] += extra_cost;
		
	
	}
	

}
