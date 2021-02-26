package programmers;

// DP #4 도둑질 
public class Thief {

	public static void main(String[] args) {
		int[] money = { 1, 2,3,1};
		
		System.out.println(solution(money));
	}
	public static int solution(int[] money) {
		int[] dp = new int[money.length-1];
		int[] dp2 = new int[money.length];
		
		dp[0] = money[0];
	    dp[1] = money[0];

		for(int i=2; i<money.length-1; i++) {
			dp[i] = Math.max(dp[i-2] + money[i], dp[i-1]);
		}
		
		dp2[0] = 0;
		dp2[1] = money[1];
		
		for(int i=2; i<money.length; i++) {
			dp2[i] = Math.max(dp2[i-2] + money[i], dp2[i-1]);
			
		}
//		for(int i : dp) {
//			System.out.println(i + " ");
//		}
		
		
		return Math.max(dp[money.length-2], dp2[money.length-1]);
    }
}
