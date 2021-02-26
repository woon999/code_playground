package programmers;

// DP #2 정수 삼각형   
public class IntTriangle {

	public static void main(String[] args) {
		int[][] triangle = {{7}, {3, 8}, {8,1,0}, {2,7,4,4}, {4,5,2,6,5}};
		
		System.out.println(solution(triangle));
	}
	
	public static int solution(int[][] triangle) {
        int answer = 0;
        
        int depth = triangle.length;
        int maxLen = triangle[depth-1].length;
        int[][] dp = new int[depth][maxLen];
        
        dp[0][0] = triangle[0][0];
        for(int i=1; i<depth; i++) {
        	for(int j=0; j<triangle[i].length; j++) {
        		int pos =triangle[i][j];
        		if(j==0) dp[i][j] = dp[i-1][j] + pos;
        		else if(i==j) dp[i][j] = dp[i-1][j-1] + pos;
        		else {
        			int left = dp[i-1][j-1] + pos;
        			int right = dp[i-1][j] + pos;
        			dp[i][j] = Math.max(left, right);
        		}
        	}
        }
        
        
        for(int i=0; i<maxLen; i++) {
        	answer = Math.max(answer, dp[depth-1][i]);
        }
        
        return answer;
    }
	
}
