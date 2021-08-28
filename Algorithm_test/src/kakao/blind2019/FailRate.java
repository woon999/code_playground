package kakao.blind2019;

// blind #2 실패율 - 자료구조  
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FailRate {

	public static void main(String[] args) {
//		int N = 5;
//		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		
		int N = 4;
//		int[] stages = {4,4,4,4,4,4};
		int[] stages = {2,2,2,2,2,2};
		
		System.out.println(Arrays.toString(solution(N, stages)));
	}
	
	public static int[] solution(int n, int[] stages) {
		int[] stageStatus = new int[n+1];
		for(int num : stages) {
			if(num == n+1) continue;
			stageStatus[num]++;
		}
		
//		for(int i=1; i<n+1; i++) {
//			System.out.print(stageStatus[i]+" ");
//		}
//		System.out.println();
		
		Map<Integer, Double> res = new HashMap<>();
		int total = stages.length;
		for(int i=1; i<n+1; i++) {
			if(total==0) {
//				System.out.println(i +", " + stageStatus[i]);
				res.put(i, (double) 0);
			}
			else {
				double rate = (double)stageStatus[i]/(double)total;
//				System.out.println(total+" " + stageStatus[i]);
				System.out.println(rate);
				total-=stageStatus[i];
				res.put(i, rate);
			}
		}
		
		List<Integer> list = new ArrayList<>(res.keySet());
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return Double.compare(res.get(o1), res.get(o2));
			}
		});
		
        int[] answer = new int[n];
		for(int i=0; i<n; i++) {
			answer[i] = list.get(i);
			System.out.println(list.get(i));
		}
		
		return answer;
		
	}
}
