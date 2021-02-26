package programmers;

// DP #3 등굣길    
public class GoToSchool {

	public static void main(String[] args) {
		int m =4;
		int n =3;
		int[][] puddles = {{2,2} ,{2,3}};
		
		System.out.println(solution(m,n,puddles));
	}
	
	public static int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        int mod = 1_000_000_007;
        
        dp[1][1] =1;

        for(int i=0; i<puddles.length; i++) {
        	int a = puddles[i][1];
        	int b = puddles[i][0];
        	dp[a][b] = -1;
        }
        
        for(int i=1; i<n+1; i++) {
        	for(int j=1; j<dp[i].length; j++) {
        		if(dp[i][j] == -1) {
        			dp[i][j] = 0;
        			continue;
        		}
		       	if(i==1) {
		       		dp[i][j] += dp[i][j-1];
		       	}
		     	else{ 
		       		dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % mod;
		       	}
        		
        	}
        }
        
        for(int i=1; i<n+1; i++) {
        	for(int j=1; j<m+1; j++) {
        		System.out.print(dp[i][j]+" ");
        	}
        	System.out.println();
        }
        
        return dp[n][m];
    }
}
