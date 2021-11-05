package programmers.ttone;

// Heap #3 이중우선순위큐 
import java.util.*;

public class DualPriorityQueue {

	public static void main(String[] args) {
//		String[] operations = {"l 7","l 6", "D 1", "D -1"};
//		String[] operations = {"I 7","I 6"};
		String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
		
		System.out.println(Arrays.toString(solution(operations)));
	}
	
	public static int[] solution(String[] operations) {
        Queue<Operation> list = new LinkedList<>();
        LinkedList<Integer> q = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();
        
        for(String s : operations) {
        	String[] op = s.split(" ");
        	System.out.println(op[0] + "," + op[1]);
        	
        	char a = op[0].charAt(0);
        	int b = Integer.parseInt(op[1]);
        	list.add(new Operation(a,b));
        }
        
        
        
        while(!list.isEmpty()) {
        	Operation op = list.poll();
        	
        	if(op.type == 'I') {
        		q.add(op.num);
        	}else {
        		if(op.num == 1) {
        			System.out.println("최댓갑: " + q.pollLast());
        		}else {
        			System.out.println("최소 : " + q.poll());
        		}
        	}
        	q.sort((a,b) -> { return a-b;});
        }
        
        if(q.size()==0) {
        	answer.add(0);
        	answer.add(0);
        }else {
        	int max = q.pollLast();
        	int min = q.poll();
        	
//        	System.out.println("max : " + max + ",  min : "+ min);
        	answer.add(max);
        	answer.add(min);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
        
    }
}

class Operation{
	char type;
	int num;
	
	Operation(char type, int num){
		this.type = type;
		this.num = num;
	}
}
