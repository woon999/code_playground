package kakao.blind2021;

// blind #7 매출하락최소화 - 중간 저장 
import java.util.*;

public class MinimizeSalesDecline {
	
	static int[][] dp;
	static List<Integer>[] sales_info;
	static boolean[] check;
	

	public static void main(String[] args) {
		int[] sales = {14, 17, 15, 18, 19, 14, 13, 16, 28, 17};
		int[][] links = { {10, 8}, {1, 9}, {9, 7}, 
						{5, 4}, {1, 5}, {5, 10}, {10, 6},
						{1, 3}, {10, 2} };
		
		System.out.println(solution(sales, links));
	}

	
	public static int solution(int[] sales, int[][] links) {
        int answer = 0;
        
        int n1 = sales.length;
        int n2 = links.length;
        dp = new int[n1+1][2];
        sales_info = new ArrayList[n1+1];
        for(int i=0; i<n1+1; i++) {
        	Arrays.fill(dp[i], -1);
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
        
        
     
        return answer;
    }
	

}
