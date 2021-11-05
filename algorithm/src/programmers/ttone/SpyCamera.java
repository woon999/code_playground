package programmers.ttone;

// 그리디 #6 단속카메라 
import java.util.PriorityQueue;
import java.util.Queue;

public class SpyCamera {

	public static void main(String[] args) {
		int[][] routes = { {-20,15}, {-14,-5}, {-18,-13}, {-5,-3} };
		
		System.out.println(solution(routes));
	}
	
	public static int solution(int[][] routes) {
        int answer = 1;
        
        Queue<Car> q = new PriorityQueue<>();
        
        for(int i=0; i<routes.length; i++) {
        	q.add(new Car(routes[i][0], routes[i][1]));
        }
        
        
        Car firstCar = q.poll();
        
        int lastIn = firstCar.start;
        int firstOut = firstCar.end;

        while(!q.isEmpty()) {
        	Car car = q.poll();
        	int in = car.start;
        	int out = car.end;
        	
        	// if (----out---- lastIn (ooooooo) firstOut ----in----)
        	if( out < lastIn || firstOut < in ) {
        		lastIn = in;
        		firstOut = out;
//        		System.out.println(in + " , " + out);
        		answer++;
        	}else {
        		lastIn = lastIn < in ? in : lastIn;
        		firstOut = firstOut > out ? out : firstOut;
        	}
     
        }
        
        return answer;
    }
}

class Car implements Comparable<Car>{
	int start;
	int end;
	
	Car(int start, int end){
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Car o) {
		return this.start - o.start;
	}
}
