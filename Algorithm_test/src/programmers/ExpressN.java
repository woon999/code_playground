package programmers;


// DP #1 N으로 표현  (DP & DFS) 
import java.util.*;

public class ExpressN {
	
//  DFS
	
	public static void main(String[] args) {
		int N =5;
		int number =12;
		System.out.println(solution(N,number));
	}
	
	static int answer = -1;

    public static int solution(int N, int number) {
    	
        dfs(N, 0, 0, number, "");
        return answer;
    }
    
    static void dfs(int n, int pos, int num, int number, String s) {
    	if(pos>8) {
    		return;
    	}
    	if(num == number) {
    		if(pos < answer || answer == -1) {
    			System.out.println(s);
    			answer = pos;
    		}
    		return;
    	}
    	
    	int nn=0;
    	
    	for(int i =0; i< 8; i++) {
    		nn = nn*10 +n;
    		dfs(n, pos +1+i, num+nn, number, s+"+");
    		dfs(n, pos +1+i, num-nn, number, s+"-");
    		dfs(n, pos +1+i, num*nn, number, s+"*");
    		dfs(n, pos +1+i, num/nn, number, s+"/");
    	}
    }
	
	
//  DP
	
//	static Set<Integer>[] num;
//
//	public static void main(String[] args) {
//		int N = 5;
//		int number = 12;
//		
//		System.out.println(solution(N, number));
//	}
//	
//	public static int solution(int N, int number) {
//        int answer = 0;
//        
//        if(N == number) return 1;
//        num = new HashSet[9];
//        
//        for(int i=0; i<num.length; i++) {
//        	num[i] = new HashSet<>();
//        }
//        num[0].add(0);
//        int sum = 0;
//        for(int i =1; i<9; i++) {
//        	sum = sum*10 + N;
//        	num[i].add(sum);
////        	System.out.println(sum);
//        }
//        
//        for(int i=1; i<9; i++) {
////        	System.out.println(i);
//        	for(int j=1; j<i; j++) {
//        		int k = i-j;
////        		System.out.println(j + " , " + k);
//        		calc(j,k);
//        		if(num[i].contains(number)) return i;
//        	}
//        	
//        }
//        
//        return -1;
//    }
//	
//	static void calc(int j, int k) {
//		
//		Iterator<Integer> list1 = num[j].iterator();
//		Iterator<Integer> list2 = num[k].iterator();
//		
//		int idx = j+k;
//		while(list1.hasNext()) {
//			int nA = list1.next();
//			
//			while(list2.hasNext()) {
//				int nB = list2.next();
////				System.out.println(nA+" : " + nB);
//				num[idx].add(nA+nB);
//				num[idx].add(nA-nB);
//				num[idx].add(nA*nB);
//				if(nB!=0) num[idx].add(nA/nB);
//				
//			}
//			
//			list2 = num[k].iterator();
////			for(Set s : num) {
////				System.out.println(s);
////			}
//			
//		}
//	}
}
