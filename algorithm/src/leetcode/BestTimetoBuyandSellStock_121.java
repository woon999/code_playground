package leetcode;

// #121 - Best Time to Buy and Sell Stock

public class BestTimetoBuyandSellStock_121 {

	public static void main(String[] args) {
		int[] prices = {7,1,5,3,6,4};

		System.out.println("maxProfit(prices) = " + maxProfit(prices));
	}

	public static int maxProfit(int[] prices) {
		int min = prices[0], max = 0;
		for(int i=1; i<prices.length; i++){
			if(min > prices[i]){
				min = prices[i];
			}else {
				max = Math.max(max, (prices[i]-min));
			}
		}
		return max;
	}
}
