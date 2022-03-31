package kakao.bline2022;

//#2 kakao2022blind k진수에서 소수 개수 구하기  

import java.util.Arrays;

public class KgetPrime {

	public static void main(String[] args) {
		int n = 437674;
		int k = 3;
		
//		int n = 110011;
//		int k = 10;
		
		System.out.println(solution(n,k));
	}
	
	public static int solution(int n, int k) {
        return (int)Arrays.stream(Integer.toString(n,k).split("0+"))
        		.mapToLong(Long::parseLong)
        		.filter(s -> s>1)
        		.filter(KgetPrime::checkIsPrimeNumber)
        		.count();
	}
	
	static boolean checkIsPrimeNumber(long num) {
		for(long i=2 ; i*i<num+1; i++) {
			if(num%i ==0) {
				return false; 
			}
		}
		return true;
	}
}
