package programmers;

import java.util.*;

// tn
public class FindPrimeNumbers {
	static String[] result;
	static String[] num;
	static Map<Integer, Integer> map;
	static boolean[] check;

	public static void main(String[] args) {
//		String numbers = "17";
		String numbers = "011";
		System.out.println(solution(numbers));
	}
	
	public static int solution(String numbers) {
        num = numbers.split("");
        map = new HashMap<>();
        check = new boolean[numbers.length()];
        result = new String[numbers.length()];
        
        for(String s : num) {
        	System.out.println(s);
        }
        
        for(int i=1; i<numbers.length()+1; i++) {
        	cycle(0, i, numbers.length());
        }
        
        return map.size();
    }
	
	public static void cycle(int start, int end, int len) {
		
		// permutation 
		if(start == end) {
			System.out.println("! : " +start);
			prime();
		}else {
			for(int i=0; i<len; i++) {
				if(!check[i]) {
					result[start] = num[i];
					check[i] = true;
					cycle(start+1, end, len);
					check[i]=false;
				}
			}
		}
	}
	
	public static void prime() {
		String s = "";
		for(int i=0; i<result.length; i++) {
			s += result[i] == null? "" : result[i];
		}
		int num = Integer.parseInt(s);
		if(num == 1 || num == 0) return;
		
		
		boolean flag = false;
		for(int i=2; i<=Math.sqrt(num); i++) {
			if(num % i ==0) flag=true;
		}
		
		if(!flag) {
			map.put(num, map.getOrDefault(num, 1));
			System.out.println("num : " + num);
		}
	}
}


