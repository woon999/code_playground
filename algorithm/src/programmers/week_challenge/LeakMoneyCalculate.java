package programmers.week_challenge;

//week_challenge 부족한 금액 계산하기  

public class LeakMoneyCalculate {

	public static void main(String[] args) {
		int price = 3;
		int money = 20;
		int count = 4;
		
		solution(price, money, count);
	}
	
	public static long solution(int price, int money, int count) {
        long total = 0;
        for(int i=1; i<=count; i++) {
        	total += price*i;
        }
        
        if(total <= money) return 0;
        
        return total-money;
    }
}
