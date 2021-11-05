package programmers.ttone;

// Stack/Queue #2 주식 가격   

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StockPrice {

	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 2, 3};
		
		System.out.println(Arrays.toString(solution(prices)));
	}
	
	public static int[] solution(int[] prices) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        
        for(int i : prices) {
        	q.add(i);
        }
        
        int idx=0;
        while(!q.isEmpty()) {
        	int price = q.poll();
        	int count =0;
        	
        	for(int i=idx; i<prices.length-1; i++	) {
        		if(price > prices[i]) {
        			break;
        		}
        		count++;
        	}
        	
        	list.add(count);
        	idx++;
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
