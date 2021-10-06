package programmers.ttone;

// Stack/Queue #1 다리를 지나는 트럭 
import java.util.LinkedList;
import java.util.Queue;

public class BridgeTruck {

	public static void main(String[] args) {
		int bridge_length =3;
		int weight = 10; 
		int[] truck_weights = {7, 4, 5, 6};
		
		System.out.println(solution(bridge_length, weight, truck_weights));
	}
	
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Queue<Integer> q = new LinkedList<>();
        
        
        int capacity=0;
        for(int truck : truck_weights) {
    		
    		while(true) {

    			if(q.isEmpty()) {
    				q.add(truck);
    				capacity += truck;
    				answer++;
    				break;
    			}
    			
    			else if(q.size() == bridge_length) {
    				capacity -= q.poll();
    			}
    			
    			else {
    				if(capacity + truck > weight) {
    					q.add(0);
    					answer++;
    				}else {
    					q.add(truck);
    					capacity += truck;
    					answer++;
    					break;
    				}
    			}
            }
        }
        
        
        return answer + bridge_length;
    }
}
